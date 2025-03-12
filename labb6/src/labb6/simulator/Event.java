package labb6.simulator;

public abstract class Event implements Comparable<Event>{

	private double executeTime;
	
	public Event(double time) {
		this.executeTime = time;
	}
	
	@Override
	public int compareTo(Event event) {
		return Double.compare(this.executeTime, event.executeTime);
	}

	public abstract void Execute(EventQueue queue, SimState state);
}
