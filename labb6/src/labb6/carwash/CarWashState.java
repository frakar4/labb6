package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.SimState;

import java.util.Deque;
import java.util.LinkedList;

import labb6.random.*;

public class CarWashState extends SimState{
	
	//TODO Kontrollera vad som ska vara publikt, privat och/eller static
	
	static String currentEvent = "";
	private int rejectedCars = 0;
	private int maxQueueSize = 5;
	Deque<Car> carQueue = new LinkedList<Car>();
	
	private double currentTime = 0;
	private double totalQueueTime = 0;
	private double totalIdleTime = 0;
	private double previousQueueTime = 0;
	private double previousIdleTime = 0;
	
	private	final int totalFastMachines = 2;
	private final int totalSlowMachines = 2;
	private int availableFastMachines = totalFastMachines;
	private int availableSlowMachines = totalSlowMachines;
	
	
	private double fastLowerDist = 2.8;
	private double fastUpperDist = 4.6;
	private double slowLowerDist = 3.5;
	private double slowUpperDist = 6.7;
	
	private double lambda = 2;
	private int seed = 1234;
	
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
	
	public double newEventTime() {
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
	
	
	
	public int getAvailableFast() {
		return availableFastMachines;
	}
	public int getAvailableSlow() {
		return availableSlowMachines;
	}
	public double getTotalIdleTime() {
		return totalIdleTime;
	}
	public double getTotalQueueTime() {
		return totalQueueTime;
	}
	public int getQueueSize() {
		return carQueue.size();
	}
	public int getRejectedCars() {
		return rejectedCars;
	}
	
	
	public int getTotalFast() {
		return totalFastMachines;
	}
	public int getTotalSlow() {
		return totalSlowMachines;
	}
	public double[] getFastDistribution() {
		return new double[] {fastLowerDist, fastUpperDist};
	}
	public double[] getSlowDistribution() {
		return new double[] {slowLowerDist, slowUpperDist};
	}
	public double getLambda() {
		return lambda;
	}
	public int getSeed() {
		return seed;
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
		availableFastMachines++;
	}
	
	void enterFastMachine() {
		availableFastMachines--;
	}
	
	void leaveSlowMachine() {
		availableSlowMachines++;
	}
	
	void enterSlowMachine() {
		availableSlowMachines--;
	}
	
}
