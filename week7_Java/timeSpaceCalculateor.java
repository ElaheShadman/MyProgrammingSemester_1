import java.util.HashMap;

public class MyProgram {

    private static HashMap<Integer, Integer> memo = new HashMap<>();

    // ---------------- FACTORIAL ----------------
    static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    // ---------------- RECURSIVE FIBONACCI ----------------
    static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // ---------------- ITERATIVE FIBONACCI ----------------
    public static int fibonacciIterative(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // ---------------- MEMOIZED FIBONACCI ----------------
    public static int fibonacciMemo(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        if (memo.containsKey(n)) return memo.get(n);

        int result = fibonacciMemo(n - 1) + fibonacciMemo(n - 2);
        memo.put(n, result);

        return result;
    }

    // ---------------- MEMORY MEASUREMENT ----------------
    public static long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // request garbage collection
        return runtime.totalMemory() - runtime.freeMemory();
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        // ---------- FACTORIAL ----------
        long memBefore = usedMemory();
        long start = System.nanoTime();
        int fact = factorial(10);
        long end = System.nanoTime();
        long memAfter = usedMemory();

        System.out.println("Factorial(10) = " + fact);
        System.out.println("Time (ns): " + (end - start));
        System.out.println("Memory (bytes): " + (memAfter - memBefore));
        System.out.println("----------------------------------");

        // ---------- RECURSIVE FIBONACCI ----------
        memBefore = usedMemory();
        start = System.nanoTime();
        int fibRec = fibonacci(30);
        end = System.nanoTime();
        memAfter = usedMemory();

        System.out.println("Fibonacci Recursive(30) = " + fibRec);
        System.out.println("Time (ns): " + (end - start));
        System.out.println("Memory (bytes): " + (memAfter - memBefore));
        System.out.println("----------------------------------");

        // ---------- ITERATIVE FIBONACCI ----------
        memBefore = usedMemory();
        start = System.nanoTime();
        int fibIter = fibonacciIterative(30);
        end = System.nanoTime();
        memAfter = usedMemory();

        System.out.println("Fibonacci Iterative(30) = " + fibIter);
        System.out.println("Time (ns): " + (end - start));
        System.out.println("Memory (bytes): " + (memAfter - memBefore));
        System.out.println("----------------------------------");

        // ---------- MEMOIZED FIBONACCI ----------
        memBefore = usedMemory();
        start = System.nanoTime();
        int fibMemo = fibonacciMemo(30);
        end = System.nanoTime();
        memAfter = usedMemory();

        System.out.println("Fibonacci Memoized(30) = " + fibMemo);
        System.out.println("Time (ns): " + (end - start));
        System.out.println("Memory (bytes): " + (memAfter - memBefore));
        System.out.println("----------------------------------");
    }
}
