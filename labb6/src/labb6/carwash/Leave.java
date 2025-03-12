package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.SimState;

public class Leave extends Event{
	
	private Car car;
	private CarWashState carWashState;
	//TODO en del beräkningar att göra
	public Leave(double time, double washTime, Car car, SimState state) {
		super(time + washTime);
		this.car = car;
		carWashState = (CarWashState) state;
		
	}

	@Override
	public void execute(EventQueue queue) {
		CarWashState.currentEvent = "LEAVE";
		
		if(car.getMachine().equals("FAST")) {
			carWashState.leaveFastMachine();
			if(!carWashState.carQueueEmpty()) {
				Car firstCar = carWashState.getFirstCarFromQueue();
				firstCar.setMachine("FAST");
				carWashState.enterFastMachine();
				double washTime = carWashState.getFastWashTime();
				
				
			}
			
		} else if(car.getMachine().equals("SLOW")) {
			carWashState.leaveSlowMachine();
		}
	}

}
