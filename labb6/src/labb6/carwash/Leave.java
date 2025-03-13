package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;

/**
 * An event representing a car leaving the car wash
 */
public class Leave extends Event{
	
	private Car car;
	private CarWashState carWashState;
	
	/**
	 * Create a new leave event
	 * @param time the time the event will occur on
	 * @param washTime the amount of time the the car will spend in a machine
	 * @param car the car this event refers to
	 * @param state the state the event will modify
	 */
	public Leave(double time, double washTime, Car car, CarWashState state) {
		super(time + washTime);
		this.car = car;
		carWashState = state;
		
	}

	/**
	 * Will be run when the event is executed.
	 * Modifies the state for when a car leaves
	 */
	@Override
	public void execute(EventQueue queue) {
		
		carWashState.updateTotalQueueTime(this);
		Car firstCar = null;
		double washTime = 0;
		
		if(car.getMachine() == Machine.FAST) {
			carWashState.leaveFastMachine();
			if(!carWashState.carQueueEmpty()) {
				firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine(Machine.FAST);
				carWashState.enterFastMachine();
				washTime = carWashState.getFastWashTime();
				queue.addEvent(new Leave(this.getTime(), washTime, firstCar,carWashState));
			}
			
		} else if(car.getMachine() == Machine.SLOW) {
			carWashState.leaveSlowMachine();
			if(!carWashState.carQueueEmpty()) {
				firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine(Machine.SLOW);
				carWashState.enterSlowMachine();
				washTime = carWashState.getSlowWashTime();
				queue.addEvent(new Leave(this.getTime(), washTime, firstCar,carWashState));
			}
		}
		
		carWashState.eventFinished(this);
		
	}
	
	/**
	 * Get the car this event refers to
	 * @return the car this event refers to
	 */
	public Car getCar() {
		return car;
	}
	
}
