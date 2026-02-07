import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Project: Analyzing Operating Room Efficiency and Surgical Data [cite: 7]
 * This program processes surgical logs to optimize hospital scheduling[cite: 9].
 */
class Surgery {
    String department, surgeonID, procedureName;
    int duration, recoveryDays;

    Surgery(String dept, String id, String name, int dur, int rec) {
        this.department = dept;
        this.surgeonID = id;
        this.procedureName = name;
        this.duration = dur;
        this.recoveryDays = rec;
    }
}

public class HospitalManagement {

    public static void main(String[] args) {
        // STEP 1: Fetch and display surgical log data from a CSV file [cite: 14]
        ArrayList<Surgery> logs = fetchSurgicalData("surgeries.csv");

        if (logs.isEmpty()) {
            System.out.println("Error: Could not load 'surgeries.csv'. Please ensure the file is in the project folder.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        // STEP 5: Create a User Experience (UX) Menu 
        while (running) {
            System.out.println("\n--- Hospital OR Analytics Suite ---");
            System.out.println("1. View Raw Data (Step 1)");
            System.out.println("2. View Efficiency Summary (Step 2)");
            System.out.println("3. Filter by Specialty (Intermediate Level)");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    displayRawLogs(logs); // Basic Level [cite: 12]
                    break;
                case 2:
                    displaySummary(logs); // Monthly Summary [cite: 13, 15]
                    break;
                case 3:
                    System.out.print("Enter Specialty (e.g., Cardiology): ");
                    String dept = sc.nextLine();
                    filterBySpecialty(logs, dept); // Filtering [cite: 18]
                    break;
                case 4:
                    running = false;
                    System.out.println("System Shutdown.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    /**
     * STEP 1: Fetch data using CSV parsing [cite: 14]
     */
    public static ArrayList<Surgery> fetchSurgicalData(String fileName) {
        ArrayList<Surgery> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header row
            while ((line = br.readLine()) != null) {
                String[] v = line.split(",");
                // Create Surgery object from CSV columns [cite: 12, 15]
                list.add(new Surgery(v[0], v[1], v[2], Integer.parseInt(v[3]), Integer.parseInt(v[4])));
            }
        } catch (Exception e) {
            System.out.println("Read Error: " + e.getMessage());
        }
        return list;
    }

    /**
     * STEP 2: Process metrics (Average, Totals) [cite: 13, 15]
     */
    public static void displaySummary(ArrayList<Surgery> logs) {
        double totalMins = 0;
        for (Surgery s : logs) totalMins += s.duration;

        double average = totalMins / logs.size();
        int totalHours = (int) totalMins / 60;

        System.out.println("\n--- Step 2: Efficiency Metrics ---");
        System.out.println("Total Monthly Procedures: " + logs.size());
        System.out.println("Total OR Hours Utilized: " + totalHours + " hours");
        System.out.println("Average Procedure Time: " + String.format("%.2f", average) + " mins");
    }

    /**
     * INTERMEDIATE LEVEL: Filtering logic [cite: 18]
     */
    public static void filterBySpecialty(ArrayList<Surgery> logs, String dept) {
        System.out.println("\nFiltering Records for: " + dept);
        boolean found = false;
        for (Surgery s : logs) {
            if (s.department.equalsIgnoreCase(dept)) {
                System.out.println("- " + s.procedureName + " | Duration: " + s.duration + " mins");
                found = true;
            }
        }
        if (!found) System.out.println("No records found for department: " + dept);
    }

    /**
     * BASIC LEVEL: Display raw logs [cite: 12]
     */
    public static void displayRawLogs(ArrayList<Surgery> logs) {
        System.out.printf("%-15s | %-10s | %-20s%n", "Dept", "ID", "Procedure");
        System.out.println("----------------------------------------------");
        for (Surgery s : logs) {
            System.out.printf("%-15s | %-10s | %-20s%n", s.department, s.surgeonID, s.procedureName);
        }
    }
}