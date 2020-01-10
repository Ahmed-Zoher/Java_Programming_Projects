package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public class FoeCell extends Cell {
	private NonPlayableFighter foe;

	public FoeCell(NonPlayableFighter foe) {
		this.foe = foe;
	}

	public NonPlayableFighter getFoe() {
		return foe;
	}

	@Override
	public void onStep() {
		if(foe.isStrong()){
		notifyOnFoeEncountered(foe);}
		
	}

	@Override
	public String toString() {
		if (foe.isStrong()) {
			return "[b]";
		} else {
			return "[w]";
		}
	}
}
