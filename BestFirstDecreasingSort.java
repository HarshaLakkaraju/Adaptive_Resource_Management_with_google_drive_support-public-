package com;

import java.util.Comparator;

public class BestFirstDecreasingSort implements Comparator<BestFirstDecreasingSort> {
    // Member variables
    String camera;
    String cloud;
    String frame;
    double cost;
    double distance;

    // Method to set the camera string
    public void setCamera(String camera) {
        this.camera = camera;
    }

    // Method to get the camera string
    public String getCamera() {
        return camera;
    }

    // Method to set the cloud string
    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    // Method to get the cloud string
    public String getCloud() {
        return cloud;
    }

    // Method to set the frame string
    public void setFrame(String frame) {
        this.frame = frame;
    }

    // Method to get the frame string
    public String getFrame() {
        return frame;
    }

    // Method to set the cost value
    public void setCost(double cost) {
        this.cost = cost;
    }

    // Method to get the cost value
    public double getCost() {
        return cost;
    }

    // Method to set the distance value
    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Method to get the distance value
    public double getDistance() {
        return distance;
    }

    // Method to compare two BestFirstDecreasingSort objects based on their distance values
    @Override
    public int compare(BestFirstDecreasingSort s1, BestFirstDecreasingSort s2) {
        // Retrieve distance values of the two objects
        double sm1 = s1.getDistance();
        double sm2 = s2.getDistance();

        // Compare the distance values and return an integer based on the comparison
        if (sm1 == sm2) {
            // Return 0 if the distances are equal
            return 0;
        } else if (sm1 > sm2) {
            // Return 1 if the first distance is greater than the second
            return 1;
        } else {
            // Return -1 if the first distance is less than the second
            return -1;
        }
    }
}
