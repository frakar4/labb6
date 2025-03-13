package labb6;

import labb6.carwash.CarWashState;
import labb6.carwash.CarWashView;
import labb6.carwash.Arrive;
import labb6.simulator.Event;
import labb6.simulator.EventQueue;
import labb6.simulator.Simulator;
import labb6.simulator.StopEvent;
import labb6.simulator.StartEvent;

/**
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class MainSim {
	public static void main(String[] args) {
		CarWashState state = new CarWashState();
		CarWashView view = new CarWashView(state);
		state.addObserver(view);
		Event[] initialEvents = {new StartEvent(state), new StopEvent(15.0, state)};
		EventQueue queue = new EventQueue(initialEvents);
		queue.addEvent(new Arrive(state.newEventTime(), state));
		Simulator sim = new Simulator(state, view, queue);
		sim.run();
	}
}
