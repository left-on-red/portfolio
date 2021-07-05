package edu.wctc;

import java.util.*;
import java.util.stream.Stream;

public class DiceGame {
    private final List<Player> players = new ArrayList<Player>();
    private final List<Die> dice = new ArrayList<Die>();
    private final int maxRolls;
    private Player currentPlayer;

    public DiceGame(int countPlayers, int countDice, int maxRolls) {
        for (int p = 0; p < countPlayers; p++) { this.players.add(new Player()); }
        for (int d = 0; d < countDice; d++) { this.dice.add(new Die(6)); }
        this.maxRolls = maxRolls;
    }

    // not sure if early returns in this manner are good practice or not, but it works

    private boolean allDiceHeld() { return this.dice.stream().allMatch(Die::isBeingHeld); }

    public boolean autoHold(int faceValue) {
        this.dice.stream().filter(d -> d.getFaceValue() == faceValue).findFirst().ifPresent(Die::holdDie);
        return this.dice.stream().anyMatch(d -> d.getFaceValue() == faceValue && d.isBeingHeld());
    }

    public boolean currentPlayerCanRoll() {
        boolean held = true;
        for (Die die : this.dice) { if (!die.isBeingHeld()) { held = false; break; } }
        return this.currentPlayer.getRollsUsed() < this.maxRolls && (!held);
    }

    public int getCurrentPlayerNumber() { return this.currentPlayer.getPlayerNumber(); }

    public int getCurrentPlayerScore() { return this.currentPlayer.getScore(); }

    public String getDiceResults() {
        String out = "";
        this.dice.forEach(d -> out.concat(d.toString()));
        return out;
    }

    public String getFinalWinner() {
        Player winner = this.players.stream().max(Comparator.comparing(Player::getWins)).get();
        return winner.toString();
    }

    public String getGameResults() {
        String out = "";
        Player winner = this.players.stream().max(Comparator.comparing(Player::getScore)).orElse(null);
        if (winner != null) { winner.addWin(); }
        this.players.stream().filter(p -> !p.equals(winner)).forEach(Player::addLoss);
        this.players.stream().sorted(Comparator.comparing(Player::getWins)).forEach(p -> out.concat(p.toString()));
        return out;
    }

    private boolean isHoldingDie (int faceValue) { return this.dice.stream().anyMatch(d -> d.getFaceValue() == faceValue && d.isBeingHeld()); }

    public boolean nextPlayer() {
        boolean isPlayer = this.currentPlayer.getPlayerNumber() < this.players.size();
        if (isPlayer) { this.currentPlayer = this.players.get(this.currentPlayer.getPlayerNumber()); }
        return isPlayer;
    }

    public void playerHold(char dieNum) { this.dice.stream().filter(d -> d.getDieNum() == dieNum).findFirst().ifPresent(Die::holdDie); }

    public void resetDice() { this.dice.forEach(Die::resetDie); }

    public void resetPlayers() { this.players.forEach(Player::resetPlayer); }

    public void rollDice() {
        System.out.print("dice rolls: ");
        this.dice.stream().filter(d -> !d.isBeingHeld()).forEach(d -> System.out.print(d.getDieNum() + "-" + d.getFaceValue() + " "));
        System.out.println();
        this.dice.forEach(Die::rollDie);
    }

    public void scoreCurrentPlayer() {
        if (this.dice.stream().anyMatch(d -> d.getFaceValue() == 6) && this.dice.stream().anyMatch(d -> d.getFaceValue() == 5) && this.dice.stream().anyMatch(d -> d.getFaceValue() == 4)) {
            Stream<Die> filtered = this.dice.stream().filter(d -> d.getFaceValue() != 6 && d.getFaceValue() != 5 && d.getFaceValue() != 4);
            filtered.forEach(d -> this.currentPlayer.setScore(this.currentPlayer.getScore() + d.getFaceValue()));
        }
    }

    public void startNewGame() {
        this.currentPlayer = this.players.get(0);
        this.players.forEach(Player::resetPlayer);
    }
}
