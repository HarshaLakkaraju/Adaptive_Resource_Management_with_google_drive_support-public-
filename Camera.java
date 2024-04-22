// Summary: 
// The Camera class represents a camera in a graphical application. It stores the camera's center point coordinates, diameter, and name or identifier. 
// The class provides methods to set and retrieve these properties and includes methods to draw the camera's graphical representation using a Graphics object. 
// The `draw` method allows drawing either a filled or an empty oval shape, depending on the provided option.
package com;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public class Camera {
    // Coordinates of the center point of the camera
    int x;
    int y;
    
    // Diameter of the camera's graphical representation
    int dia;
    
    // Camera name or identifier
    String camera;
    
    // Additional integer variable (purpose is unclear from code)
    int xx;

    // Constructor to initialize the Camera object with a center point and a radius (diameter)
    public Camera(Point center, int radius) {
        // Assign the x and y coordinates of the center point
        x = center.x;
        y = center.y;
        // Set the diameter
        dia = radius;
    }

    // Method to set the x-coordinate of the camera
    public void setX(int x) {
        this.x = x;
    }

    // Method to get the x-coordinate of the camera
    public int getX() {
        return x;
    }

    // Method to set the y-coordinate of the camera
    public void setY(int y) {
        this.y = y;
    }

    // Method to get the y-coordinate of the camera
    public int getY() {
        return y;
    }

    // Method to set the additional integer variable
    public void cloudX(int xx) {
        this.xx = xx;
    }

    // Method to get the additional integer variable
    public int getCloudX() {
        return xx;
    }

    // Method to set the camera name or identifier
    public void setCamera(String camera) {
        this.camera = camera;
    }

    // Method to get the camera name or identifier
    public String getCamera() {
        return camera;
    }

    // Method to fill the graphical representation of the camera as a source (blue color)
    public void fillSource(Graphics g) {
        // Set the color to blue
        g.setColor(Color.blue);
    }

    // Method to fill the graphical representation of the camera as a destination (yellow color)
    public void fillDest(Graphics g) {
        // Set the color to yellow
        g.setColor(Color.yellow);
    }

    // Method to draw the camera on the graphics context
    public void draw(Graphics g, String option) {
        // If the option is to fill the shape
        if (option.equals("fill")) {
            // Fill the oval shape with the set color
            g.fillOval(x, y, dia, dia);
        }
        // If the option is to draw the shape outline
        if (option.equals("empty")) {
            // Draw the oval shape outline with the set color
            g.drawOval(x, y, dia, dia);
        }
    }
}
