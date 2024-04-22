// Summary:
// The Chart class extends ApplicationFrame and is responsible for creating and displaying a bar chart. 
// It includes methods to create datasets from existing and proposed technique costs, create a bar chart with customizations, and handle window closing events.
// The class also includes a method to create a demo panel containing the chart.


package com;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;

public class Chart extends ApplicationFrame {
    // Title of the chart
    static String title;

    // Constructor that creates a Chart object with a given title
    public Chart(String paramString) {
        // Initialize the ApplicationFrame with the given title
        super(paramString);
        // Create a demo panel with the chart
        JPanel localJPanel = createDemoPanel();
        // Set the preferred size of the panel
        localJPanel.setPreferredSize(new Dimension(800, 370));
        // Create a scroll pane containing the demo panel
        JScrollPane jsp = new JScrollPane(localJPanel);
        // Set the content pane of the frame to the demo panel
        setContentPane(localJPanel);
    }

    // Method to create a dataset for the chart
    private static CategoryDataset createDataset() {
        // Create a DefaultCategoryDataset for the chart
        DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
        // Add data to the dataset from the Main class
        localDefaultCategoryDataset.addValue(Main.existing_cost, "Existing Technique Cost", "Existing Technique Cost");
        localDefaultCategoryDataset.addValue(Main.propose_cost, "ARMVAC Technique Cost", "ARMVAC Technique Cost");
        // Return the populated dataset
        return localDefaultCategoryDataset;
    }

    // Method to handle window closing events
    public void windowClosing(WindowEvent we) {
        // Set the visibility of the frame to false
        this.setVisible(false);
    }

    // Method to create a chart with a given dataset
    private static JFreeChart createChart(CategoryDataset paramCategoryDataset) {
        // Create a bar chart with the given dataset and parameters
        JFreeChart localJFreeChart = ChartFactory.createBarChart(title, "Technique Name", "Cloud Cost", paramCategoryDataset, PlotOrientation.VERTICAL, true, true, false);
        // Get the plot from the chart
        CategoryPlot localCategoryPlot = (CategoryPlot) localJFreeChart.getPlot();
        // Enable gridlines and range crosshair visibility
        localCategoryPlot.setDomainGridlinesVisible(true);
        localCategoryPlot.setRangeCrosshairVisible(true);
        localCategoryPlot.setRangeCrosshairPaint(Color.blue);
        // Customize the range axis
        NumberAxis localNumberAxis = (NumberAxis) localCategoryPlot.getRangeAxis();
        localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // Customize the bar renderer
        BarRenderer localBarRenderer = (BarRenderer) localCategoryPlot.getRenderer();
        localBarRenderer.setDrawBarOutline(false);
        // Create gradient paints for different series
        GradientPaint localGradientPaint1 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint localGradientPaint2 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint localGradientPaint3 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
        // Set the gradient paints to the renderer
        localBarRenderer.setSeriesPaint(0, localGradientPaint1);
        localBarRenderer.setSeriesPaint(1, localGradientPaint2);
        localBarRenderer.setSeriesPaint(2, localGradientPaint3);
        // Set a tooltip generator for the renderer
        localBarRenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
        // Customize the category axis
        CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
        localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.5235987755982988D));
        // Return the configured chart
        return localJFreeChart;
    }

    // Method to create a demo panel with the chart
    public static JPanel createDemoPanel() {
        // Create the chart using the dataset
        JFreeChart localJFreeChart = createChart(createDataset());
        // Return a ChartPanel containing the chart
        return new ChartPanel(localJFreeChart);
    }
}

