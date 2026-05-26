import java.util.Scanner;

public class EarthquakeCategorizer {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter earthquake magnitude: ");
        double magnitude = input.nextDouble();

        // Convert magnitude to category using chained ternary operators
        int category = (magnitude < 2.0) ? 1 :
                       (magnitude < 4.0) ? 2 :
                       (magnitude < 5.0) ? 3 :
                       (magnitude < 6.0) ? 4 :
                       (magnitude < 7.0) ? 5 :
                       (magnitude < 8.0) ? 6 :
                       (magnitude >= 8.0) ? 7 : 0;

        // Use switch to print advice
        switch (category) {
            case 1:
                System.out.println("Micro: Detected only by seismographs.");
                break;
            case 2:
                System.out.println("Minor: Rarely felt, but recorded.");
                break;
            case 3:
                System.out.println("Light: Felt by people, minor damage.");
                break;
            case 4:
                System.out.println("Moderate: Can cause damage in populated areas. Stay indoors.");
                break;
            case 5:
                System.out.println("Strong: Serious damage in areas up to 100 miles.");
                break;
            case 6:
                System.out.println("Major: Severe damage over large areas.");
                break;
            case 7:
                System.out.println("Great: Massive destruction. Evacuate immediately!");
                break;
            default:
                System.out.println("Invalid magnitude.");
        }

        input.close();
    }
}
