package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.SimState;

import java.util.Deque;
import java.util.LinkedList;

import labb6.random.*;

public class CarWashState extends SimState{
	
	//TODO Kontrollera vad som ska vara publikt, privat och/eller static
	
	static String currentEvent = "";
	int rejectedCars = 0;
	int maxQueueSize = 5;
	Deque<Car> carQueue = new LinkedList<Car>();
	
	double currentTime = 0;
	double totalQueueTime = 0;
	double totalIdleTime = 0;
	double previousQueueTime = 0;
	double previousIdleTime = 0;
	
	int totalFastMachines = 2;
	int totalSlowMachines = 2;
	int availableFastMachines = 2;
	int availableSlowMachines = 2;
	
	
	double[] fastDistribution = {2.8, 4.6};
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
	int getMaxQueueSize() {
		return maxQueueSize;
	}
	
	boolean carQueueEmpty() {
		return carQueue.size() == 0;
	}
	
	boolean carQueueFull() {
		return carQueue.size() == maxQueueSize;
	}
	
	void addCarInQueue(Car car) {
		carQueue.add(car);
	}

	Car getFirstCarFromQueue() {
		return carQueue.poll();
	}
	
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
	
	
	
	int getAvailableFast() {
		return availableFastMachines;
	}
	int getAvailableSlow() {
		return availableSlowMachines;
	}
	double getTotalIdleTime() {
		return totalIdleTime;
	}
	double getTotalQueueTime() {
		return totalQueueTime;
	}
	int getQueueSize() {
		return carQueue.size();
	}
	int getRejectedCars() {
		return rejectedCars;
	}
	
	int getTotalFast() {
		return totalFastMachines;
	}
	int getTotalSlow() {
		return totalSlowMachines;
	}
	double[] getFastDistribution() {
		return fastDistribution;
	}
	
	
	
	void carRejected() {
		rejectedCars++;
	}
	
	void updateTotalIdleTime(Event event) {
		totalIdleTime += (event.getTime() - previousIdleTime)*(availableFastMachines + availableSlowMachines);
		previousIdleTime = event.getTime();
	}
	
	void updateTotalQueueTime(Event event) {
		totalQueueTime += (event.getTime() - previousQueueTime)*(carQueue.size());
		previousQueueTime = event.getTime();
	}
	
	void leaveFastMachine() {
		availableFastMachines--;
	}
	
	void enterFastMachine() {
		availableFastMachines++;
	}
	
	void leaveSlowMachine() {
		availableSlowMachines--;
	}
	
	void enterSlowMachine() {
		availableSlowMachines++;
	}
	
}
