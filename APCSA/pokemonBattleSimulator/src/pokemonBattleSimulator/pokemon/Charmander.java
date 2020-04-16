package pokemonBattleSimulator.pokemon;
import pokemonBattleSimulator.Pokemon;
import pokemonBattleSimulator.Stats;

public class Charmander extends Pokemon {
    public Charmander(String moves, int level) {
        super("charmander", "fire", moves, level, new Stats(39, 52, 43, 60, 50, 65));
    }
}