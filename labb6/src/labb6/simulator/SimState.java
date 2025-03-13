package labb6.simulator;

import java.util.Observable;

/**
 * The central state of the simulator
 */
public class SimState extends Observable {
	
	/**
	 * Notify all observers that an event is finished and the state can be read
	 */
	public void eventFinished() {}

	private boolean runs = true;

	public boolean isRunning() {
		return runs;
	}

	public void setRunState(boolean newState) {
		runs = newState;
	}

	public void eventFinished(Event event) {
		setChanged();
		notifyObservers(event);
	}
}
