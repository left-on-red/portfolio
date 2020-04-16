package pokemonBattleSimulator.moves;
import pokemonBattleSimulator.StatusMove;
import pokemonBattleSimulator.Pokemon;

public class SwordsDance extends StatusMove {
    //private Move POUND = new AttackMove("pound", "normal", 35, 100, 40);
    public SwordsDance() {
        super("swords dance", "normal", 35, 100);
    }

    @Override
    public String execute(Pokemon offense, Pokemon defense) {
        offense.addStatChange("attack", 2, 2);
        return "attack raised by 2 stages!";
    }
}