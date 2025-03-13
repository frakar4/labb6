package labb6.simulator;

import java.util.Observer;

/**
 * Draws the result of the simulator
 */
public abstract class SimView implements Observer {

	protected SimState currentState;

	public SimView(SimState state) {
		this.currentState = state;
	}

	public abstract void beforeRun();

	public abstract void afterRun();
}
