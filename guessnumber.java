import java.util.Random;
import java.util.Scanner;

public class guessnumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totRounds = 0;
        int totScore = 0;
        while (playAgain) {
            totRounds++;
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("\nRound " + totRounds + ":");
            System.out.println("Guess a number between 1 and 100. ");
            System.out.println("You have only 5 attempts.");

            while (attempts < 5 && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    totScore += 5 - attempts; 
                    guessedCorrectly = true;
                } else if (userGuess < numberToGuess) {
                    System.out.println("The guess is too low Try again...");
                } else {
                    System.out.println("The guess is too high");
                }
            }
            if (!guessedCorrectly) {
                System.out.println("Out of attempts. The number was " + numberToGuess + ".");
            }
            System.out.print("Play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
            if (!playAgain) {
                System.out.println("\nGame Over! You played " + totRounds + " rounds.");
                System.out.println("Total score that you have got is : " + totScore);
            }
        }

        scanner.close();
    }
}
