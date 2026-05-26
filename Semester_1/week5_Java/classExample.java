class Animal {
    String name;

    void speak() {
        System.out.println(name + " makes a sound.");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal penguin = new Animal();
        penguin.name = "Penguin";
        penguin.speak();
    }
}
