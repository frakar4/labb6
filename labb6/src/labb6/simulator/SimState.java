package labb6.simulator;

import java.util.Observable;

/**
 * The central state of the simulator
 */
public class SimState extends Observable {
	
	/**
	 * Notify all observers that an event is finished and the state can be read
	 */
	public void eventFinished() {
		setChanged();
		notifyObservers();
	}
}
