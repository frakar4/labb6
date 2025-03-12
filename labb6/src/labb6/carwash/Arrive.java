package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;

public class Arrive extends Event{

	public Arrive() {
		super(0); //Uträknad tid här
	}

	@Override
	public void execute(EventQueue queue, SimState state) {
		CarWashState.currentEvent = "ARRIVE";
		queue.addEvent(new Arrive());
		
		//Räkna ut leave-tider beroende på vad som är ledigt
	}

}
