package edu.wctc;

import static org.junit.jupiter.api.Assertions.*;

class AdvantagesTest {

    @org.junit.jupiter.api.Test
    void calc() {
        assert Advantages.calc(TYPE.FIRE, new TYPE[] { TYPE.GRASS, TYPE.STEEL }) == 4.0;
        assert Advantages.calc(TYPE.GHOST, new TYPE[] { TYPE.NORMAL, TYPE.DARK }) == 0;
        assert Advantages.calc(TYPE.WATER, new TYPE[] { TYPE.GRASS, TYPE.FIRE }) == 1;
        assert Advantages.calc(TYPE.FLYING, new TYPE[] { TYPE.ELECTRIC, TYPE.ROCK }) == 0.25;
        assert Advantages.calc(TYPE.STEEL, new TYPE[] { TYPE.FAIRY, TYPE.NORMAL }) == 2;
        assert Advantages.calc(TYPE.PSYCHIC, new TYPE[] { TYPE.STEEL, TYPE.ICE }) == 0.5;
    }
}