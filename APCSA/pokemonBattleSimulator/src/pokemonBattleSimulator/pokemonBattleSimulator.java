package pokemonBattleSimulator;

//import pokemonBattleSimulator.pokemon.charmander;
import pokemonBattleSimulator.pokemon.*;

public class pokemonBattleSimulator {
	public static void main(String[] args) {
		Pokemon charmander = new Charmander("water jet, pound, swords dance, pound", 100);
		Pokemon other = new Charmander("pound, pound, pound, pound", 500);
		System.out.println(charmander.executeMove(2, other));
		System.out.println(charmander.executeMove(3, other));
		System.out.println(charmander.executeMove(2, other));
		System.out.println(charmander.executeMove(2, other));
		System.out.println(charmander.executeMove(2, other));
		System.out.println(charmander.executeMove(2, other));
		//System.out.println(Pokemon.calculateHealth(charmander.stats.getHp(), charmander.getLevel()));
		//System.out.println(charmander.executeMove(3, other));
		//System.out.println(charmander.executeMove(2, other));
		//System.out.println(charmander.executeMove(2, other));
		//System.out.println(charmander.getMove4());
		//Pokemon charmander = new charmander();
		//System.out.println(charmander.toString());
		//Move move = new AttackMove("scratch", "normal", 10);
		//System.out.println(move.getClassName());

	}
}