package week5_Java;

import java.util.ArrayList
import java.time.LocalDate;
import java.util.Scanner;

public class financialTransactionTrackerArrayList {
    public static void main(String[] args) {
       ArrayList<String> transactions = new ArrayList<String>();
       Scanner scanner = new Scanner(System.in);

       while (true){
        System.out.println("Financial Transaction Tracker");
        System.out.println("1. Add Transaction");
        System.out.println("2. Count Transactions of yesterdays date");
        System.out.println("3. calculate total income ans expense");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice){
            case 1:
                System.out.println("Enter the type : income/expense");
                String type = scanner.nextLine();

                System.out.println("Enter the amount: ");
                double amount = scanner.nextDouble();

                localDate date = LocalDate.now();
                transactions.add(type + " " + amount + " " + date);
                System.out.println("Transaction added successfully!");
      

    }
}
