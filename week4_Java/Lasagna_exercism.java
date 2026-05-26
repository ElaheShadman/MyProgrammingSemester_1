public class Lasagna {
    public static int expectedMinutesInOven() {
       return 40; 
    }
    public static int remainingMinutesInOven(int realMinutesInOven) {
        return expectedMinutesInOven() - realMinutesInOven;
        }
    public static int preparationTimeInMinutes(int layers) {
        return layers * 2;
    }
    public static int totalTimeInMinutes(int layers, int realMinutesInOven) {
        return realMinutesInOven + preparationTimeInMinutes(layers);
    }
    public static void main (String[] args) {
       Lasagna lasagna = new Lasagna();
       lasagna.expectedMinutesInOven();
       lasagna.remainingMinutesInOven(30);
       lasagna.preparationTimeInMinutes(2);
       lasagna.totalTimeInMinutes(3, 20);
        }
}
