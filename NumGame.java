import java.util.Random;
import java.util.Scanner;

public class NumGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int answer = rand.nextInt(100) + 1; // generate a random number between 1 and 100
        int attempts = 0;
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            Scanner in = new Scanner(System.in);
            int guess;
            boolean correct = false;

            while (!correct) {
                System.out.println("Guess a number between 1 and 100:");
                guess = in.nextInt();
                attempts++;

                if (guess > answer) {
                    System.out.println("Too high, try again");
                } else if (guess < answer) {
                    System.out.println("Too low, try again");
                } else {
                    System.out.println("Yes, you guessed the number!");
                    correct = true;
                    score++;
                }

                if (attempts >= 6) {
                    System.out.println("You've reached the maximum number of attempts. The answer was " + answer);
                    break;
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            String response = in.next();

            if (response.equalsIgnoreCase("no")) {
                playAgain = false;
            } else {
                answer = rand.nextInt(100) + 1; // generate a new random number
                attempts = 0;
            }
        }

        System.out.println("Your final score is " + score);
    }
}