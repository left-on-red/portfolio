package edu.wctc.pokemons;

import edu.wctc.Pokemon;
import edu.wctc.Stats;
import edu.wctc.TYPE;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", new TYPE[] { TYPE.WATER }, new Stats(44, 48, 65, 50, 64, 43));
    }
}
