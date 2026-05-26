
public class TannenBaum {
    public static void main(String[] args) {

        // 1) Small centered shape (plus on top, then stars)
        System.out.println("Shape 1:");
        printCenteredSmallShape();

        // 2) Centered pyramid
        System.out.println("\nShape 2:");
        printCenteredPyramid(6);

        // 3) Mirrored triangles
        System.out.println("\nShape 3:");
        printMirroredTriangles(7, 6);
    }

    // ---------------------------------------------------
    // Shape 1: "+" on top and centered star rows
    // ---------------------------------------------------
    public static void printCenteredSmallShape() {
        // Top "+"
        System.out.println("   +");

        // Increasing star rows
        System.out.println("  ***");
        System.out.println(" *****");
        System.out.println("*******");

        // Decreasing again
        System.out.println(" *****");
        System.out.println("  ***");
        System.out.println("   *");
    }

    // ---------------------------------------------------
    // Shape 2: Centered pyramid
    // ---------------------------------------------------
    public static void printCenteredPyramid(int height) {
        for (int i = 1; i <= height; i++) {
            int spaces = height - i;
            int stars = 2 * i - 1;

            // print spaces
            for (int s = 0; s < spaces; s++) {
                System.out.print(" ");
            }

            // print stars
            for (int k = 0; k < stars; k++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    // ---------------------------------------------------
    // Shape 3: Two mirrored right triangles
    // ---------------------------------------------------
    public static void printMirroredTriangles(int height, int gap) {
        for (int i = 1; i <= height; i++) {

            // Left triangle
            for (int a = 0; a < i; a++) {
                System.out.print("*");
            }

            // Middle gap
            for (int g = 0; g < gap; g++) {
                System.out.print(" ");
            }

            // Right triangle (same size as left)
            for (int b = 0; b < i; b++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}
