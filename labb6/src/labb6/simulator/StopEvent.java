package labb6.simulator;

public class StopEvent extends Event {
	
	SimState state;

	public StopEvent(double endTime, SimState state) {
		super(endTime,"Stop");
		this.state = state;
	}

	@Override
	public void execute(EventQueue queue) {
		state.eventFinished(this);
	}

}
