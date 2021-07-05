package edu.wctc.moves;

import edu.wctc.CATEGORY;
import edu.wctc.Move;
import edu.wctc.TYPE;

public class Ember extends Move {
    public Ember() {
        super("Ember", TYPE.FIRE, CATEGORY.SPECIAL, 25, 40, 1);
    }
}
