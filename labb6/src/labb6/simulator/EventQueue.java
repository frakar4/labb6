package labb6.simulator;

import java.util.PriorityQueue;

/**
 * A sorted list of events where the next event can be called and
 * new ones can be added to
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class EventQueue {
	private PriorityQueue<Event> queue;
	
	/**
	 * Create a new {@code EventQueue}
	 * @param initialEvents initial events that the queue will contain
	 */
	public EventQueue(Event[] initialEvents) {
		queue = new PriorityQueue<Event>();
		for (Event e : initialEvents) {
			queue.add(e);
		}
	}
	
	/**
	 * Get the next event that should occur, as in 
	 * the event with the lowest time
	 * @return the event with the lowest time
	 */
	public Event getNextEvent() {
		return queue.poll();
	}
	
	/**
	 * Add a new {@code Event} to the queue
	 * @param event the {@code Event} to be added
	 */
	public void addEvent(Event event) {
		queue.add(event);
	}
}
