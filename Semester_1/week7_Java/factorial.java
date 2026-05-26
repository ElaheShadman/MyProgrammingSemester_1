public class Main {

    static int factorial(int n) {
        if (n == 1) {
            return 1;                 // base case
        } else {
            return n * factorial(n - 1);   // recursive step
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++)
            System.err.println(factorial(i));
    }
}
