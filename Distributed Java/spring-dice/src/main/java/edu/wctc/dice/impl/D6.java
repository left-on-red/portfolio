package edu.wctc.dice.impl;

import edu.wctc.dice.iface.DieRoller;
import java.util.Random;

public class D6 implements DieRoller {

    @Override
    public int getRoll() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
