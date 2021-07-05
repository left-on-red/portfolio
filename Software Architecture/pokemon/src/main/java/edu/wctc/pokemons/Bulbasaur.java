package edu.wctc.pokemons;

import edu.wctc.Pokemon;
import edu.wctc.Stats;
import edu.wctc.TYPE;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", new TYPE[] { TYPE.GRASS, TYPE.POISON }, new Stats(45, 49, 49, 65, 65, 45));
    }
}
