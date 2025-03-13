package labb6.simulator;

/**
 * A general event that will stop the simulator when encountered
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class StopEvent extends Event {
	
	SimState state;

	/**
	 * Create a new stop event
	 * @param The time the stop event will happen as in when the simulator will stop
	 * @param the state that the event will modify
	 */
	public StopEvent(double endTime, SimState state) {
		super(endTime);
		this.state = state;
	}

	/**
	 * Does nothing but notify that an event happened
	 */
	@Override
	public void execute(EventQueue queue) {
		state.eventFinished(this);
	}


}
