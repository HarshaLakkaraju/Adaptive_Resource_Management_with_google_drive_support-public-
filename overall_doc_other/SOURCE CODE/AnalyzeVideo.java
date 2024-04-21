










package com;
import org.opencv.core.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.opencv.highgui.VideoCapture;
import javax.swing.ImageIcon;
public class AnalyzeVideo extends JPanel{
	static int count = 0;
	JLabel videolabel;
public void playVideo(String file) {
	Mat frame = new Mat();
	VideoCapture camera = new VideoCapture(file);
    while (true) {
		if (camera.read(frame)) {
            ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
            videolabel.setIcon(image);
            videolabel.repaint();
        }
    }
}

public AnalyzeVideo() {
	videolabel = new JLabel();
	add(videolabel);
}

public static BufferedImage Mat2BufferedImage(Mat m) {
    //Method converts a Mat to a Buffered Image
    int type = BufferedImage.TYPE_BYTE_GRAY;
     if ( m.channels() > 1 ) {
         type = BufferedImage.TYPE_3BYTE_BGR;
     }
     int bufferSize = m.channels()*m.cols()*m.rows();
     byte [] b = new byte[bufferSize];
     m.get(0,0,b); // get all the pixels
     BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
     final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
     System.arraycopy(b, 0, targetPixels, 0, b.length);  
	 try{
			ImageIO.write(image,"png",new File("samples/0.png"));
			boolean flag = DetectPerson.detectFaces(new File("samples/0.png"));
			if(flag) {
				ImageIO.write(image,"png",new File("frames/"+count+".png"));
				ShowNetwork.l1.setText("person detected at frame : "+count);
				count = count + 1;
			} else {
				ShowNetwork.l1.setText("No person detected at frame : "+count);
			}
	 }catch(Exception e){
		 e.printStackTrace();
	 }
     return image;
    }
}