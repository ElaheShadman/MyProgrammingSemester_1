public class RockPaperScissors {
    public static void main(String[] args) {

        String playerMove = "rock";      // you can change this
        String computerMove = "paper";   // you can change this

        String result = "";

        if (playerMove.equals(computerMove)) {
            result = "Tie";

        } else if (playerMove.equals("rock")) {
            if (computerMove.equals("scissors")) {
                result = "You win";
            } else {
                result = "You lose";
            }

        } else if (playerMove.equals("paper")) {
            if (computerMove.equals("rock")) {
                result = "You win";
            } else {
                result = "You lose";
            }

        } else if (playerMove.equals("scissors")) {
            if (computerMove.equals("paper")) {
                result = "You win";
            } else {
                result = "You lose";
            }

        } else {
            result = "Invalid move";
        }

        System.out.println("Player: " + playerMove);
        System.out.println("Computer: " + computerMove);
        System.out.println("Result: " + result);
    }
}
