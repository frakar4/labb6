package labb6.carwash;

/**
 * Creates cars with unique incrementing ids
 * 
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class CarFactory {
	private int nextId;

	/**
	 * Returns a new car with an incremented id
	 * 
	 * @return a car
	 */
	public Car createCar() {
		return new Car(nextId++);
	}
}