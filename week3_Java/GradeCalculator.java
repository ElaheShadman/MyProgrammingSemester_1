public class GradeCalculator {
    public static String getGradeSwitch(int score) {
        int gradeGroup = score / 10; // divide score by 10 to group it (e.g., 85 → 8)

        switch (gradeGroup) {
            case 10: // for score 100
            case 9:  // 90–99
                return "A";
            case 8:  // 80–89
                return "B";
            case 7:  // 70–79
                return "C";
            case 6:  // 60–69
                return "D";
            case 5:  // 50–59
                return "E";
            default: // anything below 50
                return "F";
        }
    }

    public static void main(String[] args) {
        System.out.println(getGradeSwitch(85)); // prints: B
    }
}
