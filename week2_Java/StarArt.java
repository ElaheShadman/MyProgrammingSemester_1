public class StarArt {
    public static void main(String[] args) {

        int leftStars = 5;
        int rightStars = 5;

        // Top half
        for (int i = 0; i < 5; i++) {
            printStars(leftStars - i);          // left stars
            printSpaces(2 + i * 2);             // middle expanding spaces
            printStars(rightStars - i);         // right stars (straight)
            System.out.println();
        }

        // Bottom half
        for (int i = 4; i >= 0; i--) {
            printStars(leftStars - i);
            printSpaces(2 + i * 2);
            printStars(rightStars - i);
            System.out.println();
        }
    }

    public static void printStars(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
    }

    public static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
}
