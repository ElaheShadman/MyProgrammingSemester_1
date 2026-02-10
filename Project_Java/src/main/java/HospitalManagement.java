package src.main.java;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

import Surgery;

/**
 * Project: Analyzing Operating Room Efficiency and Surgical Data [cite: 7]
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
        // STEP 1: Fetch data from your specific JSON database 
        ArrayList<Surgery> logs = fetchSurgicalData("surgeries.json");

        if (logs.isEmpty()) {
            System.out.println("Error: Could not load data. Ensure 'surgeries.json' is in your project folder.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        // STEP 5: Interactive UX Menu [cite: 24]
        while (running) {
            System.out.println("\n--- Hospital OR Analytics Suite ---");
            System.out.println("1. View Raw Data (Step 1)");
            System.out.println("2. View Efficiency Summary (Step 2)");
            System.out.println("3. Filter by Specialty (Intermediate Level)");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

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
                    filterBySpecialty(logs, dept); // Intermediate Level 
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
     * STEP 1: Parsing your specific JSON format 
     */
    public static ArrayList<Surgery> fetchSurgicalData(String fileName) {
        ArrayList<Surgery> list = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                
                // Matching your exact JSON keys with spaces
                list.add(new Surgery(
                    obj.getString("Dept"),
                    obj.getString("Surgeon ID"),
                    obj.getString("Procedure Name"),
                    obj.getInt("Duration (Mins)"),
                    obj.getInt("Recovery (Days)")
                ));
            }
        } catch (Exception e) {
            System.out.println("Fetch Error: " + e.getMessage());
        }
        return list;
    }

    /**
     * STEP 2: Efficiency Metrics [cite: 13, 15]
     */
    public static void displaySummary(ArrayList<Surgery> logs) {
        double totalMins = 0;
        for (Surgery s : logs) totalMins += s.duration;

        double average = totalMins / logs.size();
        int totalHours = (int) totalMins / 60;

        System.out.println("\n--- Step 2: Efficiency Metrics ---");
        System.out.println("Total Monthly Procedures: " + logs.size()); [cite: 13]
        System.out.println("Total OR Hours Utilized: " + totalHours + " hours"); [cite: 13]
        System.out.println("Average Procedure Time: " + String.format("%.2f", average) + " mins"); [cite: 13]
    }

    public static void filterBySpecialty(ArrayList<Surgery> logs, String dept) {
        System.out.println("\nFiltering Records for: " + dept); [cite: 18]
        boolean found = false;
        for (Surgery s : logs) {
            if (s.department.equalsIgnoreCase(dept)) {
                System.out.println("- " + s.procedureName + " | Duration: " + s.duration + " mins");
                found = true;
            }
        }
        if (!found) System.out.println("No records found for department: " + dept);
    }

    public static void displayRawLogs(ArrayList<Surgery> logs) {
        System.out.printf("%-15s | %-10s | %-20s%n", "Dept", "ID", "Procedure"); [cite: 12]
        System.out.println("----------------------------------------------");
        for (Surgery s : logs) {
            System.out.printf("%-15s | %-10s | %-20s%n", s.department, s.surgeonID, s.procedureName);
        }
    }
}