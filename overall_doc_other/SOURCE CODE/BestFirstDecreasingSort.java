









package com;
import java.util.Comparator;
public class BestFirstDecreasingSort implements Comparator<BestFirstDecreasingSort>{
	String camera;
	String cloud;
	String frame;
	double cost;
	double distance;

public void setCamera(String camera){
	this.camera = camera;
}
public String getCamera(){
	return camera;
}

public void setCloud(String cloud){
	this.cloud = cloud;
}
public String getCloud(){
	return cloud;
}
public void setFrame(String frame){
	this.frame = frame;
}
public String getFrame(){
	return frame;
}


public void setCost(double cost){
	this.cost = cost;
}
public double getCost(){
	return cost;
}

public void setDistance(double distance){
	this.distance = distance;
}
public double getDistance(){
	return distance;
}

public int compare(BestFirstDecreasingSort s1,BestFirstDecreasingSort s2){
	double sm1 = s1.getDistance();
    double sm2 = s2.getDistance();
	if (sm1 == sm2)
		return 0;
    else if (sm1 > sm2)
    	return 1;
    else
		return -1;
}
}