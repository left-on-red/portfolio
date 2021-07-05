package edu.wctc.moves;

import edu.wctc.CATEGORY;
import edu.wctc.Move;
import edu.wctc.TYPE;

public class WaterGun extends Move {
    public WaterGun() {
        super("Water Gun", TYPE.WATER, CATEGORY.SPECIAL, 25, 40, 1);
    }
}
