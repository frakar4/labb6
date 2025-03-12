package labb6.simulator;

public class StopEvent extends Event {

	public StopEvent(double endTime) {
		super(endTime,"Stop");
	}

	@Override
	public void execute(EventQueue queue) {
	}

}
