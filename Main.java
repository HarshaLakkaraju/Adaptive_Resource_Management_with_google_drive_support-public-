package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Random;
import org.opencv.core.*;

import javax.swing.JFileChooser;
import java.io.File;

public class Main extends JFrame {
    // Declare UI components and variables
    JLabel l1, l2;
    Font f1, f2;
    JPanel p1, p2, p3, p4, p5;
    LineBorder border;
    TitledBorder title;
    JTable tab;
    DefaultTableModel dtm;
    JScrollPane jsp;
    JRadioButton r1, r2, r3, r4, r5;
    ButtonGroup bg;
    int size;
    String cloud_instance[] = {"High", "Medium", "Low"};
    String frame_rates[] = {"25", "20", "15", "10", "5"};
    String cost[] = {"0.70", "0.60", "0.50", "0.30", "0.10"};
    ArrayList<BestFirstDecreasingSort> sort = new ArrayList<>();
    static double existing_cost = 0;
    static double propose_cost = 0;
    JLabel cost_label;
    JFileChooser chooser;
    File file;

    // Static block for loading OpenCV libraries and classifier
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_ffmpeg249_64");
        DetectPerson.loadClassifier();
    }

    // Constructor to set up the GUI
    public Main() {
        setTitle("Adaptive Resource Management");
        getContentPane().setLayout(new BorderLayout());
        p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        // Setting up the header panel
        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        f1 = new Font("Courier New", Font.PLAIN, 20);
        l1 = new JLabel("<HTML><BODY><CENTER>Adaptive Resource Management for Analyzing Video Streams from Globally Distributed<br/>Network Cameras</CENTER></BODY></HTML>".toUpperCase());
        l1.setFont(f1);
        p2.setBackground(Color.white);
        border = new LineBorder(Color.RED, 1, true);
        title = new TitledBorder(border, "PROJECT TITLE", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.BLUE);
        p2.setBorder(title);
        p2.add(l1);
        l1.setForeground(Color.blue);
        p1.add(p2, BorderLayout.NORTH);

        // File chooser for selecting video files
        chooser = new JFileChooser(new File("videos"));

        // Setting up the options panel
        f2 = new Font("Courier New", Font.BOLD, 14);
        p3 = new JPanel();
        FlowLayout lay = new FlowLayout();
        lay.setHgap(20);
        p3.setLayout(lay);
        p3.setBackground(Color.white);
        border = new LineBorder(Color.RED, 1, true);
        title = new TitledBorder(border, "PROJECT MODULES", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.BLUE);
        p3.setBorder(title);
        p3.setPreferredSize(new Dimension(600, 90));

        // Creating radio buttons and adding them to a button group
        bg = new ButtonGroup();
        r1 = new JRadioButton("Define Total Network Camera");
        r1.setBackground(Color.white);
        r1.setFont(f2);
        bg.add(r1);
        p3.add(r1);
        r1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                defineNetworkCamera();
            }
        });

        r4 = new JRadioButton("Assign Cloud Instances To Camera");
        r4.setBackground(Color.white);
        r4.setFont(f2);
        bg.add(r4);
        p3.add(r4);
        r4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                assignCloud();
            }
        });

        r2 = new JRadioButton("Calculate Low Cost Cloud Based On Frame Rate");
        r2.setBackground(Color.white);
        r2.setFont(f2);
        bg.add(r2);
        p3.add(r2);
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                calculateLowCostCloud();
            }
        });

        r3 = new JRadioButton("Run Simulation");
        r3.setBackground(Color.white);
        r3.setFont(f2);
        bg.add(r3);
        p3.add(r3);
        r3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int option = chooser.showOpenDialog(Main.this);
                if (option == chooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    Runnable r = new Runnable() {
                        public void run() {
                            ShowNetwork network = new ShowNetwork(sort, file.getPath());
                            network.setVisible(true);
                            network.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        }
                    };
                    new Thread(r).start();
                }
            }
        });

        // Exit radio button
        r5 = new JRadioButton("Exit");
        r5.setBackground(Color.white);
        r5.setFont(f2);
        bg.add(r5);
        p3.add(r5);
        r5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        // Label to display costs
        cost_label = new JLabel();
        cost_label.setFont(f2);
        p3.add(cost_label);

        // Add the options panel to the main panel
        p1.add(p3, BorderLayout.CENTER);

        // Setting up the table panel
        p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        dtm = new DefaultTableModel();
        tab = new JTable(dtm);
        tab.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 14));
        tab.setFont(new Font("Courier New", Font.BOLD, 13));
        tab.setRowHeight(30);
        jsp = new JScrollPane(tab);
        dtm.addColumn("Camera ID");
        dtm.addColumn("Frame Rate Requirement");
        dtm.addColumn("Chosen Cloud Instance");
        dtm.addColumn("Cost");

        jsp.getViewport().setBackground(Color.white);
        jsp.setPreferredSize(new Dimension(600, 500));
        p4.add(jsp, BorderLayout.CENTER);

        // Add the panels to the frame
        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p4, BorderLayout.CENTER);
    }

    // Method to clear the table
    public void clearTable() {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    // Method to define network camera
    public void defineNetworkCamera() {
        clearTable();
        String input = JOptionPane.showInputDialog(this, "Enter Network Camera Size");
        try {
            size = Integer.parseInt(input.trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showInputDialog(this, "Network Camera Size Must Be Numeric Only");
            return;
        }
        for (int i = 0; i < size; i++) {
            Object row[] = {"C" + i, "-", "-", "-"};
            dtm.addRow(row);
        }
    }

    // Method to assign cloud instances to cameras
    public void assignCloud() {
        clearTable();
        existing_cost = 0;
        for (int i = 0; i < size; i++) {
            String cost_data = getCloud(cost);
            existing_cost += Double.parseDouble(cost_data);
            Object row[] = {"C" + i, getCloud(frame_rates), getCloud(cloud_instance), cost_data};
            dtm.addRow(row);
        }

        cost_label.setText("Existing Technique Cost: " + existing_cost);
    }

    // Method to randomly select a cloud instance, frame rate, or cost
    public String getCloud(String arr[]) {
        Random r = new Random();
        return arr[r.nextInt(arr.length)];
    }

    // Main method to run the application
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Main on = new Main();
        on.setVisible(true);
        on.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Method to calculate the low-cost cloud technique
    public void calculateLowCostCloud() {
        sort.clear();
        propose_cost = 0;
        for (int i = 0; i < size; i++) {
            String camera = dtm.getValueAt(i, 0).toString().trim();
            int frame = Integer.parseInt(dtm.getValueAt(i, 1).toString().trim());
            String cloud = dtm.getValueAt(i, 2).toString().trim();
            double cost = Double.parseDouble(dtm.getValueAt(i, 3).toString().trim());
            existing_cost += cost;
            BestFirstDecreasingSort bf = new BestFirstDecreasingSort();
            bf.setFrame(frame + "");
            bf.setCamera(camera);
            if (frame > 15) {
                bf.setCloud("High");
                bf.setCost(0.60);
            } else if (frame > 10) {
                bf.setCloud("Medium");
                bf.setCost(0.50);
            } else {
                bf.setCloud("Low");
                bf.setCost(0.30);
            }
            propose_cost += bf.getCost();
            sort.add(bf);
        }

        clearTable();
        for (int i = 0; i < sort.size(); i++) {
            BestFirstDecreasingSort bf = sort.get(i);
            Object row[] = {bf.getCamera(), bf.getFrame(), bf.getCloud(), bf.getCost()};
            dtm.addRow(row);
        }
        cost_label.setText("Existing Technique Cost: " + existing_cost + "\nPropose Technique Low Cost: " + propose_cost);
    }
}
