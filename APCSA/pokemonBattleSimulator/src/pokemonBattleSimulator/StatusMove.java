package pokemonBattleSimulator;

public class StatusMove extends Move {
	public StatusMove(String name, String type, int PP, int accuracy) {
		super(name, type, PP, accuracy, "status");
	}

	@Override
	public String execute(Pokemon offense, Pokemon defense) {
		return "";
	}
}
