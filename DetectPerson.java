// Summary:
// The DetectPerson class provides methods for loading a face detection classifier and detecting faces in image files. 
// It uses a CascadeClassifier to detect faces in images and returns a boolean value indicating whether faces were found in the image.

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

public class DetectPerson {
    // The classifier used to detect faces
    static CascadeClassifier classifier;

    // Method to load the face detection classifier
    public static void loadClassifier() {
        try {
            // Load the classifier from a file
            classifier = new CascadeClassifier("haarcascade_frontalface_alt.xml");
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }

    // Method to detect faces in an image file
    public static boolean detectFaces(File file) {
        boolean flag = false;
        try {
            // Create an object to store the detected faces
            MatOfRect faceDetections = new MatOfRect();
            // Exclude system files (e.g. "Thumbs.db") from processing
            if (!file.getName().equals("Thumbs.db")) {
                // Read the image from the file
                Mat src = Highgui.imread(file.getPath());
                // Use the classifier to detect faces in the image
                classifier.detectMultiScale(src, faceDetections);
                // Check if any faces were detected
                if (faceDetections.toArray().length > 0) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
        // Return whether faces were detected
        return flag;
    }
}

