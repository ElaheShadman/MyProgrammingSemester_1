package week5_Java;

import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;

public class FinancialTransactionTrackerHashMap {
    public static void main(String[] args) {

        HashMap<Integer, String> transactions = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int transactionId = 1;

        while (true) {
            System.out.println("\nFinancial Transaction Tracker");
            System.out.println("1. Add Transaction");
            System.out.println("2. Transaction History");
            System.out.println("3. Total Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter transaction amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter transaction description: ");
                    String description = scanner.nextLine();

                    String date = LocalDate.now().toString();
                    String transactionDetails =
                            "ID: " + transactionId +
                            ", Amount: " + amount +
                            ", Description: " + description +
                            ", Date: " + date;

                    transactions.put(transactionId, transactionDetails);
                    transactionId++;

                    System.out.println("Transaction added successfully!");
                    break;

                case 2:
                    System.out.println("\nTransaction History:");
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions recorded.");
                    } else {
                        for (String details : transactions.values()) {
                            System.out.println(details);
                        }
                    }
                    break;

                case 3:
                    double totalBalance = 0;

                    for (String details : transactions.values()) {
                        // Example: "ID: 1, Amount: 50.0, Description: food, Date: 2026-02-04"
                        String[] parts = details.split(", ");

                        // parts[1] = "Amount: 50.0"
                        String amountPart = parts[1].split(": ")[1];
                        double amt = Double.parseDouble(amountPart);

                        totalBalance += amt;
                    }

                    System.out.println("Total Balance: " + totalBalance);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
