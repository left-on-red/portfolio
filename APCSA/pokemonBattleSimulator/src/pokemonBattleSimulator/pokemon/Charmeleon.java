package pokemonBattleSimulator.pokemon;
import pokemonBattleSimulator.Pokemon;
import pokemonBattleSimulator.Stats;

public class Charmeleon extends Pokemon {
    public Charmeleon(String moves, int level) {
        super("charmeleon", "fire", moves, level, new Stats(39, 52, 43, 60, 50, 65));
    }
}