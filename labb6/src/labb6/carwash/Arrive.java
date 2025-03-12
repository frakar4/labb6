package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;

public class Arrive extends Event{

	private CarWashState state;

	public Arrive(double time, CarWashState state) {
		super(time); //Uträknad tid här
		this.state = state;
	}

	@Override
	public void execute(EventQueue queue) {
		CarWashState.currentEvent = "ARRIVE";
<<<<<<< HEAD
		double nextTime = new ExponentialRandomStream(1).next();
		Arrive nextCarArrive = new Arrive(state);
=======
		queue.addEvent(new Arrive(state.newEventTime(), state));
		
>>>>>>> branch 'main' of git@github.com:frakar4/labb6.git
		Car car = new Car();
		
		if (state.fastAvailable()) {
			
			state.enterFastMachine();
			car.setMachine("FAST");
			queue.addEvent(new Leave(this.getTime(),state.getFastWashTime(),car,state));
			
		} else if (state.slowAvailable()) {
			
			state.enterSlowMachine();
			car.setMachine("SLOW");
			queue.addEvent(new Leave(this.getTime(),state.getSlowWashTime(),car,state));
			
		} else if (!state.carQueueFull()) {
			state.addCarInQueue(car);
		} else {
			state.carRejected();
			return;
		}
		// notify state event happened
	}

}
