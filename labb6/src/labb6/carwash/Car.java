package labb6.carwash;

/**
 * A car that can arrive and leave and take up space in 
 * a queue or a machine
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class Car {
	
	private static int nextId = 0;
	private int id;
	private Machine selectedMachine;
	
	/**
	 * Create a new car with an unique id
	 */
	public Car() {
		this.id = nextId++;
	}
	
	/**
	 * @return The id of the car
	 */
	public int getCarID() {
		return id;
	}
	
	/**
	 * Set what type of machine the car is currently being washed in
	 * @param machine the machine the car is in
	 */
	public void setMachine(Machine machine) {
		this.selectedMachine = machine;
	}
	
	/**
	 * Get the machine the car is currently being washed in
	 * @return the machine
	 */
	public Machine getMachine() {
		return selectedMachine;
	}
}
