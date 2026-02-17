package hospital;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.LookupPaintScale;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class ChartGenerator {

    public static JFreeChart createOrUsageByDept(List<Surgery> logs) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
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
        for (int i = 0; i < logs.size(); i++) {
            series.add(i + 1, 1); // treat each surgery as one case per "day index"
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
        for (Surgery s : logs) {
            series.add(s.duration, s.recoveryDays);
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
        Map<String, Long> counts = Analytics.countByProcedure(logs);
        counts.forEach(dataset::setValue);

        return ChartFactory.createPieChart(
                "Procedure Distribution",
                dataset,
                true,
                true,
                false
        );
    }

    public static JFreeChart createOrOccupancyHeatmap(List<Surgery> logs) {
        // Simple synthetic heatmap: dept index vs duration bucket
        Map<String, Long> deptCounts = Analytics.countByDept(logs);
        String[] depts = deptCounts.keySet().toArray(new String[0]);

        int n = depts.length;
        double[] x = new double[n];
        double[] y = new double[n];
        double[] z = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = i;
            y[i] = 0;
            double avg = Analytics.avgDurationByDept(logs).getOrDefault(depts[i], 0.0);
            z[i] = avg;
        }

        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[][] data = new double[][]{x, y, z};
        dataset.addSeries("OR Occupancy", data);

        NumberAxis xAxis = new NumberAxis("Department Index");
        NumberAxis yAxis = new NumberAxis("Row");
        XYBlockRenderer renderer = new XYBlockRenderer();

        double min = 0;
        double max = 0;
        for (double v : z) {
            if (v > max) max = v;
        }

        LookupPaintScale scale = new LookupPaintScale(min, max, Color.BLUE);
        scale.add(min, Color.BLUE);
        scale.add(max * 0.5, Color.ORANGE);
        scale.add(max, Color.RED);

        renderer.setPaintScale(scale);
        renderer.setBlockWidth(1.0);
        renderer.setBlockHeight(1.0);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        JFreeChart chart = new JFreeChart("OR Occupancy Heatmap", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        return chart;
    }
}
