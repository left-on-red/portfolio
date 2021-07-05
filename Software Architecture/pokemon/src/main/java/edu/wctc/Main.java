package edu.wctc;

import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] starters = { "Bulbasaur", "Charmander", "Squirtle" };
        String[] arr = new String[starters.length];

        for (int i = 0; i < starters.length; i++) { arr[i] = (i+1) + ": " + starters[i]; }

        BattleLogger.println("Welcome to my Pokemon Battle Simulator!\n");

        String starter = "";

        do {
            BattleLogger.println("To start, pick a starter Pokemon: \n\n" + String.join("\n", arr) + "\n");
            try {
                int index = Integer.parseInt(BattleLogger.readln("> ")) - 1;
                if (index >= 0 && starters.length > index) { starter = starters[index]; }
            }
            catch (NumberFormatException e) {}

            if (!Arrays.asList(starters).contains(starter)) {
                BattleLogger.println("\nOops! There was an error receiving your selection. Lets try this again.\n\n");
            }
        }

        while (!Arrays.asList(starters).contains(starter));

        String rival = "";

        switch(starter) {
            case "Bulbasaur": rival = "Squirtle"; break;
            case "Charmander": rival = "Bulbasaur"; break;
            case "Squirtle": rival = "Charmander"; break;
        }

        BattleLogger.println("\nYou chose " + starter + "! And your rival chose " + rival + ".");

        BattleManager manager = new BattleManager(
                PokemonFactory.createPokemon(
                        starter,
                        50,
                        new Stats(15, 15, 15, 15, 15, 15),
                        new Stats(0, 0, 0, 0, 0, 0)),

                PokemonFactory.createPokemon(
                        rival,
                        50,
                        new Stats(15, 15, 15, 15, 15, 15),
                        new Stats(0, 0, 0, 0, 0, 0)
                ));

        Pokemon player1 = manager.getPlayer1();
        Pokemon player2 = manager.getPlayer2();

        String move1 = "RazorLeaf";
        String move2 = "RazorLeaf";

        switch(starter) {
            case "Charmander": move1 = "Ember"; break;
            case "Squirtle": move1 = "WaterGun"; break;
        }

        switch(rival) {
            case "Charmander": move2 = "Ember"; break;
            case "Squirtle": move2 = "WaterGun"; break;
        }

        player1.setMove(0, "Scratch");
        player1.setMove(1, move1);

        player2.setMove(0, "Scratch");
        player2.setMove(1, move2);

        while (manager.executeTurn()) {}
    }
}
