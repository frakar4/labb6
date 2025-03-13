package labb6.simulator;

/**
 * A general event that will always be run first
 */
public class StartEvent extends Event{
	
	SimState state;
	

	/**
	 * Create a new start event
	 * @param the state that the event should modify
	 */
	public StartEvent(SimState state) {
		super(0.00,"Start");
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