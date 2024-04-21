package com;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
public class Simulator extends JComponent{
	public int option=0;
	public ArrayList<Camera> cameras = new ArrayList<Camera>();
	float dash1[] = {10.0f};
	BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
	BasicStroke rect=new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1f,new float[] {2f},0f);
	Dimension dim;
	Camera src;
	int destx,desty;
	
public void setSender(Camera src,int destx,int desty){
	this.src = src;
	this.destx = destx;
	this.desty = desty;
}
public Simulator() {
	super.setBackground(new Color(255,0,255));
	this.setBackground(new Color(255,0,255));
}
public ArrayList<Camera> getList(){
	return cameras;
}
public void removeAll(){
	option=0;
	cameras.clear();
	repaint();
}

public void paintComponent(Graphics g1){
	super.paintComponent(g1);
	GradientPaint gradient = new GradientPaint(0, 0, Color.green, 175, 175, Color.yellow,true); 
	Graphics2D g = (Graphics2D)g1;
	g.setPaint(gradient);
	g.setStroke(rect);

	Rectangle2D rectangle = new Rectangle2D.Double(10,30,200,40);
	g.setStroke(rect);
	g.draw(rectangle);
	g.drawString("High Cloud",60,60);

	rectangle = new Rectangle2D.Double(310,30,200,40);
	g.setStroke(rect);
	g.draw(rectangle);
	g.drawString("Medium Cloud",380,60);

	rectangle = new Rectangle2D.Double(610,30,200,40);
	g.setStroke(rect);
	g.draw(rectangle);
	g.drawString("Low Cloud",670,60);

	if(option == 0){
		for(int i=0;i<cameras.size();i++){
			Camera c = cameras.get(i);
			c.draw(g,"fill");
			g.drawString(c.getCamera(),c.x+10,c.y+50);
		}
	}
	if(option == 1){
		for(int i=0;i<cameras.size();i++){
			Camera c = cameras.get(i);
			c.draw(g,"fill");
			g.drawString(c.getCamera(),c.x+10,c.y+50);
		}
		g.setStroke(dashed);
		g.drawLine(src.x+10,src.y+10,destx+20,desty);
	}
}	
}