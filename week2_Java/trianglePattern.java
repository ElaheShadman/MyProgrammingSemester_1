
public class trianglePattern {
    public static void main(String[] args) {
        int n = 4; // number of rows

        for (int i = 1; i <= n; i++) {      // row i
            for (int j = 1; j <= i; j++) {  // i stars in row i
                System.out.print("*");
            }
            System.out.println();           // newline after each row
        }
    }
}
