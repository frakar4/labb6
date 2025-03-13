package labb6.simulator;

/**
 * The actual simulator containing the main loop
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class Simulator {

	private EventQueue eventQueue;

	/**
	 * Create a new simulator
	 * @param state a state that the events will modify
	 * @param view a view that will observe the state
	 * @param queue a priorityQueue already containing a start and stop event
	 */
	public Simulator(SimState state, EventQueue queue) {
		this.eventQueue = queue;
	}

	/**
	 * Start the simulator, it will run taking the next event in the queue
	 * and execute it until it encounters a StopEvent
	 */
	public void run() {
		while (true) {
			Event currentEvent = eventQueue.getNextEvent();
			currentEvent.execute(eventQueue);
			if(currentEvent instanceof StopEvent) {
				break;
			}
		}
	}

}
