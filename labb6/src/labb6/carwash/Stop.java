package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;

public class Stop extends Event {

	public Stop() {
		super(15.00);
	}

	@Override
	public void Execute(EventQueue queue, SimState state) {
		CarWashState.currentEvent = "STOP";
		state.setRunState(false);
		state.observable();
	}

}
