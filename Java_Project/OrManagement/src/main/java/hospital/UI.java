package hospital;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UI extends JFrame {

    private final List<Surgery> logs;
    private final ChartPanel chartPanel;

    public UI(List<Surgery> logs) {
        this.logs = logs;
        setTitle("Hospital OR Analytics Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());
        add(createRibbon(), BorderLayout.NORTH);

        JFreeChart initialChart = ChartGenerator.createOrUsageByDept(logs);
        chartPanel = new ChartPanel(initialChart);
        add(chartPanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "OR Analytics Dashboard\nElahe Shadman",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE));
        help.add(about);

        bar.add(file);
        bar.add(help);
        return bar;
    }

    private JPanel createRibbon() {
        JPanel ribbon = new JPanel(new GridLayout(1, 3));

        // Summary Tools
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary Tools"));
        JButton btnSummary = new JButton("Show Summary");
        btnSummary.addActionListener(e -> showSummaryDialog());
        summaryPanel.add(btnSummary);

        // Chart Tools
        JPanel chartTools = new JPanel();
        chartTools.setBorder(BorderFactory.createTitledBorder("Chart Tools"));
        JButton btnBar = new JButton("OR by Dept");
        JButton btnLine = new JButton("Daily Volume");
        JButton btnScatter = new JButton("Duration vs Recovery");
        JButton btnPie = new JButton("Procedure Distribution");
        JButton btnHeat = new JButton("OR Heatmap");

        btnBar.addActionListener(e -> updateChart(ChartGenerator.createOrUsageByDept(logs)));
        btnLine.addActionListener(e -> updateChart(ChartGenerator.createDailyVolumeChart(logs)));
        btnScatter.addActionListener(e -> updateChart(ChartGenerator.createDurationVsRecoveryScatter(logs)));
        btnPie.addActionListener(e -> updateChart(ChartGenerator.createProcedureDistributionPie(logs)));
        btnHeat.addActionListener(e -> updateChart(ChartGenerator.createOrOccupancyHeatmap(logs)));

        chartTools.add(btnBar);
        chartTools.add(btnLine);
        chartTools.add(btnScatter);
        chartTools.add(btnPie);
        chartTools.add(btnHeat);

        // Filter / Advanced (placeholder for now)
        JPanel filterPanel = new JPanel();
        filterPanel.setBorder(BorderFactory.createTitledBorder("Advanced / Filters"));
        JLabel lbl = new JLabel("Future: filters, surgeons, ORs");
        filterPanel.add(lbl);

        ribbon.add(summaryPanel);
        ribbon.add(chartTools);
        ribbon.add(filterPanel);

        return ribbon;
    }

    private void updateChart(JFreeChart chart) {
        chartPanel.setChart(chart);
    }

    private void showSummaryDialog() {
        double avgDur = Analytics.averageDuration(logs);
        double avgRec = Analytics.averageRecovery(logs);
        int totalMins = Analytics.totalOrMinutes(logs);
        Surgery longest = Analytics.longestSurgery(logs);
        Surgery shortest = Analytics.shortestSurgery(logs);

        StringBuilder sb = new StringBuilder();
        sb.append("Total procedures: ").append(logs.size()).append("\n");
        sb.append("Total OR hours: ").append(totalMins / 60.0).append("\n");
        sb.append("Average duration: ").append(String.format("%.2f", avgDur)).append(" mins\n");
        sb.append("Average recovery: ").append(String.format("%.2f", avgRec)).append(" days\n");
        if (longest != null) {
            sb.append("Longest: ").append(longest.procedureName)
                    .append(" (").append(longest.duration).append(" mins)\n");
        }
        if (shortest != null) {
            sb.append("Shortest: ").append(shortest.procedureName)
                    .append(" (").append(shortest.duration).append(" mins)\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(),
                "Summary Metrics", JOptionPane.INFORMATION_MESSAGE);
    }
}
