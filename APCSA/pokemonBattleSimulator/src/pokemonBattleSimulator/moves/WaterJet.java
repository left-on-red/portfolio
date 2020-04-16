package pokemonBattleSimulator.moves;
import pokemonBattleSimulator.AttackMove;

public class WaterJet extends AttackMove {
    //private Move POUND = new AttackMove("pound", "normal", 35, 100, 40);
    public WaterJet() {
        super("water jet", "water", 25, 100, 40, "physical");
    }
}