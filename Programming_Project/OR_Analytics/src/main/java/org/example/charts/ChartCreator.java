package org.example.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class ChartCreator {

    public static JPanel createDailyVolumeChart(Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String day : data.keySet()) {
            dataset.addValue(data.get(day), "Surgeries", day);
        }
        JFreeChart chart = ChartFactory.createLineChart(
                "Daily Surgery Volume",
                "Date",
                "Count",
                dataset
        );
        return new ChartPanel(chart);
    }

    public static JPanel createSpecialtyUsageChart(Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String spec : data.keySet()) {
            dataset.addValue(data.get(spec), "Minutes", spec);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "OR Usage by Specialty",
                "Specialty",
                "Minutes",
                dataset
        );
        return new ChartPanel(chart);
    }

    public static JPanel createSurgeonComparisonChart(Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String surgeon : data.keySet()) {
            dataset.addValue(data.get(surgeon), "Surgeries", surgeon);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Surgeries by Surgeon",
                "Surgeon",
                "Count",
                dataset
        );
        return new ChartPanel(chart);
    }

    public static JPanel createProcedureRecoveryChart(Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String proc : data.keySet()) {
            dataset.addValue(data.get(proc), "Avg Recovery (min)", proc);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Procedure Type vs Recovery Time",
                "Procedure",
                "Avg Recovery (min)",
                dataset
        );
        return new ChartPanel(chart);
    }

    public static JPanel createDurationOccupancyChart(Map<Integer, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Integer bucket : data.keySet()) {
            String label = bucket + "-" + (bucket + 29) + " min";
            dataset.addValue(data.get(bucket), "Surgeries", label);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Duration vs OR Occupancy (Buckets)",
                "Duration Bucket",
                "Count",
                dataset
        );
        return new ChartPanel(chart);
    }

    public static JPanel createHourlyHeatmapChart(Map<Integer, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Integer hour : data.keySet()) {
            String label = hour + ":00";
            dataset.addValue(data.get(hour), "Surgeries", label);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "OR Occupancy by Hour (Heatmap-like)",
                "Hour",
                "Surgeries",
                dataset
        );
        return new ChartPanel(chart);
    }

    public static JPanel createMovingAverageChart(Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String day : data.keySet()) {
            dataset.addValue(data.get(day), "Moving Avg", day);
        }
        JFreeChart chart = ChartFactory.createLineChart(
                "Moving Average of Daily Volume",
                "Date",
                "Surgeries",
                dataset
        );
        return new ChartPanel(chart);
    }
}
