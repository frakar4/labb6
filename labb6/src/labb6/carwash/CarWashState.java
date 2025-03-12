package labb6.carwash;

import labb6.simulator.SimState;
import labb6.random.*;

public class CarWashState extends SimState{
	
	//TODO Kontrollera vad som ska vara publikt, privat och/eller static
	
	static String currentEvent = "";
	int rejectedCars = 0;
	int maxQueueSize = 5;
	
	double currentTime = 0;
	double totalQueueTime = 0;
	double totalIdleTime = 0;
	
	int totalFastMachines = 2;
	int totalSlowMachines = 2;
	int availableFastMachines = 2;
	int availableSlowMachines = 2;
	
	
	double fastLowerDist = 2.8;
	double fastUpperDist = 4.6;
	double slowLowerDist = 3.5;
	double slowUpperDist = 6.7;
	
	double lambda = 2;
	int seed = 1234;
	
	private UniformRandomStream fastMachineTime = new UniformRandomStream(fastLowerDist,fastUpperDist,seed);
	private UniformRandomStream slowMachineTime = new UniformRandomStream(slowLowerDist,slowUpperDist,seed);
	private ExponentialRandomStream nextArrivalTime = new ExponentialRandomStream(lambda,seed);
	
	//TODO Saknas några getters och setters
	double newEventTime() {
		currentTime += nextArrivalTime.next();
		return currentTime;
	}
	
	double getFastWashTime() {
		return fastMachineTime.next();
	}
	
	double getSlowWashTime() {
		return slowMachineTime.next();
	}
	
	boolean fastAvailable() {
		return availableFastMachines > 0;
	}
	
	boolean slowAvailable() {
		return availableSlowMachines > 0;
	}
	
	int getRejectedCars() {
		return rejectedCars;
	}
	
	int getMaxQueueSize() {
		return maxQueueSize;
	}
	
	
}
