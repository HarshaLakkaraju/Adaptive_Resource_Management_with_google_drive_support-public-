package com;
import java.util.ArrayList;
public class SendData extends Thread{
	Simulator sim;
	
public SendData(Simulator sim){
	this.sim = sim;
	start();
}
public void run(){
	try{
		for(int i=0;i<sim.cameras.size();i++){
			Camera c = sim.cameras.get(i);
			double distance1 = CameraPlacement.getDistance(10,30,c.getX(),c.getY());
			double distance2 = CameraPlacement.getDistance(310,30,c.getX(),c.getY());
			double distance3 = CameraPlacement.getDistance(610,30,c.getX(),c.getY());
			if (distance1 <= distance2 && distance1 <= distance3) {
				  c.cloudX(10);
			} else if (distance2 <= distance3 && distance2 <= distance1) {
				c.cloudX(310);
			} else {
				c.cloudX(610);
			}
		}
		while(true){
		for(int i=0;i<sim.cameras.size();i++){
			Camera c = sim.cameras.get(i);
			for(int k=0;k<5;k++){
				sim.setSender(c,c.getCloudX(),30);
				sim.option=1;
				sim.repaint();
				sleep(100);
				sim.option=0;
				sim.repaint();
				sleep(50);
			}
		}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}