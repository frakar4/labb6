package labb6.carwash;

import java.util.Observable;

import labb6.simulator.Event;
import labb6.simulator.SimView;
import labb6.simulator.StartEvent;
import labb6.simulator.StopEvent;

/**
 * Draws the result of the carwash simulator
 * @author Edvin Ingemarsson
 * @author Frans Karlsson
 * @author Linnea Villskog
 */
public class CarWashView extends SimView {
	
	CarWashState state;
	int biggestId = 0;
	

	/**
	 * 
	 * @param state the CarWashState that the view observes
	 */
	public CarWashView(CarWashState state) {
		super(state);
		this.state = state;
	}

	/**
	 * Runs when the observed CarWashState gets modified
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			throw new IllegalArgumentException("CarWashView needs an Event.");
		
		String template = "%10.2f %10s %10s %8s %10s %9.2f %9.2f %10s %10s%n";
		String stopTemplate = "%10.2f %10s %19s %10s %9.2f %9.2f %10s %10s%n";
		String updateInfo;
		
		if (arg instanceof StartEvent) {
			StartEvent event = (StartEvent) arg;
			updateInfo = String.format("%10.2f %10s%n", event.getTime(), "Start");
		}
		else if (arg instanceof StopEvent) {
			StopEvent event = (StopEvent) arg;
			state.updateTotalQueueTime(event);
			updateInfo = String.format(stopTemplate, event.getTime(), "Stop", state.getAvailableFast(), state.getAvailableSlow(),
					state.getTotalIdleTime(), state.getTotalQueueTime(), state.getCarsInQueue(), state.getRejectedCars());
		}
		else if (arg instanceof Arrive) {
			Arrive event = (Arrive) arg;
			biggestId = (event.getCar().getCarID() > biggestId) ? event.getCar().getCarID() : biggestId;
			updateInfo = String.format(template, event.getTime(), "Arrive", event.getCar().getCarID(), state.getAvailableFast(), state.getAvailableSlow(),
					state.getTotalIdleTime(), state.getTotalQueueTime(), state.getCarsInQueue(), state.getRejectedCars());
		}
		else if (arg instanceof Leave) {
			Leave event = (Leave) arg;
			updateInfo = String.format(template, event.getTime(), "Leave", event.getCar().getCarID(), state.getAvailableFast(), state.getAvailableSlow(),
					state.getTotalIdleTime(), state.getTotalQueueTime(), state.getCarsInQueue(), state.getRejectedCars());
		}
		else {
			Event event = (Event) arg;
			updateInfo = String.format("%10.2f %10s%n", event.getTime(), "Unknown");
		}
		System.out.print(updateInfo);
	}

	/**
	 * Called before the simulator is started for setup info of the CarWash
	 */
	@Override
	public void beforeRun() {
		String titleTemplate = "%10s %10s %10s %10s %10s %10s %10s %10s %10s%n";
		String message = "Fast Machines: " + state.getTotalFast()+ "\n"
				+ "Slow Machines: " + state.getTotalSlow() + "\n"
				+ "Fast Distribution: (" + state.getFastDistribution()[0] + ", " + state.getFastDistribution()[1] + ")\n"
				+ "Slow Distribution: (" + state.getSlowDistribution()[0] + ", " + state.getSlowDistribution()[1] + ")\n"
				+ "Exponential distribution with lambda = " + state.getLambda() + "\n"
				+ "Seed = " + state.getSeed() + "\n"
				+ "Max Queue Size: " + state.getMaxQueueSize() + "\n"
				
				+ "--------------------------------------------------------------------------------------------------------";
		System.out.println(message);
		System.out.printf(titleTemplate, "Time", "Event", "Id", "Fast", "Slow", "IdleTime", "QueueTime", "QueueSize", "Rejected");
		
	}
	/**
	 * Called after the simulator is stopped for cleanup and summary of the CarWash
	 */
	@Override
	public void afterRun() {
		System.out.print("--------------------------------------------------------------------------------------------------------\n");
		System.out.printf("%-25s %5.2f%n", "Total idle machine time: ", state.getTotalIdleTime());
		System.out.printf("%-25s %5.2f%n", "Total queueing time: ", state.getTotalQueueTime());
		System.out.printf("%-25s %5.2f%n", "Mean queueing time: ", state.getTotalQueueTime()/(biggestId + 1 - state.getRejectedCars()));
		System.out.printf("%-25s %5s%n", "Rejected cars: ", state.getRejectedCars());
		
	}
	
	

}
