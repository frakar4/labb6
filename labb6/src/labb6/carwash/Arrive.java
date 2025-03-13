package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;

/**
 * Represents a car arriving at the car wash
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class Arrive extends Event {

	private CarWashState state;
	private Car car;

	/**
	 * Create a new start event
	 * @param time the time the event will occur on
	 * @param state the state that the event will modify
	 */
	public Arrive(double time, CarWashState state) {
		super(time); 
		this.state = state;
	}

	/**
	 * Will be run when the event is executed.
	 * Modifies the state for when a car arrives
	 */
	@Override
	public void execute(EventQueue queue) {
		queue.addEvent(new Arrive(state.newEventTime(), state));
		
		Car car = state.newCar();
		this.car = car;
		state.updateTotalIdleTime(this);
		state.updateTotalQueueTime(this);
		state.eventFinished(this);
		
		if (state.fastAvailable()) {
			
			state.enterFastMachine();
			car.setMachine(Machine.FAST);
			queue.addEvent(new Leave(this.getTime(),state.getFastWashTime(),car,state));
			
		} else if (state.slowAvailable()) {

			state.enterSlowMachine();
			car.setMachine(Machine.SLOW);
			queue.addEvent(new Leave(this.getTime(),state.getSlowWashTime(),car,state));
			
		} else if (state.getCarsInQueue() < state.getMaxQueueSize()) {
			state.addCarInQueue(car);
		} else {
			state.carRejected();
			return;
		}
	}
	
	/**
	 * Get the car this event refers to
	 * @return
	 */
	public Car getCar() {
		return car;
	}

}
