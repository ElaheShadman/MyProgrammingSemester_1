public class matrixMultiplication {
    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6}
        }; // 2x3

        int[][] B = {
                {7, 8},
                {9, 10},
                {11, 12}
        }; // 3x2

        // Result matrix
        int rowsA = A.length;       // 2
        int colsA = A[0].length;    // 3
        int colsB = B[0].length;    // 2
        int[][] C = new int[rowsA][colsB]; // 2x2

        // Matrix multiplication: C[i][j] = sum over k of A[i][k] * B[k][j]
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }

        // Print result matrix
        System.out.println("Result of A x B:");
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
