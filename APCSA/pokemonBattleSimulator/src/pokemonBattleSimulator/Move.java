package pokemonBattleSimulator;

public abstract class Move {
	private String name;
	private Type type;
	private int PP;
	private int maxPP;
	private int accuracy;
	private String moveClass;
	private static Types TYPES = new Types();
	
	public Move(String name, String type, int PP, int accuracy, String moveClass) {
		this.name = name;
		this.type = TYPES.get(type);
		this.maxPP = PP;
		this.PP = this.maxPP;
		this.accuracy = accuracy;
		this.moveClass = moveClass;
	}

	public String getName() { return this.name; }
	public Type getType() { return this.type; }
	public int getPP() { return this.PP; }
	public int getAccuracy() { return this.accuracy; }
	public String getMoveClass() { return this.moveClass; }

	public void setPP(int PP) { this.PP = PP; }

	// can be overridden for custom code specific to a certain move
	public String execute(Pokemon offense, Pokemon defense) { this.PP -= 1; return null; }

	public String toString() {
		String[] toReturn = {
			"name: " + this.name,
			"type: " + this.type.getName(),
			"class: " + this.moveClass,
			"PP: " + this.PP + "/" + this.maxPP
		};
		//String toReturn = this.name;
		//Type[] types = this.getTypes();
		//for (int i = 0; i < types.length; i++) { toReturn += "\n" + types[i].toString(); }
		return String.join("\n", toReturn);
	}
}
