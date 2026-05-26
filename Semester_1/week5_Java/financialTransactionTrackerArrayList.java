package week5_Java;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class financialTransactionTrackerArrayList {
    public static void main(String[] args) {

        ArrayList<String> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFinancial Transaction Tracker");
            System.out.println("1. Add Transaction");
            System.out.println("2. Count Transactions of yesterday's date");
            System.out.println("3. Calculate total income and expense");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.println("Enter the type: income/expense");
                    String type = scanner.nextLine();

                    System.out.println("Enter the amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    LocalDate date = LocalDate.now();
                    transactions.add(type + " " + amount + " " + date);
                    System.out.println("Transaction added successfully!");
                    break;

                case 2:
                    LocalDate yesterday = LocalDate.now().minusDays(1);
                    int sum = 0;

                    for (String transaction : transactions) {
                        String[] parts = transaction.split(" ");
                        LocalDate transactionDate = LocalDate.parse(parts[2]);

                        if (transactionDate.equals(yesterday)) {
                            sum++;
                        }
                    }

                    System.out.println("Number of transactions from yesterday: " + sum);
                    break;

                case 3:
                    double totalIncome = 0;
                    double totalExpense = 0;

                    for (String transaction : transactions) {
                        String[] parts = transaction.split(" ");
                        String tType = parts[0];
                        double tAmount = Double.parseDouble(parts[1]);

                        if (tType.equalsIgnoreCase("income")) {
                            totalIncome += tAmount;
                        } else if (tType.equalsIgnoreCase("expense")) {
                            totalExpense += tAmount;
                        }
                    }

                    System.out.println("Total Income: " + totalIncome);
                    System.out.println("Total Expense: " + totalExpense);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
