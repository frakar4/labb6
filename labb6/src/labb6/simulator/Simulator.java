package labb6.simulator;

public class Simulator {

	private SimState simState;
	private SimView simView;
	private EventQueue eventQueue;

	public Simulator(SimState state, SimView view, EventQueue queue) {
		this.simState = state;
		this.simView = view;
		this.eventQueue = queue;
	}

	public void run() {
		simView.beforeRun();
		while (simState.isRunning() == true) {
			Event currentEvent = eventQueue.getNextEvent();
			currentEvent.Execute(eventQueue, simState);
		}
		simView.afterRun();
	}

}
