// AnalyzeVideo class processes video frames and detects people in the frames.
// Constructor initializes videolabel for displaying video frames.
// playVideo method reads video frames, converts them to images, and displays them in videolabel.
// Mat2BufferedImage method converts Mat to BufferedImage, checks for people, and saves frames if a person is detected.

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

public class AnalyzeVideo extends JPanel {
    // Static variable to keep track of the number of frames processed
    static int count = 0;
    
    // Label to display the video frames
    JLabel videolabel;

    // Constructor for the AnalyzeVideo class
    public AnalyzeVideo() {
        // Initialize the JLabel for video display
        videolabel = new JLabel();
        // Add the JLabel to the JPanel
        add(videolabel);
    }

    // Method to play video from a given file path
    public void playVideo(String file) {
        // Create a Mat object to store video frames
        Mat frame = new Mat();
        // Initialize VideoCapture with the given file path
        VideoCapture camera = new VideoCapture(file);
        
        // Loop to continuously read frames from the video
        while (true) {
            // Read a frame from the video
            if (camera.read(frame)) {
                // Convert the Mat object to BufferedImage
                ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
                // Set the frame as the icon of the JLabel
                videolabel.setIcon(image);
                // Repaint the JLabel to display the new frame
                videolabel.repaint();
            }
        }
    }

    // Method to convert a Mat object to a BufferedImage
    public static BufferedImage Mat2BufferedImage(Mat m) {
        // Determine the type of the BufferedImage (grayscale or color)
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        // Calculate the buffer size
        int bufferSize = m.channels() * m.cols() * m.rows();
        // Create a byte array to hold pixel data
        byte[] b = new byte[bufferSize];
        // Retrieve pixel data from the Mat object
        m.get(0, 0, b);
        
        // Create a BufferedImage with the appropriate type and dimensions
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        // Get the data buffer of the BufferedImage
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        // Copy pixel data from the Mat object to the BufferedImage
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        
        try {
            // Save the current frame as an image file
            ImageIO.write(image, "png", new File("samples/0.png"));
            
            // Detect if a person is present in the frame using a custom function
            boolean flag = DetectPerson.detectFaces(new File("samples/0.png"));
            
            // Check the detection result
            if (flag) {
                // Save the frame if a person is detected
                ImageIO.write(image, "png", new File("frames/" + count + ".png"));
                // Update a JLabel with detection information
                ShowNetwork.l1.setText("Person detected at frame: " + count);
                // Increment the frame count
                count += 1;
            } else {
                // Update the JLabel if no person is detected
                ShowNetwork.l1.setText("No person detected at frame: " + count);
            }
        } catch (Exception e) {
            // Print stack trace in case of exceptions
            e.printStackTrace();
        }
        
        // Return the converted BufferedImage
        return image;
    }
}
