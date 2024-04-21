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

public class ShowNetwork extends JFrame{
	Simulator node;
	JPanel p1,p2;
	int left,top;
	Font f1;
	JButton b1,b2,b3,b4;
	ArrayList<BestFirstDecreasingSort> sort;
	AnalyzeVideo an;
	String file;
	static JLabel l1;
public ShowNetwork(ArrayList<BestFirstDecreasingSort> s,String f){
	super("Adaptive Resource Management");
	sort = s;
	file = f;

	f1 = new Font("Courier New",Font.BOLD,14);
	node = new Simulator();
	p1 = new JPanel();
	p1.setLayout(new BorderLayout());
	p1.add(node,BorderLayout.CENTER);
	p1.setBackground(Color.black);
	getContentPane().add(p1,BorderLayout.CENTER);

	an = new AnalyzeVideo();
	getContentPane().add(an,BorderLayout.EAST);
	an.setPreferredSize(new Dimension(500,400));
		
	
	p2 = new JPanel();
	p2.setBackground(Color.white);
	p2.setLayout(new MigLayout("wrap 2")); 
	p2.setPreferredSize(new Dimension(600,100));
		
	b1 = new JButton("Start Video Analysis");
	p2.add(b1,"span,split 4");
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Runnable r = new Runnable(){
				public void run(){
					an.playVideo(file);
				}
			};
			new Thread(r).start();
		}
	});

	b2 = new JButton("Start Simulation");
	p2.add(b2);
	b2.setFont(f1);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Runnable run = new Runnable(){
					public void run(){
						new SendData(node);
					}
				};
				new Thread(run).start();

		}
	});

	b3 = new JButton("Existing & ARMVAC Cost Graph");
	p2.add(b3);
	b3.setFont(f1);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Chart chart1 = new Chart("Existing & ARMVAC Cost Graph");
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
		}
	});

	b4 = new JButton("Exit");
	p2.add(b4,"wrap");
	b4.setFont(f1);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});
	
	l1 = new JLabel();
	l1.setFont(f1);
	p2.add(l1);

	getContentPane().add(p2,BorderLayout.SOUTH);
	left = getInsets().left;
    top = getInsets().top;
	
	CameraPlacement.randomNodes(sort,800,700,node);
	node.option=0;
    node.repaint();

	
}
}