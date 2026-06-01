import java.util.Scanner;

public class TowerOfHanoi {
    static void move(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }
        move(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        move(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = scanner.nextInt();

        System.out.println("Total moves: " + ((int)Math.pow(2, n) - 1));
        move(n, 'A', 'C', 'B');
    }
}
