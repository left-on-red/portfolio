package pokemonBattleSimulator;
import java.util.ArrayList;

public class Pokemon {
	// don't know if it's a good idea to have a pool of constants be defined for every instance of the class, but I didn't really know how else to do it(I can't define static references to non-static variables/methods)
	private Types TYPES = new Types();
	private Moves MOVES = new Moves();

	private String name;
	private ArrayList<Type> types;
	private int level;
	private int maxHealth;
	private int health;
	private ArrayList<StatChange> statChanges;

	// easier for directly accessing all the stats, as opposed to having get methods for each(there's already get methods in the Stats class)
	public Stats stats;

	// would make a little more sense if this was an Array/ArrayList but a pokemon can have up to 4 moves at any given point in time so it doesn't really matter
	private Move move1;
	private Move move2;
	private Move move3;
	private Move move4;

	private boolean dead;

	public Pokemon(String name, String types, String moves, int level, Stats stats) {
		this.name = name;
		this.types = new ArrayList<Type>();
		this.level = level;
		this.stats = stats;
		this.dead = false;
		this.statChanges = new ArrayList<StatChange>();

		this.maxHealth = (int) this.calculateHealth();
		this.health = this.maxHealth;

		String[] typesArr = types.split(",");
		for (int i = 0; i < typesArr.length; i++) { if (!typesArr[i].equals("")) { this.types.add(TYPES.get(typesArr[i])); } }
		String[] movesArr = moves.split(",");
		for (int i = 0; i < movesArr.length; i++) { movesArr[i] = movesArr[i].trim(); }
		this.move1 = MOVES.get(movesArr[0]);
		this.move2 = MOVES.get(movesArr[1]);
		this.move3 = MOVES.get(movesArr[2]);
		this.move4 = MOVES.get(movesArr[3]);
	}

	public Move getMove1() { return this.move1; }
	public Move getMove2() { return this.move2; }
	public Move getMove3() { return this.move3; }
	public Move getMove4() { return this.move4; }
	public String getName() { return this.name; }
	public boolean isDead() { return this.dead; }

	public String[] getMoveStrings() {
		return new String[] {
			this.getMove1().getName(),
			this.getMove2().getName(),
			this.getMove3().getName(),
			this.getMove4().getName()
		};
	}

	public int getLevel() { return this.level; }

	public Type[] getTypes() {
		Type[] toReturn = new Type[this.types.size()];
		for (int i = 0; i < this.types.size(); i++) { toReturn[i] = this.types.get(i); }
		return toReturn;
	}

	public String[] getTypeStrings() {
		Type[] types = this.getTypes();
		String[] toReturn = new String[types.length];
		for (int t = 0; t < types.length; t++) { toReturn[t] = types[t].getName(); }
		return toReturn;
	}

	public double getEffectiveness(Type type) {
		double toReturn = 1;
		String[] typesArr = this.getTypeStrings();
		String[] effectives = type.getEffectives();
		String[] ineffectives = type.getIneffectives();

		for (int t = 0; t < typesArr.length; t++) {
			if (this.TYPES.get(typesArr[t]).getImmune().equals(type.getName())) { toReturn = 0; break; }
			for (int e = 0; e < effectives.length; e++) { if (typesArr[t].equals(effectives[e])) { toReturn = toReturn * 2; } }
			for (int i = 0; i < ineffectives.length; i++) { if (typesArr[t].equals(ineffectives[i])) { toReturn = toReturn / 2; } }
		}

		return toReturn;
	}

	// src: https://bulbapedia.bulbagarden.net/wiki/Statistic
	// (slight modification)
	private double calculateHealth() {
		return Math.floor(((this.stats.getHp() * 2 + (Math.sqrt(100) / 4)) * this.level) / 100) + level + 10;
	}

	public int getMaxHealth() { return this.maxHealth; }
	public int getHealth() { return this.health; }

	public boolean hasType(Type type) {
		boolean toReturn = false;
		Type[] typesArr = this.getTypes();
		for (int t = 0; t < typesArr.length; t++) { if (typesArr[t].getName().equals(type.getName())) { toReturn = true; } }
		return toReturn;
	}

	public void addStatChange(String stat, int stages, int turns) {
		StatChange statChange = new StatChange(stat, stages, turns);
		this.statChanges.add(statChange);
	}

	// Same Type Attack Bonus (do more damage if the pokemons type(s) is same as the move)
	public boolean isSTAB(Move move) { return this.hasType(move.getType()); }

	public void hurt(int damage) {
		this.health -= damage;
		if (this.health < 0) { this.health = 0; this.dead = true; }
	}

	public void heal(int toHeal) {
		this.health += toHeal;
		if (this.health > this.maxHealth) { this.health = this.maxHealth; }
	}

