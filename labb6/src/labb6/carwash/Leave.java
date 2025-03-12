package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;

public class Leave extends Event{
	
	//TODO en del beräkningar att göra
	public Leave(double time) {
		super(time);
		
	}

	@Override
	public void execute(EventQueue queue, SimState state) {
		CarWashState.currentEvent = "LEAVE";
	}

}
