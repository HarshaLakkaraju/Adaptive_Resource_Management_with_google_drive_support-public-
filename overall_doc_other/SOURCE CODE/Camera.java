package com;
import java.awt.Point;
import java.awt.Graphics;
import javax.crypto.SecretKey;
import java.awt.Color;
public class Camera{
	int x;           
    int y;           
    int dia;    
    String camera;
	int xx;

public void cloudX(int xx){
	this.xx = xx;
}
public int getCloudX(){
	return xx;
}

public Camera(Point center, int radius){
	x   = center.x;
    y   = center.y;
    dia = radius;
}
public void setX(int x){
	this.x=x;
}
public int getX(){
	return x;
}
public void setY(int y){
	this.y=y;
}
public int getY(){
	return y;
}
public void fillSource(Graphics g){
	g.setColor(Color.blue);
}
public void fillDest(Graphics g){
	g.setColor(Color.yellow);
}
public void draw(Graphics g,String option){
	if(option.equals("fill"))
		g.fillOval(x, y, dia,dia);
	if(option.equals("empty"))
		g.drawOval(x, y, dia,dia);
}
public void setCamera(String camera){
	this.camera = camera;
}
public String getCamera(){
	return camera;
}
}

