package labb6.simulator;

/**
 * The actual simulator containing the main loop
 */
public class Simulator {

	private SimState simState;
	private SimView simView;
	private EventQueue eventQueue;

	/**
	 * Create a new simulator
	 * @param state a state that the events will modify
	 * @param view a view that will observe the state
	 * @param queue a priorityQueue already containing a start and stop event
	 */
	public Simulator(SimState state, SimView view, EventQueue queue) {
		this.simState = state;
		this.simView = view;
		this.eventQueue = queue;
	}

	/**
	 * Start the simulator, it will run taking the next event in the queue
	 * and execute it until it encounters a StopEvent
	 */
	public void run() {
		simView.beforeRun();
		while (true) {
			Event currentEvent = eventQueue.getNextEvent();
			currentEvent.execute(eventQueue);
			if(currentEvent instanceof StopEvent) {
				currentEvent.execute(eventQueue);
				break;
			}
		}
		simView.afterRun();
	}

}
