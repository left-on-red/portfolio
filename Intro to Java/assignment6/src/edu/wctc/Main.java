package edu.wctc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);

            // Prompt for number of players
            System.out.print("How many players? ");
            int players = Integer.parseInt(keyboard.nextLine());

            // Create a game object with X players, 5 dice, and 3 rolls per player
            DiceGame diceGame = new DiceGame(players, 5, 3);

            // Loop that controls how many rounds are played
            char response;
            do {
                playGame(diceGame, keyboard);

                System.out.print("\nAnother game? (y/n) ");
                try {
                    response = keyboard.nextLine().toLowerCase().charAt(0);
                } catch (StringIndexOutOfBoundsException ex) {
                    // I keep pressing enter at this prompt and crashing the game, so this
                    // is to save me from me.
                    System.out.println("Bad input... exiting.");
                    response = 'n';
                }
            } while (response == 'y');

            System.out.printf("And the grand champion is... %s", diceGame.getFinalWinner());

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void playGame(DiceGame game, Scanner keyboard) {
        // Assigns game's current player and resets all players from previous round
        game.startNewGame();

        // while there are more players to take turns this round
        do {
            // announce player X's turn
            System.out.printf("PLAYER %d's TURN%n", game.getCurrentPlayerNumber());

            // reset the dice for new player
            game.resetDice();

            // while current player can continue
            while (game.currentPlayerCanRoll()) {
                // roll any unheld dice
                game.rollDice();

                // try to ensure that a 6, 5, and 4 are held automatically
                if (game.autoHold(6) && game.autoHold(5) && game.autoHold(4)) {
                    // if so, player has ship, captain, and crew and can now
                    // work on holding the best cargo dice

                    // print out what the dice look like now
                    System.out.println(game.getDiceResults());

                    // if any dice are unheld and the player has rerolls remaining...
                    if (game.currentPlayerCanRoll()) {

                        char choice;
                        do {
                            // prompt for reroll of unheld dice
                            System.out.print("Enter letter of cargo die to hold, or (r) to reroll now: ");
                            try {
                                choice = keyboard.nextLine().toLowerCase().charAt(0);
                            } catch (StringIndexOutOfBoundsException ex) {
                                // I have a bad habit of striking enter at this prompt without typing anything...
                                System.out.println("Bad command. Rerolling...");
                                choice = 'r';
                            }

                            if (choice != 'r') {
                                // hold the die indicated by its menu number (NOT face value)
                                game.playerHold(choice);

                                // print out what the dice look like now
                                System.out.println(game.getDiceResults());
                            }
                        } while (choice != 'r');
                    }
                } else {
                    // print out what the dice look like now
                    System.out.println(game.getDiceResults());

                    // reroll is all the player can do
                    System.out.println("You don't have ship, captain, and crew. Press enter to continue...");
                    keyboard.nextLine();
                }
            }

            // if player had ship, captain, and crew dice held,
            // total up cargo dice ONLY and set as player's score
            game.scoreCurrentPlayer();

            // print player's score before moving on to next player
            System.out.printf("PLAYER %d's SCORE: %d %n%n",
                    game.getCurrentPlayerNumber(), game.getCurrentPlayerScore());

            // Pause to keep current player's score onscreen before moving on
            System.out.println("Press enter for next player...");
            keyboard.nextLine();
        } while (game.nextPlayer());

        // after all players go, print round results
        System.out.println(game.getGameResults());
    }

}
