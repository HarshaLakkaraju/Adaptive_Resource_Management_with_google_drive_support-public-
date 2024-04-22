/*
Summary:
The Simulator class extends the JComponent class and is responsible for rendering the simulation of a network of cameras.
It contains an ArrayList of Camera objects and various properties related to rendering, such as line styles and colors.

The class provides the following methods:

1. setSender(Camera src, int destx, int desty): Sets the source camera and destination coordinates for rendering.
2. Simulator(): Constructor for the Simulator class, which sets the background color.
3. getList(): Returns the list of cameras in the simulator.
4. removeAll(): Removes all cameras from the simulator and repaints the component.
5. paintComponent(Graphics g1): Overrides the paintComponent method to render the simulator. It draws rectangles representing different cloud levels and renders the cameras based on the option value. If the option is 1, it also draws a dashed line from the source camera to the destination coordinates.

The purpose of this class is to provide a visual representation of the camera network simulation, allowing for rendering of cameras and their connections based on the specified options.
*/
package com;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

public class Simulator extends JComponent {
    public int option = 0; // Variable to control the rendering option
    public ArrayList<Camera> cameras = new ArrayList<Camera>(); // List of cameras to be simulated
    float dash1[] = {10.0f}; // Dash pattern for dashed line
    BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f); // Dashed line style
    BasicStroke rect = new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[]{2f}, 0f); // Rectangle line style
    Dimension dim; // Dimension of the component
    Camera src; // Source camera for rendering
    int destx, desty; // Destination coordinates for rendering

    /**
     * Sets the source camera and destination coordinates for rendering.
     *
     * @param src   the source camera
     * @param destx the x-coordinate of the destination
     * @param desty the y-coordinate of the destination
     */
    public void setSender(Camera src, int destx, int desty) {
        this.src = src;
        this.destx = destx;
        this.desty = desty;
    }

    /**
     * Constructor for the Simulator class.
     */
    public Simulator() {
        super.setBackground(new Color(255, 0, 255));
        this.setBackground(new Color(255, 0, 255));
    }

    /**
     * Returns the list of cameras in the simulator.
     *
     * @return the list of cameras
     */
    public ArrayList<Camera> getList() {
        return cameras;
    }

    /**
     * Removes all cameras from the simulator and repaints the component.
     */
    public void removeAll() {
        option = 0;
        cameras.clear();
        repaint();
    }

    /**
     * Overrides the paintComponent method to render the simulator.
     *
     * @param g1 the Graphics object to render on
     */
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        GradientPaint gradient = new GradientPaint(0, 0, Color.green, 175, 175, Color.yellow, true); // Gradient paint for background
        Graphics2D g = (Graphics2D) g1;
        g.setPaint(gradient);
        g.setStroke(rect); // Set the stroke for drawing rectangles

        // Draw and label the rectangles representing different cloud levels
        Rectangle2D rectangle = new Rectangle2D.Double(10, 30, 200, 40);
        g.setStroke(rect);
        g.draw(rectangle);
        g.drawString("High Cloud", 60, 60);

        rectangle = new Rectangle2D.Double(310, 30, 200, 40);
        g.setStroke(rect);
        g.draw(rectangle);
        g.drawString("Medium Cloud", 380, 60);

        rectangle = new Rectangle2D.Double(610, 30, 200, 40);
        g.setStroke(rect);
        g.draw(rectangle);
        g.drawString("Low Cloud", 670, 60);

        if (option == 0) {
            // Render the cameras when option is 0
            for (int i = 0; i < cameras.size(); i++) {
                Camera c = cameras.get(i);
                c.draw(g, "fill"); // Draw the camera shape
                g.drawString(c.getCamera(), c.x + 10, c.y + 50); // Draw the camera label
            }
        }

        if (option == 1) {
            // Render the cameras and a dashed line from the source camera to the destination when option is 1
            for (int i = 0; i < cameras.size(); i++) {
                Camera c = cameras.get(i);
                c.draw(g, "fill"); // Draw the camera shape
                g.drawString(c.getCamera(), c.x + 10, c.y + 50); // Draw the camera label
            }
            g.setStroke(dashed); // Set the stroke for drawing dashed line
            g.drawLine(src.x + 10, src.y + 10, destx + 20, desty); // Draw the dashed line
        }
    }
}