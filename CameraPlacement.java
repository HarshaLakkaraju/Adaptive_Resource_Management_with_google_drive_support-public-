// Summary:
// The CameraPlacement class contains methods to randomly place cameras on a grid while ensuring a minimum distance between cameras. 
// It uses the Simulator class to manage the placement and storage of cameras.
// The class includes methods to generate random coordinates within a specified range, check distances from existing cameras, and calculate the Euclidean distance between points.

package com;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

public class CameraPlacement {
    // Diameter of the cameras
    static int size = 40;

    // Simulator object for managing camera placement
    static Simulator g;

    // Method to randomly place cameras with the given list, width, height, and a simulator
    public static void randomNodes(ArrayList<BestFirstDecreasingSort> sort, int width, int height, Simulator nodes) {
        // Set the simulator object
        g = nodes;
        // Call the overloaded method with the current time as the seed for randomness
        randomNodes(sort, width, height, System.currentTimeMillis());
    }

    // Overloaded method to randomly place cameras with the given list, width, height, and seed for randomness
    public static void randomNodes(ArrayList<BestFirstDecreasingSort> sort, int width, int height, long seed) {
        // Initialize x and y coordinates
        int x = getXPosition(10, 700);
        int y = getYPosition(150, 550);

        // Iterate through the list of BestFirstDecreasingSort objects
        for (int i = 0; i < sort.size(); i++) {
            BestFirstDecreasingSort bf = sort.get(i);

            // Check the distance from existing cameras
            boolean flag = checkDistance(x, y);
            
            // If the distance is acceptable, place the camera
            if (!flag) {
                // Create a new Camera object with the specified center point and diameter
                Camera c = new Camera(new Point(x, y), size);
                // Set the camera identifier
                c.setCamera(bf.getCamera());
                // Add the camera to the simulator
                g.cameras.add(c);
            } else {
                // If the distance is not acceptable, retry by decrementing the loop counter
                i -= 1;
            }

            // Generate new x and y coordinates for the next camera
            x = getXPosition(10, 700);
            y = getYPosition(150, 550);
        }
    }

    // Method to generate a random x-coordinate within a specified range
    public static int getXPosition(int start, int end) {
        // Create a Random object
        Random rn = new Random();
        // Calculate the range
        int range = end - start + 1;
        // Return a random integer within the specified range
        return rn.nextInt(range) + start;
    }

    // Method to generate a random y-coordinate within a specified range
    public static int getYPosition(int start, int end) {
        // Create a Random object
        Random rn = new Random();
        // Calculate the range
        int range = end - start + 1;
        // Return a random integer within the specified range
        return rn.nextInt(range) + start;
    }

    // Method to check if a point (x, y) is within a certain distance from any existing camera
    public static boolean checkDistance(int x, int y) {
        // Initialize the flag as false
        boolean flag = false;
        // Iterate through the list of existing cameras in the simulator
        for (int i = 0; i < g.cameras.size(); i++) {
            // Retrieve the current camera
            Camera node = g.cameras.get(i);
            // Calculate the distance between the point and the current camera
            double d = getDistance(x, y, node.x, node.y);
            
            // If the distance is less than 50, set the flag to true and break the loop
            if (d < 50) {
                flag = true;
                break;
            }
        }
        // Return the flag indicating whether the distance is too close to any camera
        return flag;
    }

    // Method to calculate the Euclidean distance between two points (n1, n2) and (n2x, n2y)
    public static double getDistance(int n1x, int n1y, int n2x, int n2y) {
        // Calculate the difference in x-coordinates squared
        int dx = (n1x - n2x) * (n1x - n2x);
        
        // Calculate the difference in y-coordinates squared
        int dy = (n1y - n2y) * (n1y - n2y);
        
        // Calculate the sum of the squared differences
        int total = dx + dy;
        
        // Return the square root of the total, representing the Euclidean distance
        return Math.sqrt(total);
    }
}

