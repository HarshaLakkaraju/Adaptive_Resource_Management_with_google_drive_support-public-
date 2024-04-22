 /*
 Summary:
 The ShowNetwork class is a Java Swing application that provides a user interface for managing and simulating a network of cameras. It extends the JFrame class and creates various UI components such as panels, buttons, and labels. The main functionality includes:
 
 1. Displaying a simulation panel (Simulator) for visualizing the camera network.
 2. Providing buttons for starting video analysis, starting the simulation, displaying a cost graph, and exiting the application.
 3. Handling button click events to initiate the corresponding actions, such as playing a video, starting a simulation thread, or creating a chart.
 4. Setting up the layout and appearance of the UI components.
 5. Initializing the camera network by calling the CameraPlacement.randomNodes method with the provided parameters.
 
 The purpose of this class is to provide a graphical user interface for managing and visualizing a camera network simulation, allowing users to perform various tasks such as video analysis, simulation, and cost analysis.
 */
package com;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 import java.util.ArrayList;
 import java.awt.BorderLayout;
 import java.awt.Color;
 import javax.swing.JOptionPane;
 import java.awt.Dimension;
 import java.awt.Font;
 import net.miginfocom.swing.MigLayout;
 import javax.swing.JButton;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.JLabel;
 import org.jfree.ui.RefineryUtilities;
 import java.util.ArrayList;
 
 /*
	* The ShowNetwork class is the main entry point for the simulation application.
	* It extends the JFrame class and provides a graphical user interface (GUI) for interacting with the simulation.
	*/
 public class ShowNetwork extends JFrame {
		 Simulator node; // Represents the simulation panel
		 JPanel p1, p2; // Panels for holding UI components
		 int left, top; // Variables for storing insets
		 Font f1; // Font for UI components
		 JButton b1, b2, b3, b4; // Buttons for various actions
		 ArrayList<BestFirstDecreasingSort> sort; // List of sorted camera configurations
		 AnalyzeVideo an; // Component for video analysis
		 String file; // File path for the video
		 static JLabel l1; // Label for displaying information
 
		 /*
			* Constructor for the ShowNetwork class.
			* @param s An ArrayList of BestFirstDecreasingSort objects representing sorted camera configurations.
			* @param f The file path of the video to be analyzed.
			*/
		 public ShowNetwork(ArrayList<BestFirstDecreasingSort> s, String f) {
				 super("Adaptive Resource Management");
				 sort = s;
				 file = f;
				 f1 = new Font("Courier New", Font.BOLD, 14);
				 node = new Simulator();
				 p1 = new JPanel();
				 p1.setLayout(new BorderLayout());
				 p1.add(node, BorderLayout.CENTER);
				 p1.setBackground(Color.black);
				 getContentPane().add(p1, BorderLayout.CENTER);
				 an = new AnalyzeVideo();
				 getContentPane().add(an, BorderLayout.EAST);
				 an.setPreferredSize(new Dimension(500, 400));
				 p2 = new JPanel();
				 p2.setBackground(Color.white);
				 p2.setLayout(new MigLayout("wrap 2"));
				 p2.setPreferredSize(new Dimension(600, 100));
				 b1 = new JButton("Start Video Analysis");
				 p2.add(b1, "span,split 4");
				 b1.setFont(f1);
				 b1.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent ae) {
								 // Create a new Runnable instance to play the video in a separate thread
								 Runnable r = new Runnable() {
										 public void run() {
												 an.playVideo(file);
										 }
								 };
								 new Thread(r).start();
						 }
				 });
				 b2 = new JButton("Start Simulation");
				 p2.add(b2);
				 b2.setFont(f1);
				 b2.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent ae) {
								 // Create a new Runnable instance to start the simulation in a separate thread
								 Runnable run = new Runnable() {
										 public void run() {
												 new SendData(node);
										 }
								 };
								 new Thread(run).start();
						 }
				 });
				 b3 = new JButton("Existing & ARMVAC Cost Graph");
				 p2.add(b3);
				 b3.setFont(f1);
				 b3.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent ae) {
								 // Create a new Chart instance to display the cost graph
								 Chart chart1 = new Chart("Existing & ARMVAC Cost Graph");
								 chart1.pack();
								 RefineryUtilities.centerFrameOnScreen(chart1);
								 chart1.setVisible(true);
						 }
				 });
				 b4 = new JButton("Exit");
				 p2.add(b4, "wrap");
				 b4.setFont(f1);
				 b4.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent ae) {
								 // Exit the application
								 System.exit(0);
						 }
				 });
				 l1 = new JLabel();
				 l1.setFont(f1);
				 p2.add(l1);
				 getContentPane().add(p2, BorderLayout.SOUTH);
				 left = getInsets().left;
				 top = getInsets().top;
				 CameraPlacement.randomNodes(sort, 800, 700, node);
				 node.option = 0;
				 node.repaint();
		 }
 }
 
