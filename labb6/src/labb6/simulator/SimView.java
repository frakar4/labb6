package labb6.simulator;

import java.util.Observer;

/**
 * Draws the result of the simulator
 */
public abstract class SimView implements Observer {

	protected SimState currentState;

	/**
	 * Create a new instance of {@code SimView}
	 * @param the {@code SimState} that the view should read from
	 */
	public SimView(SimState state) {
		this.currentState = state;
	}

	/**
	 * Called before the simulator is started for setup
	 */
	public abstract void beforeRun();

	/**
	 * Called after the simulator is stopped for cleanup and summary 
	 */
	public abstract void afterRun();
}
