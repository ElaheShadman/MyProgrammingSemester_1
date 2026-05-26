/*
This whole section is commented out.
Nothing inside here will run.
You can put as many lines as you want.


public class main {
  public static void main (String[] args) {
    String trafficLights = "red";
    if(trafficlights == "red") {
      System.out.println("Stop");
    }else if (trafficLights == "green") {
      System.out.println("go");
    }else {
      System.out.println("go carefully");
    }
  }
}
*/


public class main {
  public static void main (String[] args) {
    switch (trafficLights){
      case "red":
         System.out.println("Stop");
         break;
      case "green":
         System.out.println("go");
         break;
      default:
         System.out.println("go carefully");
    }
  }
}
