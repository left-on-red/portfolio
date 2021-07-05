package edu.wctc;

import java.util.Random;

public class BattleManager {
    private final Pokemon player1;
    private final Pokemon player2;

    private final Random rng = new Random();

    private int turns;

    /**
     * a Battle Manager that manages the state and execution of the Pokemon Battle as well as user input
     * @param player1
     * @param player2
     */
    public BattleManager(Pokemon player1, Pokemon player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.turns = 0;
    }

    /**
     * returns player 1
     * @return Pokemon
     */
    public Pokemon getPlayer1() { return player1; }

    /**
     * returns player 2
     * @return Pokemon
     */
    public Pokemon getPlayer2() { return player2; }

    /**
     * returns the attacking Pokemon for the current turn
     * @return Pokemon
     */
    private Pokemon getAttacker() { return turns % 2 == 0 ? player1 : player2; }

    /**
     * returns the defending Pokemon for the current turn
     * @return Pokemon
     */
    private Pokemon getDefender() { return turns % 2 == 0 ? player2 : player1; }

    /**
     * gets the Move that the user wishes to use
     * @return Move
     */
    private Move getTurnMove() {
        Pokemon attacker = getAttacker();

        if (attacker == player2) {
            int index = rng.nextInt(2);
            return attacker.getMoves()[index];
        }

        while (true) {
            String input = BattleLogger.readln("Which move do you want to use?\n\n" + attacker.getMovesDisplay() + "\n\n> ");
            try {
                int index = Integer.parseInt(input) - 1;
                if (index < 0 || index > 3) { BattleLogger.println("\nPlease specify a number between 1 and 4...\n\n"); }
                else if (attacker.getMoves()[index] == null) { BattleLogger.println("\nYou don't have a move in that slot...\n\n"); }
                else { return attacker.getMoves()[index]; }
            }
            catch (NumberFormatException e) { BattleLogger.println("\nThere was a problem with your response...\n\n"); }
        }
    }

    /**
     * executes the next battle turn
     * @return
     */
    public boolean executeTurn() {
        Pokemon attacker = getAttacker();
        Pokemon defender = getDefender();

        BattleLogger.println("\nYOU: " + getPlayer1().toString());
        BattleLogger.println("OPPONENT: " + getPlayer2().toString() + "\n");

        Move move = getTurnMove();

        while (!move.useOn(defender)) { BattleLogger.println("\nYou don't have any more uses for that move...\n\n"); }

        if (defender.isDead()) {
            if (defender == player1) { BattleLogger.println("\nYour " + player1.getName() + " fainted..."); }
            else if (defender == player2) { BattleLogger.println("\nThe opposing " + player2.getName() + " fainted."); }
            return false;
        }

        else {
            turns += 1;
            return true;
        }
    }
}
