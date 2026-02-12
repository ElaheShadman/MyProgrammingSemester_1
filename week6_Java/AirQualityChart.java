// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import javax.swing.*;
import java.util.*;

public class AirQualityChart {

    public static void main(String[] args) {
        String[] Days = {"Mon" , "Tue", "Wed", "Tur", "Fri", "Sat", "Sun"};
        double[] pm25_values = {12.5, 15.2, 18.3, 11.4, 22.0, 25.3, 30.5};
        ArrayList<String> daysList = new ArrayList<>();
        daysList.add("Mon");
        daysList.add("Tue");
        daysList.add("Wed");
        daysList.add("Tur");
        daysList.add("Fri");
        daysList.add("Sat");
        daysList.add("Sun");

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int  i = 0; i < Days.length; i++) {
            String currentDay = Days[i];
            double currentValue = pm25_values[i];

            dataset.addValue(currentValue, "pm2.5", currentDay);
        }
        JFreeChart chart = ChartFactory.createBarChart("Weekly Air Quality Report", "Day of the Week", "pm2.5", dataset);

        ChartFrame frame = new ChartFrame("Air Quality Dashboard", chart);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

}