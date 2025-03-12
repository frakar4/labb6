package labb6.simulator;

public class StopEvent extends Event {

	public StopEvent(double endTime) {
		super(endTime);
	}

	@Override
	public void Execute(EventQueue queue, SimState state) {
	}

}
