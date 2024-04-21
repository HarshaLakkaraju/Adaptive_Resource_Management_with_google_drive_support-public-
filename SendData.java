package com;
import java.util.ArrayList;

public class SendData extends Thread {
    Simulator sim; // Reference to the Simulator object

    // Constructor that takes a Simulator object and starts the thread
    public SendData(Simulator sim) {
        this.sim = sim;
        start(); // Start the thread
    }

    public void run() {
        try {
            // Iterate through all cameras in the simulator
            for (int i = 0; i < sim.cameras.size(); i++) {
                Camera c = sim.cameras.get(i); // Get the current camera object
                double distance1 = CameraPlacement.getDistance(10, 30, c.getX(), c.getY()); // Calculate distance from camera to (10, 30)
                double distance2 = CameraPlacement.getDistance(310, 30, c.getX(), c.getY()); // Calculate distance from camera to (310, 30)
                double distance3 = CameraPlacement.getDistance(610, 30, c.getX(), c.getY()); // Calculate distance from camera to (610, 30)

                // Assign the closest point (10, 30), (310, 30), or (610, 30) to the camera's cloudX
                if (distance1 <= distance2 && distance1 <= distance3) {
                    c.cloudX(10);
                } else if (distance2 <= distance3 && distance2 <= distance1) {
                    c.cloudX(310);
                } else {
                    c.cloudX(610);
                }
            }

            while (true) { // Infinite loop
                // Iterate through all cameras in the simulator
                for (int i = 0; i < sim.cameras.size(); i++) {
                    Camera c = sim.cameras.get(i); // Get the current camera object

                    // Repeat the following 5 times
                    for (int k = 0; k < 5; k++) {
                        sim.setSender(c, c.getCloudX(), 30); // Set the sender for the camera
                        sim.option = 1; // Set option to 1
                        sim.repaint(); // Repaint the simulator
                        sleep(100); // Sleep for 100 milliseconds
                        sim.option = 0; // Set option to 0
                        sim.repaint(); // Repaint the simulator
                        sleep(50); // Sleep for 50 milliseconds
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace in case of an exception
        }
    }
}

/*
Summary:
The SendData class is a thread that handles sending data from cameras in a Simulator object. It first iterates through all the cameras and assigns the closest fixed point (10, 30), (310, 30), or (610, 30) to the camera's cloudX value based on the distance calculations. Then, it enters an infinite loop where it repeatedly iterates through all the cameras, sets a "sender" for each camera using the setSender method of the Simulator object, and updates the simulator's display by setting the option value, repainting, and sleeping for a specified duration. This process is repeated 5 times for each camera in the loop. The purpose of this code is not entirely clear without more context, but it seems to be related to simulating data sending or processing from cameras based on their positions relative to fixed points.
*/