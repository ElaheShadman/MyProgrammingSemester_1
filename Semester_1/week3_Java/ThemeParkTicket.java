import java.util.Scanner;

public class ThemeParkTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input age
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Input status
        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean();

        System.out.print("Are you a senior? (true/false): ");
        boolean isSenior = scanner.nextBoolean();

        double ticketPrice;

        if (isSenior) {
            ticketPrice = 10.0; // Senior discount
        } else if (isStudent) {
            if (age < 30) {
                ticketPrice = 12.0; // Student discount
            } else {
                ticketPrice = 20.0; // Regular price
            }
        } else {
            ticketPrice = 20.0; // Regular price
        }

        System.out.println("Your ticket price is: €" + ticketPrice);
    }
}
