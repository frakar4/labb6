package labb6.simulator;

import java.util.ArrayList;

public class EventQueue {
	
	private ArrayList<Event> eventQueue = new ArrayList<Event>();
	
	public ArrayList<Event> getEventList(){
		return eventQueue;
	}
	
	public Event getFirstEvent() {
		return eventQueue.remove(0);
	}
	
	public void addEvent(Event event) {
		eventQueue.add(event);
	}
}
