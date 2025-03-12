package labb6.carwash;
public class Car {
	private static int nextId = 0;
	private int id;
	public Car() {
		this.id = nextId++;
	}
}
