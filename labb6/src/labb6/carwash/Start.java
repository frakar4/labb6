package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;

public class Start extends Event{

	public Start() {
		super(0.00);
	}

	@Override
	public void Execute(EventQueue queue, SimState state) {
		CarWashState.currentEvent = "START";
		state.setRunState(true);
		queue.addEvent(new Arrive());
		state.observable();
	}

}
