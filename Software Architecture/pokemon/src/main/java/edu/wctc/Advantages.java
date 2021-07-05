package edu.wctc;

import java.util.Arrays;
import java.util.HashMap;

public class Advantages {
    private static final HashMap<TYPE, TYPE[]> effectives = new HashMap<>();
    private static final HashMap<TYPE, TYPE[]> ineffectives = new HashMap<>();
    private static final HashMap<TYPE, TYPE[]> noeffects = new HashMap<>();

    /**
     * populates the effectives hashmaps with the correct values
     */
    private static void populateEffectives() {
        effectives.put(TYPE.NORMAL, new TYPE[] {});
        effectives.put(TYPE.FIRE, new TYPE[] { TYPE.BUG, TYPE.GRASS, TYPE.ICE, TYPE.STEEL });
        effectives.put(TYPE.WATER, new TYPE[] { TYPE.FIRE, TYPE.GROUND, TYPE.ROCK });
        effectives.put(TYPE.ELECTRIC, new TYPE[] { TYPE.FLYING, TYPE.WATER });
        effectives.put(TYPE.GRASS, new TYPE[] { TYPE.GROUND, TYPE.ROCK, TYPE.WATER });
        effectives.put(TYPE.ICE, new TYPE[] { TYPE.DRAGON, TYPE.FLYING, TYPE.GRASS, TYPE.GROUND });
        effectives.put(TYPE.FIGHTING, new TYPE[] { TYPE.DARK, TYPE.ICE, TYPE.NORMAL, TYPE.ROCK, TYPE.STEEL });
        effectives.put(TYPE.POISON, new TYPE[] { TYPE.FAIRY, TYPE.GRASS });
        effectives.put(TYPE.GROUND, new TYPE[] { TYPE.ELECTRIC, TYPE.FIRE, TYPE.POISON, TYPE.ROCK, TYPE.STEEL });
        effectives.put(TYPE.FLYING, new TYPE[] { TYPE.BUG, TYPE.GRASS, TYPE.FIGHTING });
        effectives.put(TYPE.PSYCHIC, new TYPE[] { TYPE.FIGHTING, TYPE.POISON });
        effectives.put(TYPE.BUG, new TYPE[] { TYPE.DARK, TYPE.GRASS, TYPE.PSYCHIC });
        effectives.put(TYPE.ROCK, new TYPE[] { TYPE.BUG, TYPE.FIRE, TYPE.FLYING, TYPE.ICE });
        effectives.put(TYPE.GHOST, new TYPE[] { TYPE.GHOST, TYPE.PSYCHIC });
        effectives.put(TYPE.DRAGON, new TYPE[] { TYPE.DRAGON });
        effectives.put(TYPE.DARK, new TYPE[] { TYPE.GHOST, TYPE.PSYCHIC });
        effectives.put(TYPE.STEEL, new TYPE[] { TYPE.FAIRY, TYPE.ICE, TYPE.ROCK });
        effectives.put(TYPE.FAIRY, new TYPE[] { TYPE.DARK, TYPE.DRAGON, TYPE.FIGHTING });
    }

