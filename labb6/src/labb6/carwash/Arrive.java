package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;
import labb6.random.ExponentialRandomStream;

public class Arrive extends Event{
	private EventQueue queue;
	private CarWashState state;

	public Arrive(double time, CarWashState state) {
		super(time); //Uträknad tid här
		this.state = state;
		this.queue = queue;
	}

	@Override
	public void execute(EventQueue queue) {
		CarWashState.currentEvent = "ARRIVE";
		double nextTime = new ExponentialRandomStream(1).next();
		Arrive nextCarArrive = new Arrive(state);
		Car car = new Car();
		double leaveTime;
		if (state.fastAvailable()) {
			state.enterFastMachine();
			leaveTime = state.getFastWashTime();
		} else if (state.slowAvailable()) {
			state.enterSlowMachine();
			leaveTime = state.getSlowWashTime();
		} else if (!state.carQueueFull()) {
			state.addCarInQueue(car);
		} else {
			state.carRejected();
			return;
		}
		// notify state event happened
	}

}
