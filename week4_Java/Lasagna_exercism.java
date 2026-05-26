public class Lasagna {
    // TODO: define the 'expectedMinutesInOven()' method
    public static int expectedMinutesInOven() {
       return 40; 
    }
    // TODO: define the 'remainingMinutesInOven()' method
    public static int remainingMinutesInOven(int realMinutesInOven) {
        return expectedMinutesInOven() - realMinutesInOven;
        }
    // TODO: define the 'preparationTimeInMinutes()' method
    public static int preparationTimeInMinutes(int layers) {
        return layers * 2;
    }
    // TODO: define the 'totalTimeInMinutes()' method
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
