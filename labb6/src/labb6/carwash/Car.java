package labb6.carwash;
public class Car {
	
	private static int nextId = 0;
	private int id;
	private String fromMachine = "";
	
	public Car() {
		this.id = nextId++;
	}
	
	public int getCarID() {
		return id;
	}
	
	public void setMachine(String machine) {
		this.fromMachine = machine;
	}
	
	public String getMachine() {
		return fromMachine;
	}
}
