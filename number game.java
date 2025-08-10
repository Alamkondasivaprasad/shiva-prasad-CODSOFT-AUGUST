import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int score = 0; 
        System.out.println("=== Welcome to the Number Guessing Game ===");
        boolean playAgain = true;
        while (playAgain) {
            int numberToGuess = rand.nextInt(100) + 1;
            int attemptsLeft = 5; 
            boolean guessedCorrectly = false;
            System.out.println("\nI have picked a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it.");
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attemptsLeft--;
                if (guess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number!");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Too high! Attempts left: " + attemptsLeft);
                }
            }
            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The number was: " + numberToGuess);
            }
            System.out.println("Current Score (Rounds Won): " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();
            playAgain = choice.equals("yes");
        }
        System.out.println("\nGame Over! Final Score: " + score);
        sc.close();
    }
}