    /**
     * populates the ineffectives hashmap with the correct values
     */
    private static void populateIneffectives() {
        ineffectives.put(TYPE.NORMAL, new TYPE[] { TYPE.ROCK, TYPE.STEEL });
        ineffectives.put(TYPE.FIRE, new TYPE[] { TYPE.FIRE, TYPE.WATER, TYPE.ROCK, TYPE.DRAGON });
        ineffectives.put(TYPE.WATER, new TYPE[] { TYPE.WATER, TYPE.GRASS, TYPE.DRAGON });
        ineffectives.put(TYPE.ELECTRIC, new TYPE[] { TYPE.ELECTRIC, TYPE.GRASS, TYPE.DRAGON });
        ineffectives.put(TYPE.GRASS, new TYPE[] { TYPE.FIRE, TYPE.GRASS, TYPE.POISON, TYPE.FLYING, TYPE.BUG, TYPE.DRAGON, TYPE.STEEL });
        ineffectives.put(TYPE.ICE, new TYPE[] { TYPE.FIRE, TYPE.WATER, TYPE.ICE, TYPE.STEEL });
        ineffectives.put(TYPE.FIGHTING, new TYPE[] { TYPE.POISON, TYPE.FLYING, TYPE.PSYCHIC, TYPE.BUG, TYPE.FAIRY });
        ineffectives.put(TYPE.POISON, new TYPE[] { TYPE.POISON, TYPE.GROUND, TYPE.ROCK, TYPE.GHOST });
        ineffectives.put(TYPE.GROUND, new TYPE[] { TYPE.GRASS, TYPE.BUG });
        ineffectives.put(TYPE.FLYING, new TYPE[] { TYPE.ELECTRIC, TYPE.ROCK, TYPE.STEEL });
        ineffectives.put(TYPE.PSYCHIC, new TYPE[] { TYPE.PSYCHIC, TYPE.STEEL });
        ineffectives.put(TYPE.BUG, new TYPE[] { TYPE.FIRE, TYPE.FIGHTING, TYPE.POISON, TYPE.FLYING, TYPE.GHOST, TYPE.STEEL, TYPE.FAIRY });
        ineffectives.put(TYPE.ROCK, new TYPE[] { TYPE.FIGHTING, TYPE.GROUND, TYPE.STEEL });
        ineffectives.put(TYPE.GHOST, new TYPE[] { TYPE.DARK });
        ineffectives.put(TYPE.DRAGON, new TYPE[] { TYPE.STEEL });
        ineffectives.put(TYPE.DARK, new TYPE[] { TYPE.FIGHTING, TYPE.DARK, TYPE.FAIRY });
        ineffectives.put(TYPE.STEEL, new TYPE[] { TYPE.FIRE, TYPE.WATER, TYPE.ELECTRIC, TYPE.STEEL });
        ineffectives.put(TYPE.FAIRY, new TYPE[] { TYPE.FIRE, TYPE.POISON, TYPE.STEEL });
    }

    /**
     * populates the noeffects hashmap with the correct values
     */
    private static void populateNoeffects() {
        noeffects.put(TYPE.NORMAL, new TYPE[] { TYPE.GHOST });
        noeffects.put(TYPE.FIRE, new TYPE[] {});
        noeffects.put(TYPE.WATER, new TYPE[] {});
        noeffects.put(TYPE.ELECTRIC, new TYPE[] { TYPE.GROUND });
        noeffects.put(TYPE.GRASS, new TYPE[] {});
        noeffects.put(TYPE.ICE, new TYPE[] {});
        noeffects.put(TYPE.FIGHTING, new TYPE[] { TYPE.GHOST });
        noeffects.put(TYPE.POISON, new TYPE[] { TYPE.STEEL });
        noeffects.put(TYPE.GROUND, new TYPE[] { TYPE.FLYING });
        noeffects.put(TYPE.FLYING, new TYPE[] {});
        noeffects.put(TYPE.PSYCHIC, new TYPE[] { TYPE.DARK });
        noeffects.put(TYPE.BUG, new TYPE[] {});
        noeffects.put(TYPE.ROCK, new TYPE[] {});
        noeffects.put(TYPE.GHOST, new TYPE[] { TYPE.NORMAL });
        noeffects.put(TYPE.DRAGON, new TYPE[] { TYPE.FAIRY });
        noeffects.put(TYPE.DARK, new TYPE[] {});
        noeffects.put(TYPE.STEEL, new TYPE[] {});
        noeffects.put(TYPE.FAIRY, new TYPE[] {});
    }

    /**
     * validates that each hashmap has been populated. if not, populate them
     */
    private static void checkMaps() {
        if (effectives.size() == 0) { populateEffectives(); }
        if (ineffectives.size() == 0) { populateIneffectives(); }
        if (noeffects.size() == 0) { populateNoeffects(); }
    }

    /**
     * returns a multiplier representing how much of an advantage an attacking type would have over an array of defending types
     * @param attacking
     * @param defending
     * @return multiplier
     */
    public static double calc(TYPE attacking, TYPE[] defending) {
        double effectiveness = 1;

        for (int t = 0; t < defending.length; t++) {
            double value = calc(attacking, defending[t]);
            if (value == 0) { effectiveness = 0; break; }
            else { effectiveness *= value; }
        }

        return effectiveness;
    }

    /**
     * returns a multiplier representing how much of an advantage an attacking type would have over a defending type
     * @param attacking
     * @param defending
     * @return
     */
    public static double calc(TYPE attacking, TYPE defending) {
        checkMaps();

        // if the defending type is immune to the attacking type
        if (Arrays.asList(noeffects.get(attacking)).contains(defending)) { return 0; }

        // if the attacking type is super effective against the defending type

        if (Arrays.asList(effectives.get(attacking)).contains(defending)) { return 2; }

        // if the attacking type is ineffective against the defending type
        if (Arrays.asList(ineffectives.get(attacking)).contains(defending)) { return 0.5; }

        // if the attacking type is neutral against the defending type
        return 1;
    }
}
