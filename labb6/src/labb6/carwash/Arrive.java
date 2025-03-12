package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;
import labb6.random.ExponentialRandomStream;

public class Arrive extends Event{
	private EventQueue queue;
	private CarWashState state;

	public Arrive(EventQueue queue, CarWashState state) {
		super(0.0); //Uträknad tid här
		this.state = state;
		this.queue = queue;
	}

	@Override
	public void execute(EventQueue queue) {
		CarWashState.currentEvent = "ARRIVE";
		double nextTime = new ExponentialRandomStream(1).next();
		Arrive nextCarArrive = new Arrive(queue, state);
		Car car = new Car();
		if (state.fastAvailable()) {
		}
		
		
		//Räkna ut leave-tider beroende på vad som är ledigt
	}

}
