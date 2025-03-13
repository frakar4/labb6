package labb6.simulator;

import java.util.Observable;

/**
 * The central state of the simulator
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class SimState extends Observable {
	
	/**
	 * Notify all observers that an event is finished and the state can be read
	 */

	private boolean runs = true;

	/**
	 * @return whether the simulator is currently running
	 */
	public boolean isRunning() {
		return runs;
	}

	/**
	 * Set whether the simulator is running or no
	 * @param the state the simulator will have
	 */
	public void setRunState(boolean newState) {
		runs = newState;
	}

	/**
	 * Notify all observers that an event is finished and the state can be read
	 */
	public void eventFinished(Event event) {
		setChanged();
		notifyObservers(event);
	}
}
