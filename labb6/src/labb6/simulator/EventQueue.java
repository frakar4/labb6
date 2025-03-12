package labb6.simulator;

import java.util.PriorityQueue;

public class EventQueue {
	PriorityQueue<Event> queue;
	public EventQueue(Event[] initialEvents) {
		queue = new PriorityQueue<Event>();
		for (Event e : initialEvents) {
			queue.add(e);
		}
	}
	
	public PriorityQueue<Event> getSortedQueue(){
		return queue;
	}
	
	public Event getNextEvent() {
		return queue.poll();
	}
	
	public void addEvent(Event event) {
		queue.add(event);
	}
}
