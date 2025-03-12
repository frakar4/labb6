package labb6.simulator;

import java.util.Observer;

public abstract class SimView implements Observer {

	protected SimState currentState;

	public SimView(SimState state) {
		this.currentState = state;
	}

	public abstract void beforeRun();

	public abstract void afterRun();
}
