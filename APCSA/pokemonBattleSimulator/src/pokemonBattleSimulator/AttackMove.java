package pokemonBattleSimulator;

public class AttackMove extends Move {
	private int power;
	
	/*
	 * @param name the name of the move
	 * @param PP the PP of the move
	 * @param accuracy the accuracy of the move
	 * @param power the power of the move
	*/
	public AttackMove(String name, String type, int PP, int accuracy, int power, String moveClass) {
		super(name, type, PP, accuracy, moveClass);
		this.power = power;
	}

	public int getPower() { return this.power; }
}
