package labb6.carwash;

import labb6.simulator.Event;
import labb6.simulator.SimState;

import java.util.Deque;
import java.util.LinkedList;

import labb6.random.*;

/**
 * The central state of the car wash which the event will modify and the 
 * view will read
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class CarWashState extends SimState{
	
	private int rejectedCars = 0;
	private int maxQueueSize = 5;
	Deque<Car> carQueue = new LinkedList<Car>();

	private double currentTime = 0;
	private double totalQueueTime = 0;
	private double totalIdleTime = 0;
	private double previousQueueTime = 0;
	private double previousIdleTime = 0;
	private final int totalFastMachines = 2;
	private final int totalSlowMachines = 2;
	private int availableFastMachines = totalFastMachines;
	private int availableSlowMachines = totalSlowMachines;

	private double fastLowerDist = 2.8;
	private double fastUpperDist = 4.6;
	private double slowLowerDist = 3.5;
	private double slowUpperDist = 6.7;

	private double lambda = 2;
	private int seed = 1234;

	private UniformRandomStream fastMachineTime = new UniformRandomStream(fastLowerDist, fastUpperDist, seed);
	private UniformRandomStream slowMachineTime = new UniformRandomStream(slowLowerDist, slowUpperDist, seed);
	private ExponentialRandomStream nextArrivalTime = new ExponentialRandomStream(lambda, seed);

	/**
	 * Returns the maximum amount of cars allowed in the queue
	 * 
	 * @return the max number of cars in the queue
	 */
	int getMaxQueueSize() {
		return maxQueueSize;
	}

	/**
	 * Return whether the queue is full
	 * 
	 * @return a boolean
	 */
	boolean carQueueFull() {
		return carQueue.size() == maxQueueSize;
	}

	/**
	 * Add a car to the queue
	 * 
	 * @param car the car to be added
	 */
	void addCarInQueue(Car car) {
		carQueue.add(car);
	}

	/**
	 * Returns the first car in the queue
	 * 
	 * @return a car
	 */
	Car getFirstCarFromQueue() {
		return carQueue.poll();
	}

	/**
	 * Returns the time that the next car will arrive on
	 * 
	 * @return a double
	 */
	public double newEventTime() {
		currentTime += nextArrivalTime.next();
		return currentTime;
	}

	/**
	 * Return the time to wash with a fast machine
	 * 
	 * @return a boolean
	 */
	double getFastWashTime() {
		return fastMachineTime.next();
	}

	/**
	 * Return the time to wash with a slow machine
	 * 
	 * @return
	 */
	double getSlowWashTime() {
		return slowMachineTime.next();
	}

	/**
	 * Return whether a fast machine is available
	 * 
	 * @return a boolean
	 */
	boolean fastAvailable() {
		return availableFastMachines > 0;
	}

	/**
	 * Return whether a slow machine is available
	 * 
	 * @return a boolean
	 */
	boolean slowAvailable() {
		return availableSlowMachines > 0;
	}
	
	
	
	

	/**
	 * Return how many fast machines are currently available
	 * 
	 * @return an integer
	 */
	public int getAvailableFast() {
		return availableFastMachines;
	}

	/**
	 * Return how many slow machines are currently available
	 * 
	 * @return an integer
	 */
	public int getAvailableSlow() {
		return availableSlowMachines;
	}

	/**
	 * Return the total amount of time a machine has been empty
	 * 
	 * @return a double
	 */
	public double getTotalIdleTime() {
		return totalIdleTime;
	}

	/**
	 * The total amount of time a car has been waiting in the queue
	 * 
	 * @return a double
	 */
	public double getTotalQueueTime() {
		return totalQueueTime;
	}

	/**
	 * Return how many cars are are waiting in the queue
	 * 
	 * @return an integer
	 */
	public int getCarsInQueue() {
		return carQueue.size();
	}

	/**
	 * Return how many cars have been rejected
	 * 
	 * @return an integer
	 */
	public int getRejectedCars() {
		return rejectedCars;
	}

	/**
	 * Return the total amount of fast machines
	 * 
	 * @return an integer
	 */
	public int getTotalFast() {
		return totalFastMachines;
	}

	/**
	 * Return the total amount of slow machines
	 * 
	 * @return an integer
	 */
	public int getTotalSlow() {
		return totalSlowMachines;
	}

	/**
	 * Get the upper and lower distribution of the time a fast machine can take
	 * 
	 * @return an array containing the lower and upper distribution as doubles
	 */
	public double[] getFastDistribution() {
		return new double[] { fastLowerDist, fastUpperDist };
	}

	/**
	 * Get the upper and lower distribution of the time a slow machine can take
	 * 
	 * @return an array containing the lower and upper distribution as doubles
	 */
	public double[] getSlowDistribution() {
		return new double[] { slowLowerDist, slowUpperDist };
	}

	/**
	 * Returns the lambra of the exponential random stream for new arrival time
	 * 
	 * @return a double
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * Returns the seed the random methods uses
	 * 
	 * @return an integer
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * Reject a car, incrementing rejectedCars
	 */
	void carRejected() {
		rejectedCars++;
	}
	
	
	
	

	/**
	 * Update the total time a machine has been idling
	 * 
	 * @param an event
	 */
	void updateTotalIdleTime(Event event) {
		totalIdleTime += (event.getTime() - previousIdleTime) * (availableFastMachines + availableSlowMachines);
		previousIdleTime = event.getTime();
	}

	/**
	 * Update the total time a car has been waiting in queue
	 * 
	 * @param event
	 */
	void updateTotalQueueTime(Event event) {
		totalQueueTime += (event.getTime() - previousQueueTime) * (carQueue.size());
		previousQueueTime = event.getTime();
	}

	/**
	 * A car leaves a fast machine, leaving a machine available
	 */
	void leaveFastMachine() {
		availableFastMachines++;
	}

	/**
	 * A car enters a fast machine, leaving a machine unavailable
	 */
	void enterFastMachine() {
		availableFastMachines--;
	}

	/**
	 * A car leaves a slow machine, leaving a machine available
	 */
	void leaveSlowMachine() {
		availableSlowMachines++;
	}

	/**
	 * A car enters a slow machine, leaving a machine unavailable
	 */
	void enterSlowMachine() {
		availableSlowMachines--;
	}

}
