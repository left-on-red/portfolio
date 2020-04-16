package pokemonBattleSimulator.moves;
import pokemonBattleSimulator.AttackMove;

public class Pound extends AttackMove {
    //private Move POUND = new AttackMove("pound", "normal", 35, 100, 40);
    public Pound() {
        super("pound", "normal", 35, 100, 40, "physical");
    }
}