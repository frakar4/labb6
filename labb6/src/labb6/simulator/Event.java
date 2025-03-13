package labb6.simulator;

/**
 * A general event
 */
public abstract class Event implements Comparable<Event>{

	private double executeTime;
	
	/**
	 * Creates a new event
	 * @param time The time the event will be executed
	 */
	public Event(double time) {
		this.executeTime = time;
	}
	
	/**
	 * Get the time the event will be executed on
	 * @return the time the event will be executed
	 */
	public double getTime() {
		return executeTime;
	}
	
	/**
	 * Make two {@code Event}s be comparable by their time 
	 */
	@Override
	public int compareTo(Event event) {
		return Double.compare(this.executeTime, event.executeTime);
	}

	/**
	 * Execute the event, updating {@code SimState}
	 * @param queue the queue new {@code Event}s created here will be
	 * added to
	 */
	public abstract void execute(EventQueue queue);
}
