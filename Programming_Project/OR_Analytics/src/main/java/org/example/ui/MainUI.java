package org.example.ui;

import org.example.charts.ChartCreator;
import org.example.model.Surgery;
import org.example.service.DataLoader;
import org.example.service.StatisticsService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MainUI extends JFrame {

    public MainUI() {
        setTitle("Operating Room Analytics Dashboard");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Surgery> surgeries = DataLoader.loadData("src/main/data/surgeries.json");
        if (surgeries == null || surgeries.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data loaded.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double avgDuration = StatisticsService.averageDuration(surgeries);
        String mostFreqProc = StatisticsService.mostFrequentProcedure(surgeries);
        int totalHours = StatisticsService.totalORHours(surgeries);

        Map<String, Integer> dailyVolume = StatisticsService.dailyVolume(surgeries);
        Map<String, Integer> specialtyUsage = StatisticsService.usageBySpecialty(surgeries);
        Map<String, Integer> surgeonComparison = StatisticsService.surgeriesBySurgeon(surgeries);
        Map<String, Double> procRecovery = StatisticsService.procedureVsRecovery(surgeries);
        Map<Integer, Integer> durationOcc = StatisticsService.durationVsOccupancy(surgeries);
        Map<Integer, Integer> hourlyOcc = StatisticsService.hourlyOccupancy(surgeries);
        Map<String, Double> movingAvg = StatisticsService.movingAverageDailyVolume(surgeries, 3);

        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255));
        header.setPreferredSize(new Dimension(1100, 55));
        JLabel title = new JLabel("Operating Room Analytics");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);

        // ------------------------------
        // BEAUTIFUL, SMALL SUMMARY PANEL
        // ------------------------------
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(3, 1, 2, 2));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        summaryPanel.setBackground(new Color(245, 245, 245));
        summaryPanel.setPreferredSize(new Dimension(260, 120));

        JLabel avgLabel = new JLabel("Avg Duration: " + String.format("%.1f", avgDuration) + " min");
        avgLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel freqLabel = new JLabel("Most Common: " + mostFreqProc);
        freqLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel hoursLabel = new JLabel("Total OR Hours: " + totalHours);
        hoursLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        summaryPanel.add(avgLabel);
        summaryPanel.add(freqLabel);
        summaryPanel.add(hoursLabel);

        // ------------------------------
        // SMALL, CLEAN PREDICTION BOX
        // ------------------------------
        JTextArea notes = new JTextArea();
        notes.setEditable(false);
        notes.setLineWrap(true);
        notes.setWrapStyleWord(true);
        notes.setFont(new Font("Arial", Font.PLAIN, 12));
        notes.setBackground(new Color(250, 250, 250));
        notes.setBorder(BorderFactory.createTitledBorder("Prediction Notes"));
        notes.setText(
                "• 3-day moving average predicts demand\n" +
                        "• Smooths daily fluctuations\n" +
                        "• Helps identify trends\n" +
                        "• Peak hours = bottlenecks"
        );
        notes.setPreferredSize(new Dimension(260, 120));

        // ------------------------------
        // CHART SELECTOR
        // ------------------------------
        String[] chartOptions = new String[]{
                "Daily Surgery Volume",
                "OR Usage by Specialty",
                "Surgeries by Surgeon",
                "Procedure vs Recovery Time",
                "Duration vs OR Occupancy",
                "OR Occupancy by Hour",
                "Moving Average of Daily Volume"
        };

        JComboBox<String> chartSelector = new JComboBox<>(chartOptions);
        chartSelector.setFont(new Font("Arial", Font.PLAIN, 13));
        chartSelector.setPreferredSize(new Dimension(260, 30));

        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.add(ChartCreator.createDailyVolumeChart(dailyVolume), BorderLayout.CENTER);

        chartSelector.addActionListener(e -> {
            chartPanel.removeAll();
            String choice = (String) chartSelector.getSelectedItem();

            switch (choice) {
                case "Daily Surgery Volume" ->
                        chartPanel.add(ChartCreator.createDailyVolumeChart(dailyVolume));
                case "OR Usage by Specialty" ->
                        chartPanel.add(ChartCreator.createSpecialtyUsageChart(specialtyUsage));
                case "Surgeries by Surgeon" ->
                        chartPanel.add(ChartCreator.createSurgeonComparisonChart(surgeonComparison));
                case "Procedure vs Recovery Time" ->
                        chartPanel.add(ChartCreator.createProcedureRecoveryChart(procRecovery));
                case "Duration vs OR Occupancy" ->
                        chartPanel.add(ChartCreator.createDurationOccupancyChart(durationOcc));
                case "OR Occupancy by Hour" ->
                        chartPanel.add(ChartCreator.createHourlyHeatmapChart(hourlyOcc));
                case "Moving Average of Daily Volume" ->
                        chartPanel.add(ChartCreator.createMovingAverageChart(movingAvg));
            }

            chartPanel.revalidate();
            chartPanel.repaint();
        });

        // ------------------------------
        // LEFT PANEL (compact & clean)
        // ------------------------------
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        leftPanel.setPreferredSize(new Dimension(280, 0));
        leftPanel.add(summaryPanel);
        leftPanel.add(chartSelector);
        leftPanel.add(notes);

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(chartPanel, BorderLayout.CENTER);
    }
}
