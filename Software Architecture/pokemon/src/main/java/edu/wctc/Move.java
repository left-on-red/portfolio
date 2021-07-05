package edu.wctc;

import java.util.Arrays;

public abstract class Move {
    private final String name;
    private final TYPE type;
    private final CATEGORY category;

    private final int PP;
    private final int power;
    private final double accuracy;

    private Pokemon pokemon;
    private int used;

    public Move(String name, TYPE type, CATEGORY category, int PP, int power, double accuracy) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.PP = PP;
        this.accuracy = accuracy;

        this.used = 0;
    }

    @Override
    public String toString() {
        return name + " (" + getUses() + "/" + PP + ")";
    }

    public String getName() { return name; }

    /**
     * gets the Type of this Move; of TYPE enum
     * @return
     */
    public TYPE getType() { return type; }

    /**
     * gets the Category of this Move; either CATEGORY.PHYSICAL or CATEGORY.SPECIAL
     * @return category
     */
    public CATEGORY getCategory() { return category; }

    /**
     * gets the total PP (number of uses) of this Move
     * @return PP
     */
    public int getPP() { return PP; }

    /**
     * gets the (attack) power of this Move. the higher the better
     * @return power
     */
    public int getPower() { return power; }

    /**
     * gets the accuracy of this Move. 1 is 100% and 0 is 0%
     * @return accuracy
     */
    private double getAccuracy() { return accuracy; }

    /**
     * gets the number of uses that this Move has left
     * @return uses
     */
    private int getUses() { return PP - used; }

    /**
     * returns whether or not the number of times the moved was used does not exceed the PP limit for the move
     * @return boolean
     */
    public boolean canUse() { return used < PP; }

    /**
     * binds this Move to a specific Pokemon. this allows just a Move to be passed in as an argument while maintaining the same context of being associated with a specific Pokemon instance
     * @param pokemon
     */
    public void bind(Pokemon pokemon) { this.pokemon = pokemon; }

    /**
     * returns whether or not the Move qualifies for Same Type Attack Bonus (the pokemon has a type in common with the move)
     * @return isSameTypeAttackBonus
     */
    public boolean isSTAB() { return Arrays.asList(pokemon.getTypes()).contains(type); }

    public Pokemon getAttacker() { return this.pokemon; }

    /**
     * returns the damage multiplier of how this Move will effect the target Pokemon
     * @param recipient
     * @return multiplier/effectiveness
     */
    public double getEffectivenessAgainst(Pokemon recipient) { return Advantages.calc(type, recipient.getTypes()); }

    /**
     * uses this Move on an opposing Pokemon and returns true if the attack connected and false if it didn't. (accuracy hasn't been implemented so Moves always connect)
     * @param defending
     * @return connected
     */
    public boolean useOn(Pokemon defending) {
        if (canUse()) {

            double attack_stat = 0;
            double defense_stat = 0;

            Pokemon attacking = pokemon;

            if (category == CATEGORY.PHYSICAL) {
                attack_stat = attacking.actualStats().getAttack();
                defense_stat = defending.actualStats().getDefense();
            }

            else if (category == CATEGORY.SPECIAL) {
                attack_stat = attacking.actualStats().getSpecialAttack();
                defense_stat = defending.actualStats().getSpecialDefense();
            }

            double stab = 1;

            if (isSTAB()) { stab = 1.5; }

            double effectiveness = getEffectivenessAgainst(defending);

            String flavor = "";
            if (effectiveness == 0) { flavor = "had no effect on"; }
            else if (effectiveness == 0.25) { flavor = "was extremely ineffective against"; }
            else if (effectiveness == 0.5) { flavor = "wasn't very effective against"; }
            else if (effectiveness == 1) { flavor = "was effective against"; }
            else if (effectiveness == 2) { flavor = "was super effective against"; }
            else if (effectiveness == 4) { flavor = "was extremely effective against"; }

            int damage = (int)((((((2 * (double)attacking.getLevel()) / 5) + 2) * power * (attack_stat / defense_stat) / 50) + 2) * stab * effectiveness);

            defending.takeDamage(damage);

            BattleLogger.println(attacking.getName() + " used " + name + " and it " + flavor + " the opposing " + defending.getName());

            used += 1;

            return true;
        }

        return false;
    }
}
