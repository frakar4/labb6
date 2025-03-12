package labb6.simulator;

import java.util.PriorityQueue;

public class EventQueue {

	private PriorityQueue<Event> sortedQueue = new PriorityQueue<Event>();
	
	public PriorityQueue<Event> getSortedQueue(){
		return sortedQueue;
	}
	
	public Event getNextEvent() {
		return sortedQueue.poll();
	}
	
	public void addEvent(Event event) {
		sortedQueue.add(event);
	}
}
