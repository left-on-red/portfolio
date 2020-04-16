package pokemonBattleSimulator;

import java.util.ArrayList;

public class Type {
	private String name;
	private String immune;
	private ArrayList<String> effectives;
	private ArrayList<String> ineffectives;
	
	/*
	 * pokemon type
	 * @param name	the name of the type
	 * @param strength	the strength of the type
	 * @param weakness	the weakness of the type
	 * @param immune 	the immunity of the type
	 */
	public Type(String name, String effectives, String ineffectives, String immune) {
		this.name = name;
		this.immune = immune;
		
		this.effectives = new ArrayList<String>();
		if (effectives != null) {
			String[] effectivesArr = effectives.split(",");
			for (int i = 0; i < effectivesArr.length; i++) { if (effectivesArr[i] != "") { this.effectives.add(effectivesArr[i].trim()); } }
		}
		
		this.ineffectives = new ArrayList<String>();
		if (ineffectives != null) {
			String[] ineffectivesArr = ineffectives.split(",");
			for (int i = 0; i < ineffectivesArr.length; i++) { if (ineffectivesArr[i] != "") { this.ineffectives.add(ineffectivesArr[i].trim()); } }
		}
	}
	
	public String getName() { return this.name; }
	public String getImmune() { return this.immune; }
	public String[] getEffectives() {
		String[] toReturn = new String[this.effectives.size()];
		for (int i = 0; i < this.effectives.size(); i++) { toReturn[i] = this.effectives.get(i); }
		return toReturn;
	}
	public String[] getIneffectives() {
		String[] toReturn = new String[this.ineffectives.size()];
		for (int i = 0; i < this.ineffectives.size(); i++) { toReturn[i] = this.ineffectives.get(i); }
		return toReturn;
	}

	public boolean isEffectiveAgainst(Type type) {
		boolean toReturn = false;
		String[] effectiveArr = this.getEffectives();
		for (int i = 0; i < effectiveArr.length; i++) { if (effectiveArr[i].equals(type.name)) { toReturn = true; } }
		return toReturn;
	}

	public boolean isIneffectiveAgainst(Type type) {
		boolean toReturn = false;
		String[] ineffectiveArr = this.getIneffectives();
		for (int i = 0; i < ineffectiveArr.length; i++) { if (ineffectiveArr[i].equals(type.name)) { toReturn = true; } }
		return toReturn;
	}

	public boolean isWeakAgainst(Type type) {
		boolean toReturn = false;
		String[] againstEffectives = type.getEffectives();
		for (int i = 0; i < againstEffectives.length; i++) { if (againstEffectives[i].equals(this.name)) { toReturn = true; } }
		return toReturn;
	}
	
	public double getEffectiveness(Pokemon pokemon) {
		double toReturn = 1;
		Type[] againstTypes = pokemon.getTypes();
		for (int i = 0; i < againstTypes.length; i++) {
			if (againstTypes[i].getImmune().equals(this.name)) { toReturn = 0; break; }
			else if (this.isEffectiveAgainst(againstTypes[i])) { toReturn = toReturn * 2; }
			else if (this.isEffectiveAgainst(againstTypes[i])) { toReturn = toReturn / 2; }
		}
		
		return toReturn;
	}

	public String toString() {
		String toReturn = this.name + "\neffectives: "
		+ String.join(", ", this.getEffectives()) + "\nineffectives: "
		+ String.join(", ", this.getIneffectives()) + "\nimmunity: " + this.immune;
		return toReturn;
	}
}
