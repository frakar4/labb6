package labb6.simulator;

import java.util.Observable;

public class SimState extends Observable {

	private boolean runs = true;

	public boolean isRunning() {
		return runs;
	}

	public void setRunState(boolean newState) {
		runs = newState;
	}

	public void eventFinished() {
		setChanged();
		notifyObservers();
	}
}
