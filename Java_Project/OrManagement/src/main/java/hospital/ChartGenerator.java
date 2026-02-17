package hospital;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class ChartGenerator {

    private static boolean hasEnough(List<Surgery> logs) {
        return logs != null && !logs.isEmpty();
    }

    public static JFreeChart createOrUsageByDept(List<Surgery> logs) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (!hasEnough(logs)) {
            dataset.addValue(1, "Procedures", "No Data");
            return ChartFactory.createBarChart("No Data", "Dept", "Count", dataset);
        }

        Map<String, Long> counts = Analytics.countByDept(logs);
        counts.forEach((dept, count) -> dataset.addValue(count, "Procedures", dept));

        return ChartFactory.createBarChart(
                "OR Usage by Specialty",
                "Department",
                "Number of Procedures",
                dataset
        );
    }

    public static JFreeChart createDailyVolumeChart(List<Surgery> logs) {
        XYSeries series = new XYSeries("Daily Volume");

        if (!hasEnough(logs)) {
            series.add(1, 0);
        } else {
            for (int i = 0; i < logs.size(); i++) {
                series.add(i + 1, 1);
            }
        }

        XYSeriesCollection coll = new XYSeriesCollection(series);

        return ChartFactory.createXYLineChart(
                "Surgery Volume (Index-based)",
                "Index",
                "Count",
                coll
        );
    }

    public static JFreeChart createDurationVsRecoveryScatter(List<Surgery> logs) {
        XYSeries series = new XYSeries("Duration vs Recovery");

        if (!hasEnough(logs)) {
            series.add(0, 0);
        } else {
            for (Surgery s : logs) {
                series.add(s.duration, s.recoveryDays);
            }
        }

        XYSeriesCollection coll = new XYSeriesCollection(series);

        return ChartFactory.createScatterPlot(
                "Duration vs Recovery",
                "Duration (mins)",
                "Recovery (days)",
                coll
        );
    }

    public static JFreeChart createProcedureDistributionPie(List<Surgery> logs) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        if (!hasEnough(logs)) {
            dataset.setValue("No Data", 1);
        } else {
            Map<String, Long> counts = Analytics.countByProcedure(logs);
            counts.forEach(dataset::setValue);
        }

        return ChartFactory.createPieChart(
                "Procedure Distribution",
                dataset,
                true,
                true,
                false
        );
    }

    public static JFreeChart createOrOccupancyHeatmap(List<Surgery> logs) {
        DefaultXYZDataset dataset = new DefaultXYZDataset();

        if (!hasEnough(logs)) {
            double[][] empty = new double[][]{{0}, {0}, {0}};
            dataset.addSeries("Empty", empty);
        } else {
            Map<String, Double> avg = Analytics.avgDurationByDept(logs);
            int n = avg.size();

            double[] x = new double[n];
            double[] y = new double[n];
            double[] z = new double[n];

            int i = 0;
            for (Map.Entry<String, Double> e : avg.entrySet()) {
                x[i] = i;
                y[i] = 0;
                z[i] = e.getValue();
                i++;
            }

            dataset.addSeries("OR Occupancy", new double[][]{x, y, z});
        }

        NumberAxis xAxis = new NumberAxis("Department Index");
        NumberAxis yAxis = new NumberAxis("Row");

        XYBlockRenderer renderer = new XYBlockRenderer();
        LookupPaintScale scale = new LookupPaintScale(0, 300, Color.BLUE);
        scale.add(100, Color.GREEN);
        scale.add(200, Color.ORANGE);
        scale.add(300, Color.RED);

        renderer.setPaintScale(scale);
        renderer.setBlockWidth(1.0);
        renderer.setBlockHeight(1.0);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

        return new JFreeChart("OR Occupancy Heatmap", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
    }
}
