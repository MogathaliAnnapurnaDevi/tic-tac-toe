📁 number_guess_game/exceptions/InvalidInputException.java
package number_guess_game.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

📁 number_guess_game/services/GuessGameService.java

package number_guess_game.services;

import java.util.Random;
import number_guess_game.exceptions.InvalidInputException;

public class GuessGameService {
    private int targetNumber;
    private int maxAttempts;
    private int attemptsMade;
    private int maxRange;

    public GuessGameService(String difficulty) {
        this.maxAttempts = 7;
        this.attemptsMade = 0;
        this.maxRange = switch (difficulty.toLowerCase()) {
            case "easy" -> 50;
            case "hard" -> 200;
            default -> 100;
        };
        generateNumber();
    }

    private void generateNumber() {
        Random rand = new Random();
        this.targetNumber = rand.nextInt(maxRange) + 1;
    }

    public String processGuess(String input) throws InvalidInputException {
        int guess;
        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input must be an integer.");
        }

        if (guess < 1 || guess > maxRange) {
            throw new InvalidInputException("Guess must be between 1 and " + maxRange);
        }

        attemptsMade++;

        if (guess == targetNumber) {
            return "Correct! You guessed it in " + attemptsMade + " attempts.";
        } else if (guess < targetNumber) {
            return "Too low!";
        } else {
            return "Too high!";
        }
    }

    public boolean isGameOver() {
        return attemptsMade >= maxAttempts;
    }

    public int getRemainingAttempts() {
        return maxAttempts - attemptsMade;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getAttemptsMade() {
        return attemptsMade;
    }

    public void reset(String difficulty) {
        this.attemptsMade = 0;
        this.maxRange = switch (difficulty.toLowerCase()) {
            case "easy" -> 50;
            case "hard" -> 200;
            default -> 100;
        };
        generateNumber();
    }
}



📁 number_guess_game/main/NumberGuessGameApp.java

package number_guess_game.main;

import java.util.Scanner;
import number_guess_game.services.GuessGameService;
import number_guess_game.exceptions.InvalidInputException;

public class NumberGuessGameApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String difficulty = "medium";
        int bestScore = Integer.MAX_VALUE;

        System.out.println("--- Number Guessing Game ---");

        boolean playAgain = true;
        while (playAgain) {
            System.out.print("Select difficulty (easy / medium / hard): ");
            difficulty = scanner.nextLine().trim();
            GuessGameService game = new GuessGameService(difficulty);

            System.out.println("Guess a number between 1 and " + game.getMaxRange());

            while (!game.isGameOver()) {
                System.out.print("Attempt " + (game.getAttemptsMade() + 1) + ": Enter your guess: ");
                String input = scanner.nextLine();
                try {
                    String result = game.processGuess(input);
                    System.out.println(result);
                    if (result.startsWith("Correct!")) {
                        if (game.getAttemptsMade() < bestScore) {
                            bestScore = game.getAttemptsMade();
                            System.out.println("🎉 New Best Score: " + bestScore + " attempts!");
                        }
                        break;
                    } else {
                        System.out.println("Remaining Attempts: " + game.getRemainingAttempts());
                    }
                } catch (InvalidInputException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (game.isGameOver() && game.getTargetNumber() != Integer.parseInt(scanner.nextLine())) {
                System.out.println("Game Over! The number was: " + game.getTargetNumber());
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
📁 number_guess_game/exceptions/InvalidInputException.java
package number_guess_game.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

📁 number_guess_game/services/GuessGameService.java

package number_guess_game.services;

import java.util.Random;
import number_guess_game.exceptions.InvalidInputException;

public class GuessGameService {
    private int targetNumber;
    private int maxAttempts;
    private int attemptsMade;
    private int maxRange;

    public GuessGameService(String difficulty) {
        this.maxAttempts = 7;
        this.attemptsMade = 0;
        this.maxRange = switch (difficulty.toLowerCase()) {
            case "easy" -> 50;
            case "hard" -> 200;
            default -> 100;
        };
        generateNumber();
    }

    private void generateNumber() {
        Random rand = new Random();
        this.targetNumber = rand.nextInt(maxRange) + 1;
    }

    public String processGuess(String input) throws InvalidInputException {
        int guess;
        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input must be an integer.");
        }

        if (guess < 1 || guess > maxRange) {
            throw new InvalidInputException("Guess must be between 1 and " + maxRange);
        }

        attemptsMade++;

        if (guess == targetNumber) {
            return "Correct! You guessed it in " + attemptsMade + " attempts.";
        } else if (guess < targetNumber) {
            return "Too low!";
        } else {
            return "Too high!";
        }
    }

    public boolean isGameOver() {
        return attemptsMade >= maxAttempts;
    }

    public int getRemainingAttempts() {
        return maxAttempts - attemptsMade;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getAttemptsMade() {
        return attemptsMade;
    }

    public void reset(String difficulty) {
        this.attemptsMade = 0;
        this.maxRange = switch (difficulty.toLowerCase()) {
            case "easy" -> 50;
            case "hard" -> 200;
            default -> 100;
        };
        generateNumber();
    }
}



📁 number_guess_game/main/NumberGuessGameApp.java

package number_guess_game.main;

import java.util.Scanner;
import number_guess_game.services.GuessGameService;
import number_guess_game.exceptions.InvalidInputException;

public class NumberGuessGameApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String difficulty = "medium";
        int bestScore = Integer.MAX_VALUE;

        System.out.println("--- Number Guessing Game ---");

        boolean playAgain = true;
        while (playAgain) {
            System.out.print("Select difficulty (easy / medium / hard): ");
            difficulty = scanner.nextLine().trim();
            GuessGameService game = new GuessGameService(difficulty);

            System.out.println("Guess a number between 1 and " + game.getMaxRange());

            while (!game.isGameOver()) {
                System.out.print("Attempt " + (game.getAttemptsMade() + 1) + ": Enter your guess: ");
                String input = scanner.nextLine();
                try {
                    String result = game.processGuess(input);
                    System.out.println(result);
                    if (result.startsWith("Correct!")) {
                        if (game.getAttemptsMade() < bestScore) {
                            bestScore = game.getAttemptsMade();
                            System.out.println("🎉 New Best Score: " + bestScore + " attempts!");
                        }
                        break;
                    } else {
                        System.out.println("Remaining Attempts: " + game.getRemainingAttempts());
                    }
                } catch (InvalidInputException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (game.isGameOver() && game.getTargetNumber() != Integer.parseInt(scanner.nextLine())) {
                System.out.println("Game Over! The number was: " + game.getTargetNumber());
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}