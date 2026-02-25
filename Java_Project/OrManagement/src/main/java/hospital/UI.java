package hospital;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class UI extends JFrame {

    private final List<Surgery> logs;
    private final ChartPanel chartPanel;

    public UI(List<Surgery> logs) {
        this.logs = logs;

        setTitle("Hospital OR Analytics Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());
        add(createRibbon(), BorderLayout.NORTH);

        chartPanel = new ChartPanel(hospital.ChartGenerator.createOrUsageByDept(logs));
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
                        "OR Analytics Dashboard\nCreated by Elahe Shadman",
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

        // Filters / Advanced
        JPanel filterPanel = new JPanel();
        filterPanel.setBorder(BorderFactory.createTitledBorder("Advanced / Filters"));

        JButton btnFilterDept = new JButton("Filter by Dept");
        JButton btnFilterSurgeon = new JButton("Filter by Surgeon");
        JButton btnFilterDuration = new JButton("Filter by Duration");

        btnFilterDept.addActionListener(e -> filterByDepartment());
        btnFilterSurgeon.addActionListener(e -> filterBySurgeon());
        btnFilterDuration.addActionListener(e -> filterByDurationRange());

        filterPanel.add(btnFilterDept);
        filterPanel.add(btnFilterSurgeon);
        filterPanel.add(btnFilterDuration);

        ribbon.add(summaryPanel);
        ribbon.add(chartTools);
        ribbon.add(filterPanel);

        return ribbon;
    }

    private void updateChart(JFreeChart chart) {
        chartPanel.setChart(chart);
        chartPanel.revalidate();
        chartPanel.repaint();
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

        if (longest != null)
            sb.append("Longest: ").append(longest.procedureName).append(" (").append(longest.duration).append(" mins)\n");

        if (shortest != null)
            sb.append("Shortest: ").append(shortest.procedureName).append(" (").append(shortest.duration).append(" mins)\n");

        JOptionPane.showMessageDialog(this, sb.toString(),
                "Summary Metrics", JOptionPane.INFORMATION_MESSAGE);
    }

    // -------------------------
    // FILTER FUNCTIONS
    // -------------------------

    private void filterByDepartment() {
        String dept = JOptionPane.showInputDialog(this, "Enter department name:");
        if (dept == null || dept.isBlank()) return;

        List<Surgery> filtered = logs.stream()
                .filter(s -> s.department.equalsIgnoreCase(dept))
                .collect(Collectors.toList());

        updateChart(ChartGenerator.createOrUsageByDept(filtered));
    }

    private void filterBySurgeon() {
        String surgeon = JOptionPane.showInputDialog(this, "Enter Surgeon ID (e.g., S01):");
        if (surgeon == null || surgeon.isBlank()) return;

        List<Surgery> filtered = logs.stream()
                .filter(s -> s.surgeonId.equalsIgnoreCase(surgeon))
                .collect(Collectors.toList());

        updateChart(ChartGenerator.createDurationVsRecoveryScatter(filtered));
    }

    private void filterByDurationRange() {
        String minStr = JOptionPane.showInputDialog(this, "Min duration (mins):");
        String maxStr = JOptionPane.showInputDialog(this, "Max duration (mins):");

        if (minStr == null || maxStr == null) return;

        try {
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);

            List<Surgery> filtered = logs.stream()
                    .filter(s -> s.duration >= min && s.duration <= max)
                    .collect(Collectors.toList());

            updateChart(ChartGenerator.createDurationVsRecoveryScatter(filtered));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.");
        }
    }
}
