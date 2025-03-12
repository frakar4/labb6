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
		
		if(car.getMachine().equals("FAST")) {
			carWashState.leaveFastMachine();
			if(!carWashState.carQueueEmpty()) {
				Car firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine("FAST");
				carWashState.enterFastMachine();
				double washTime = carWashState.getFastWashTime();
				queue.addEvent(new Leave(this.getTime(), washTime, firstCar,carWashState));
			}
			
		} else if(car.getMachine().equals("SLOW")) {
			carWashState.leaveSlowMachine();
			if(!carWashState.carQueueEmpty()) {
				Car firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine("SLOW");
				carWashState.enterSlowMachine();
				double washTime = carWashState.getSlowWashTime();
				queue.addEvent(new Leave(this.getTime(), washTime, firstCar,carWashState));
			}
		}
		carWashState.eventFinished(this);
		
	}
	
}