	public String executeMove(int move, Pokemon against) {
		// src: https://www.w3schools.com/java/java_switch.asp
		Move toUse;

		if (move == 1) { toUse = this.move1; }
		else if (move == 2) { toUse = this.move2; }
		else if (move == 3) { toUse = this.move3; }
		else if (move == 4) { toUse = this.move4; }
		else { toUse = this.move1; }

		String result = null;
		if (toUse.getPP() > 0) {

			// there's probably a better way of doing all these stat stage calculations
			double attackC = 0;
			double defenseC = 0;
			double specialAttackC = 0;
			double specialDefenseC = 0;
			double speedC = 0;

			for (int c = 0; c < this.statChanges.size(); c++) {
				StatChange change = this.statChanges.get(c);
				if (change.getTurns() == 0) { this.statChanges.remove(c); }
				else {
					change.passTurn();
					if (change.getStat().equals("attack")) { attackC += change.getStages(); }
					else if (change.getStat().equals("defense")) { defenseC += change.getStages(); }
					else if (change.getStat().equals("special attack")) { specialAttackC += change.getStages(); }
					else if (change.getStat().equals("special defense")) { specialDefenseC += change.getStages(); }
					else if (change.getStat().equals("speed")) { speedC += change.getStages(); }
				}
			}

			if (attackC > 6) { attackC = 6; }
			if (defenseC > 6) { defenseC = 6; }
			if (specialAttackC > 6) { specialAttackC = 6; }
			if (specialDefenseC > 6) { specialDefenseC = 6; }
			if (speedC > 6) { speedC = 6; }

			if (attackC < -6) { attackC = -6; }
			if (defenseC < -6) { defenseC = -6; }
			if (specialAttackC < -6) { specialAttackC = -6; }
			if (specialDefenseC < -6) { specialDefenseC = -6; }
			if (speedC < -6) { speedC = -6; }

			double attackM;
			double defenseM;
			double specialAttackM;
			double specialDefenseM;
			double speedM;

			if (attackC < 0) { attackM = 2 / (Math.abs(attackC) + 2); }
			else { attackM = (attackC + 2) / 2; }

			if (defenseC < 0) { defenseM = 2 / (Math.abs(defenseC) + 2); }
			else { defenseM = (defenseC + 2) / 2; }

			if (specialAttackC < 0) { specialAttackM = 2 / (Math.abs(specialAttackC) + 2); }
			else { specialAttackM = (specialAttackC + 2) / 2; }

			if (specialDefenseC < 0) { specialDefenseM = 2 / (Math.abs(specialDefenseC) + 2); }
			else { specialDefenseM = (specialDefenseC + 2) / 2; }

			if (speedC < 0) { speedM = 2 / (Math.abs(speedC) + 2); }
			else { speedM = (speedC + 2) / 2; }
			
			double accuracy = toUse.getAccuracy();
			boolean isLucky = Math.random() <= accuracy / 100;
			if (isLucky) {
				result = toUse.execute(this, against);
				if (result == null) {
					if (!toUse.getMoveClass().equals("status")) {
						AttackMove attack = (AttackMove) toUse;
						int power = attack.getPower();
						double attackStat;
						double defenseStat;
						if (attack.getMoveClass().equals("physical")) { attackStat = this.stats.getAttack() * attackM; defenseStat = against.stats.getDefense() * defenseM; }
						else { attackStat = this.stats.getSpecialAttack() * specialAttackM; defenseStat = against.stats.getSpecialDefense() * specialDefenseM; }
						double STAB = 1;
						if (this.isSTAB(attack)) { STAB = 1.5; }
						// src: https://bulbapedia.bulbagarden.net/wiki/Damage
						// (excluded most of the other modifiers)
						// effectiveness and STAB are the biggest modifiers and the least technical
						double effectiveness = against.getEffectiveness(attack.getType());
						double modifier = effectiveness * STAB;
						int damage = (int) (((((((2 * this.level) / 5) + 2) * power * attackStat / defenseStat) / 50) + 2) * modifier);
						against.hurt(damage);

						// switch wasn't compatible with doubles
						ArrayList<String> resultList = new ArrayList<String>();
						if (effectiveness == 0) { resultList.add("that didn't effect " + against.getName()); }
						else if (effectiveness == 0.5) { resultList.add("that wasn't very effective..."); }
						else if (effectiveness == 2) { resultList.add("that was super effective!"); }
						else if (effectiveness == 4) { resultList.add("that was extremely effective!"); }
						resultList.add("did " + damage + " damage to " + against.getName());
						if (against.isDead()) { resultList.add(against.getName() + " fainted"); }
						String[] resultArr = new String[resultList.size()];
						for (int r = 0; r < resultList.size(); r++) { resultArr[r] = resultList.get(r); }
						result = String.join("\n", resultArr);
					}
				}
			}

			else { result = "missed!"; }
		}

		return result;

	}
	
	public String toString() {
		String[] toReturn = {
			"name: " + this.name,
			"types: " + String.join(", ", this.getTypeStrings()),
			"moves: " + String.join(", ", this.getMoveStrings()),
			"stats: " + this.stats,
			"HP: " + this.health + "/" + this.maxHealth
		};
		//String toReturn = this.name;
		//Type[] types = this.getTypes();
		//for (int i = 0; i < types.length; i++) { toReturn += "\n" + types[i].toString(); }
		return String.join("\n", toReturn);
	}
}
