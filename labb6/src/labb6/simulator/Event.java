package labb6.simulator;

public abstract class Event implements Comparable<Event>{

	private double executeTime;
	
	public Event(double time) {
		this.executeTime = time;
	}
	
	public double getTime() {
		return executeTime;
	}
	
	@Override
	public int compareTo(Event event) {
		return Double.compare(this.executeTime, event.executeTime);
	}

	public abstract void execute();
}
