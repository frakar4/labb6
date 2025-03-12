package labb6.simulator;

public abstract class Event implements Comparable<Event>{

	private double executeTime;
	
	private String name;
	
	public Event(double time, String name) {
		this.executeTime = time;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public double getTime() {
		return executeTime;
	}
	
	@Override
	public int compareTo(Event event) {
		return Double.compare(this.executeTime, event.executeTime);
	}

	public abstract void execute(EventQueue queue);
}
