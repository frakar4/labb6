package labb6.simulator;

public class StartEvent extends Event{
	
	SimState state;
	

	public StartEvent(SimState state) {
		super(0.00,"Start");
		this.state = state;
	}

	@Override
	public void execute(EventQueue queue) {
		state.eventFinished(this);
	}

}