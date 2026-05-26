package bank;

import bank.accounts.*;
import bank.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    static List<Account> accounts = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static String bankName = "Java Bank 🏦";

    public static void main(String[] args) {
        System.out.println("Welcome to " + bankName + "!");
        System.out.println("==============================");

        while (true) {
            printMainMenu();
            int choice = readInt("Your choice: ");

            switch (choice) {
                case 1 -> openAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> viewAccount();
                case 5 -> viewHistory();
                case 6 -> applyInterest();
                case 7 -> listAllAccounts();
                case 8 -> {
                    System.out.println("\n👋 Thank you for banking with " + bankName + "!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice, please pick 1-8.");
            }
        }
    }

    static void printMainMenu() {
        System.out.println("\n====== MAIN MENU ======");
        System.out.println("  1. Open a new account");
        System.out.println("  2. Deposit money");
        System.out.println("  3. Withdraw money");
        System.out.println("  4. View account summary");
        System.out.println("  5. View transaction history");
        System.out.println("  6. Apply monthly interest");
        System.out.println("  7. List all accounts");
        System.out.println("  8. Exit");
        System.out.println("=======================");
    }

    static void openAccount() {
        System.out.println("\n====== OPEN ACCOUNT ======");
        System.out.println("  1. Savings   (3% monthly interest)");
        System.out.println("  2. Checking  ($0.50 fee per withdrawal)");
        System.out.println("  3. Student   (1% interest, no fees)");
        System.out.println("  4. Business  (5% interest, high limits)");
        int type = readInt("Account type: ");

        System.out.print("Owner name: ");
        String name = input.next();

        double deposit = readDouble("Initial deposit ($): ");

        try {
            Account account = switch (type) {
                case 1 -> new SavingsAccount(name, deposit);
                case 2 -> new CheckingAccount(name, deposit);
                case 3 -> {
                    System.out.print("University name: ");
                    String uni = input.next();
                    yield new StudentAccount(name, deposit, uni);
                }
                case 4 -> {
                    System.out.print("Business name: ");
                    String biz = input.next();
                    yield new BusinessAccount(name, deposit, biz);
                }
                default -> throw new InvalidAmountException(-1);
            };

            accounts.add(account);
            System.out.println("\n✅ Account opened successfully!");
            account.printSummary();

        } catch (InvalidAmountException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    static void deposit() {
        System.out.println("\n====== DEPOSIT ======");
        try {
            Account acc = pickAccount();
            double amount = readDouble("Amount to deposit ($): ");
            acc.deposit(amount);
        } catch (AccountNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    static void withdraw() {
        System.out.println("\n====== WITHDRAW ======");
        try {
            Account acc = pickAccount();
            double amount = readDouble("Amount to withdraw ($): ");
            acc.withdraw(amount);
        } catch (AccountNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    static void viewAccount() {
        System.out.println("\n====== ACCOUNT SUMMARY ======");
        try {
            Account acc = pickAccount();
            acc.printSummary();
        } catch (AccountNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    static void viewHistory() {
        System.out.println("\n====== TRANSACTION HISTORY ======");
        try {
            Account acc = pickAccount();
            acc.printHistory();
        } catch (AccountNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    static void applyInterest() {
        System.out.println("\n====== APPLY MONTHLY INTEREST ======");
        boolean any = false;
        for (Account acc : accounts) {
            if (acc instanceof InterestBearing ib) {
                System.out.print(acc.toString() + "  →  ");
                ib.applyMonthlyInterest();
                any = true;
            }
        }
        if (!any) {
            System.out.println("No interest-bearing accounts found.");
        }
    }

    static void listAllAccounts() {
        System.out.println("\n====== ALL ACCOUNTS ======");
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet! Open one first.");
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + accounts.get(i));
        }
    }

    static Account pickAccount() throws AccountNotFoundException {
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("(none exist yet)");
        }
        listAllAccounts();
        int index = readInt("Choose account: ") - 1;
        if (index < 0 || index >= accounts.size()) {
            throw new AccountNotFoundException("index " + (index + 1));
        }
        return accounts.get(index);
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return input.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Please enter a number.");
                input.nextLine();
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return input.nextDouble();
            } catch (Exception e) {
                System.out.println("❌ Please enter a valid amount.");
                input.nextLine();
            }
        }
    }
}