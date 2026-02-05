import java.time.LocalDate; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;

public class MyProgram {

    public static void main(String[] args) {

        List<String[]> records = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== Transaction Menu ===");
            System.out.println("1. Record New Transaction");
            System.out.println("2. Count Yesterday's Transactions");
            System.out.println("3. Show Income & Expenses");
            System.out.println("4. Quit");
            System.out.print("Select option: ");

            int option = input.nextInt();
            input.nextLine(); // clear newline


             switch (option) {

                case 1:
                    System.out.print("Type (Sale / Purchase): ");
                    String category = input.nextLine();

                    System.out.print("Amount: ");
                    double value = input.nextDouble();
                    input.nextLine();

                    LocalDate today = LocalDate.now();
                    records.add(new String[]{
                            category.trim(),
                            String.valueOf(value),
                            today.toString()
                    });

                    System.out.println("Saved successfully.");
                    break;
                
                 case 2:
                    LocalDate prevDay = LocalDate.now().minusDays(1);

                    long yesterdayCount = records.stream()
                            .filter(r -> r[2].equals(prevDay.toString()))
                            .count();

                    System.out.println("Number of transactions yesterday: " + yesterdayCount);
                    break;

                 case 3:
                    double income = records.stream()
                            .filter(r -> r[0].equalsIgnoreCase("Sale"))
                            .mapToDouble(r -> Double.parseDouble(r[1]))
                            .sum();

                    double expenses = records.stream()
                            .filter(r -> r[0].equalsIgnoreCase("Purchase"))
                            .mapToDouble(r -> Double.parseDouble(r[1]))
                            .sum();

                    System.out.println("Income Total: " + income);
                    System.out.println("Expense Total: " + expenses);
                    break;