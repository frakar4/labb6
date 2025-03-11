package labb6.simulator;

import java.util.Observer;

public abstract class SimView implements Observer {
	
	private SimState currentState;
	
	public SimView(SimState state) {
		this.currentState = state;
	}
	
	public abstract void start();
	public abstract void stop();
}
