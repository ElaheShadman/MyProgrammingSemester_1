public class Main {

    static int fib(int n) {
        if (n == 0) {
            return 0;               // base case 1
        }
        if (n == 1) {
            return 1;               // base case 2
        }
        return fib(n - 1) + fib(n - 2);   // recursive step
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(fib(i));
    }
}
