package pokemonBattleSimulator;

public class Stats {
	private int hp;
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;
	
	public Stats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
	}
	
	public int getHp() { return this.hp; }
	public int getAttack() { return this.attack; }
	public int getDefense() { return this.defense; }
	public int getSpecialAttack() { return this.specialAttack; }
	public int getSpecialDefense() { return this.specialDefense; }
	public int getSpeed() { return this.speed; }

	public void setHp(int val) { this.hp = val; }
	public void setAttack(int val) { this.attack = val; }
	public void setDefense(int val) { this.defense = val; }
	public void setSpecialAttack(int val) { this.specialAttack = val; }
	public void setSpecialDefense(int val) { this.specialDefense = val; }
	public void setSpeed(int val) { this.speed = val; }

	public String toString() {
		String[] toReturn = {
			"HP: " + this.hp,
			"ATK: " + this.attack,
			"DEF: " + this.defense,
			"SP.ATK: " + this.specialAttack,
			"SP.DEF: " + this.specialDefense,
			"SPD: " + this.speed
		};

		return String.join(", ", toReturn);
	}
}
