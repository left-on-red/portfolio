package edu.wctc;

public class Stats {
    private final int hp;
    private final int atk;
    private final int def;
    private final int spatk;
    private final int spdef;
    private final int spd;

    /**
     *
     * @param hp Health Stat
     * @param atk Attack Stat
     * @param def Defense Stat
     * @param spatk Special Attack Stat
     * @param spdef Special Defense Stat
     * @param spd Speed Stat
     */
    public Stats(int hp, int atk, int def, int spatk, int spdef, int spd) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spatk = spatk;
        this.spdef = spdef;
        this.spd = spd;
    }

    /**
     * gets the Health Stat
     * @return health
     */
    public int getHp() { return this.hp; }

    /**
     * gets the Attack Stat
     * @return attack
     */
    public int getAttack() { return this.atk; }

    /**
     * gets the Defense Stat
     * @return defense
     */
    public int getDefense() { return this.def; }

    /**
     * gets the Special Attack Stat
     * @return special attack
     */
    public int getSpecialAttack() { return this.spatk; }

    /**
     * gets the Special Defense Stat
     * @return special defense
     */
    public int getSpecialDefense() { return this.spdef; }

    /**
     * gets the Speed Stat
     * @return speed
     */
    public int getSpeed() { return this.spd; }

    // I intentionally didn't put any setters in here because Stats in the way that I intend to use them will be immutable.
    // in the actual Pokemon games, an individual pokemons EV stats (effort values) increase during runtime,
    // but the stat values that are used in battle are calculated using 3 stat objects and the level of the pokemon
}
