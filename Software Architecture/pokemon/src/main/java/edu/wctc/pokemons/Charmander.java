package edu.wctc.pokemons;

import edu.wctc.Pokemon;
import edu.wctc.Stats;
import edu.wctc.TYPE;

public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", new TYPE[] { TYPE.FIRE }, new Stats(39, 52, 43, 60, 50, 65));
    }
}
