package com;
import java.io.File;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.core.MatOfRect; 
import org.opencv.core.Point; 
import org.opencv.core.Rect; 
import org.opencv.core.Scalar; 
import org.opencv.imgproc.Imgproc; 
import org.opencv.objdetect.CascadeClassifier; 
public class DetectPerson{
	static CascadeClassifier classifier;

public static void loadClassifier() {
	try{
		classifier = new CascadeClassifier("haarcascade_frontalface_alt.xml");
	}catch(Exception e){
		e.printStackTrace();
	}
}
public static boolean detectFaces(File file){
	boolean flag = false;
	try{
		MatOfRect faceDetections = new MatOfRect();
		if(!file.getName().equals("Thumbs.db")){
			Mat src = Highgui.imread(file.getPath());
			classifier.detectMultiScale(src, faceDetections);  
			if(faceDetections.toArray().length > 0) {
				flag = true;
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return flag;
}

}