package pokemonBattleSimulator;
public class StatChange {
    private String stat;
    private int stages;
    private int turnsLeft;

    public StatChange(String stat, int stages, int turns) {
        this.stat = stat;
        this.stages = stages;
        this.turnsLeft = turns;
    }

    public void passTurn() {
        this.turnsLeft -= 1;
        if (this.turnsLeft < 0) { this.turnsLeft = 0; }
    }

    public String getStat() { return this.stat; }
    public int getStages() { return this.stages; }
    public int getTurns() { return this.turnsLeft; }
}