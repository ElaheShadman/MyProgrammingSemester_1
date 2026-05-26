
public class TransposeMatrix {
    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int rows = A.length;        // 2
        int cols = A[0].length;     // 3
        int[][] T = new int[cols][rows]; // Transposed shape: 3 x 2

        // Transpose logic: T[j][i] = A[i][j]
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                T[j][i] = A[i][j];
            }
        }

        // Print transposed matrix
        System.out.println("Transposed Matrix:");
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[0].length; j++) {
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }
    }
}
