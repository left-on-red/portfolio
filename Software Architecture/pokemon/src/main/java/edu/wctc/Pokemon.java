package edu.wctc;

import java.lang.reflect.InvocationTargetException;

public abstract class Pokemon {
    private final String name;
    private final TYPE[] types;
    private final Stats base;

    private int level;
    private int hp;

    private Stats iv;
    private Stats ev;

    // there will always be 4 move slots
    private final Move[] moves = { null, null, null, null };

    public Pokemon(String name, TYPE[] types, Stats base) {
        this.name = name;
        this.types = types;
        this.base = base;
    }

    /**
     * gets the species name of this Pokemon
     * @return
     */
    public String getName() { return name; }

    /**
     * gets the Types associated with this Pokemon; an array of TYPE enums
     * @return
     */
    public TYPE[] getTypes() { return types; }

    /**
     * gets the level of this Pokemon
     * @return level
     */
    public int getLevel() { return this.level; }

    /**
     * initializes this Pokemon with a level
     * @param level
     */
    public void initializeLevel(int level) {
        if (this.level == 0) { this.level = level; }
    }

    /**
     * initializes this Pokemon with iv Stats
     * @param iv
     */
    public void initializeIv(Stats iv) {
        if (this.iv == null) { this.iv = iv; }
    }

    /**
     * initializes this Pokemon with ev Stats
     * @param ev
     */
    public void initializeEv(Stats ev) {
        if (this.ev == null) { this.ev = ev; }
    }

    /**
     * initializes this Pokemon with a level, iv Stats, and ev Stats
     * @param level
     * @param iv
     * @param ev
     */
    public void initialize(int level, Stats iv, Stats ev) {
        initializeLevel(level);
        initializeIv(iv);
        initializeEv(ev);
        this.hp = actualStats().getHp();
    }

    /**
     * gets the current hp level of this Pokemon
     * @return hp
     */
    public int getHp() { return this.hp; }

    /**
     * gets the max hp of this Pokemon
     * @return max hp
     */
    public int getMaxHp() { return this.actualStats().getHp(); }

    /**
     * calculates the actual stat for hp
     * @param level
     * @param base
     * @param iv
     * @param ev
     * @return actual hp
     */
    private static int getEffectiveHp(int level, int base, int iv, int ev) {
        return (((2 * base + iv + (ev / 4)) * level) / 100) + level + 10;
    }

    /**
     * calculates the actual stat for every stat except for hp
     * @param level
     * @param base
     * @param iv
     * @param ev
     * @return actual stat (not hp)
     */
    private static int getEffectiveElse(int level, int base, int iv, int ev) {
        return ((((2 * base + iv + (ev / 4))) * level) / 100) + 5; // normally this would be multiplied by 1.1 or 0.9 if natures were implemented
    }

    /**
     * gets the actual calculated stats of this Pokemon that are to be used in an actual battle scenario
     * @return stats
     */
    protected Stats actualStats() {
        return new Stats(
                getEffectiveHp(level, base.getHp(), iv.getHp(), ev.getHp()),
                getEffectiveElse(level, base.getAttack(), iv.getAttack(), ev.getAttack()),
                getEffectiveElse(level, base.getDefense(), iv.getDefense(), ev.getDefense()),
                getEffectiveElse(level, base.getSpecialAttack(), iv.getSpecialAttack(), ev.getSpecialAttack()),
                getEffectiveElse(level, base.getSpecialDefense(), iv.getSpecialDefense(), ev.getSpecialDefense()),
                getEffectiveElse(level, base.getSpeed(), iv.getSpeed(), ev.getSpeed())
        );
    }

    /**
     * returns an array containing this Pokemons Moves
     * @return Move[]
     */
    public Move[] getMoves() { return this.moves; }

    /**
     * returns a string representation of this Pokemons Movelist
     * @return String
     */
    public String getMovesDisplay() {
        String[] lines = new String[moves.length];

        for (int m = 0; m < moves.length; m++) { lines[m] = (m+1) + ": " + (moves[m] == null ? "-" : moves[m].toString()); }

        return String.join("\n", lines);
    }

    /**
     * sets a specific Move index
     * @param index
     * @param move
     */
    public void setMove(int index, Move move) {
        move.bind(this);
        this.moves[index] = move;
    }

    /**
     * sets a specific Move index via a Move id string
     * @param index
     * @param move
     */
    public void setMove(int index, String move) {
        try {
            String className = "edu.wctc.moves." + move;
            Class moveClass = Class.forName(className);
            Move moveObject = (Move)moveClass.getDeclaredConstructor().newInstance();
            setMove(index, moveObject);
        }
        catch (InstantiationException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {}
    }

    /**
     * takes away health from this Pokemon to simulate an attack landing and doing damage
     * @param damage
     */
    public void takeDamage(int damage) { this.hp -= damage; }

    /**
     * returns a boolean representing whether or not this Pokemon is dead (at or below 0 hp)
     * @return
     */
    public boolean isDead() { return this.hp <= 0; }

    @Override
    public String toString() {
        return name + " (" + hp + "/" + getMaxHp() + ")";
    }
}
