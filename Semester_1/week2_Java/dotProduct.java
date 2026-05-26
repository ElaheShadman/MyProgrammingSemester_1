
public class dotProduct {
    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int[] B = {4, 5, 6};

        int dotProduct = 0;

        // Complete the for-loop
        for (int i = 0; i < A.length; i++) {
            dotProduct += A[i] * B[i];
        }

        System.out.println("Dot Product: " + dotProduct);
    }
}
