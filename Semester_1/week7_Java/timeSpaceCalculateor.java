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


/*
    Recursive Fibonacci
    -------------------
    Time Complexity: O(2^n)
    Space Complexity: O(n)
    Notes:
    - Extremely slow for large n because it recomputes the same values many times.
    - Builds a huge recursion tree.

    Iterative Fibonacci
    -------------------
    Time Complexity: O(n)
    Space Complexity: O(1)
    Notes:
    - Very fast and memory efficient.
    - Uses only a few variables and no recursion.

    Memoized Fibonacci
    ------------------
    Time Complexity: O(n)
    Space Complexity: O(n)
    Notes:
    - Fast because it stores previously computed results in a HashMap.
    - Uses extra memory to save values but avoids repeated work.
*/


/*
    How the time measurement works
    ------------------------------
    We measure execution time using System.nanoTime():

        long start = System.nanoTime();
        // run the algorithm
        long end = System.nanoTime();
        System.out.println(end - start);

    This returns the duration in nanoseconds, which is the most precise
    timing method available in Java.


    How the memory measurement works
    --------------------------------
    We measure memory usage using the Runtime class:

        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // ask the JVM to clean up
        long used = runtime.totalMemory() - runtime.freeMemory();

    To measure memory before and after running an algorithm:

        long memBefore = usedMemory();
        long memAfter = usedMemory();
        System.out.println(memAfter - memBefore);

    This gives an approximate number of bytes used by the algorithm.
    Memory values are approximate because the JVM manages memory dynamically.
*/

