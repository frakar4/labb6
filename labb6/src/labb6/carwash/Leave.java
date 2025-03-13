package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;

public class Leave extends Event{
	
	private Car car;
	private CarWashState carWashState;
	//TODO en del beräkningar att göra
	public Leave(double time, double washTime, Car car, CarWashState state) {
		super(time + washTime, "Leave");
		this.car = car;
		carWashState = state;
		
	}

	@Override
	public void execute(EventQueue queue) {
		
		carWashState.updateTotalQueueTime(this);
		Car firstCar = null;
		double washTime = 0;
		
		if(car.getMachine().equals("FAST")) {
			carWashState.leaveFastMachine();
			if(!carWashState.carQueueEmpty()) {
				firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine("FAST");
				carWashState.enterFastMachine();
				washTime = carWashState.getFastWashTime();
			}
			
		} else if(car.getMachine().equals("SLOW")) {
			carWashState.leaveSlowMachine();
			if(!carWashState.carQueueEmpty()) {
				firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine("SLOW");
				carWashState.enterSlowMachine();
				washTime = carWashState.getSlowWashTime();
			}
		}
		queue.addEvent(new Leave(this.getTime(), washTime, firstCar,carWashState));
		carWashState.eventFinished(this);
		
	}
	
	public Car getCar() {
		return car;
	}
	
}
