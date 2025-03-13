package labb6.carwash;

import java.util.Observable;

import labb6.simulator.Event;
import labb6.simulator.SimState;
import labb6.simulator.SimView;
import labb6.simulator.StartEvent;
import labb6.simulator.StopEvent;

public class CarWashView extends SimView {
	
	CarWashState state;
	int biggestId;
	

	public CarWashView(CarWashState state) {
		super(state);
		this.state = state;
		this.biggestId = 0;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			throw new IllegalArgumentException("CarWashView needs an Event.");
		
		String template = "%10.2f %10s %10s %10s %10s %10.2f %10.2f %10s %10s%n";
		String stopTemplate = "%10.2f %10s %21s %10s %10.2f %10.2f %10s %10s%n";
		String updateInfo = "";
		
		final String spacing = "\t";		
		if (arg instanceof StartEvent) {
			StartEvent event = (StartEvent) arg;
			updateInfo = String.format("%10.2f %10s%n", event.getTime(), event.getName());
		}
		else if (arg instanceof StopEvent) {
			StopEvent event = (StopEvent) arg;
			state.updateTotalQueueTime(event);
			updateInfo = String.format(stopTemplate, event.getTime(), event.getName(), state.getAvailableFast(), state.getAvailableSlow(),
					state.getTotalIdleTime(), state.getTotalQueueTime(), state.carsInQueue(), state.getRejectedCars());
		}
		else if (arg instanceof Arrive) {
			Arrive event = (Arrive) arg;
			biggestId = (event.getCar().getCarID() > biggestId) ? event.getCar().getCarID() : biggestId;
			updateInfo = String.format(template, event.getTime(), event.getName(), event.getCar().getCarID(), state.getAvailableFast(), state.getAvailableSlow(),
					state.getTotalIdleTime(), state.getTotalQueueTime(), state.carsInQueue(), state.getRejectedCars());
		}
		else if (arg instanceof Leave) {
			Leave event = (Leave) arg;
			updateInfo = String.format(template, event.getTime(), event.getName(), event.getCar().getCarID(), state.getAvailableFast(), state.getAvailableSlow(),
					state.getTotalIdleTime(), state.getTotalQueueTime(), state.carsInQueue(), state.getRejectedCars());
		}
		System.out.print(updateInfo);
	}

	@Override
	public void beforeRun() {
		String titleTemplate = "%10s %10s %10s %10s %10s %10s %10s %10s %10s%n";
		String spacing = "\t";
		String message = "Fast Machines: " + state.getTotalFast()+ "\n"
				+ "Slow Machines: " + state.getTotalSlow() + "\n"
				+ "Fast Distribution: (" + state.getFastDistribution()[0] + ", " + state.getFastDistribution()[1] + ")\n"
				+ "Slow Distribution: (" + state.getSlowDistribution()[0] + ", " + state.getSlowDistribution()[1] + ")\n"
				+ "Exponential distribution with lambda = " + state.getLambda() + "\n"
				+ "Seed = " + state.getSeed() + "\n"
				+ "Max Queue Size: " + state.getMaxQueueSize() + "\n"
				
				+ "-----------------------------------------------------------------------------";
		System.out.println(message);
		System.out.printf(titleTemplate, "Time", "Event", "Id", "Fast", "Slow", "IdleTime", "QueueTime", "QueueSize", "Rejected");
		
	}

	@Override
	public void afterRun() {
		System.out.print("-----------------------------------------------------------------------------\n");
		System.out.printf("%-10s %8.2f%n", "Total idle machine time: ", state.getTotalIdleTime());
		System.out.printf("%-10s %10.2f%n", "Total queueing time: ", state.getTotalQueueTime());
		System.out.printf("%-10s %10.2f%n", "Mean queueing time: ", state.getTotalQueueTime()/(biggestId + 1 - state.getRejectedCars()));
		System.out.printf("%-10s %13s%n", "Rejected cars: ", state.getRejectedCars());
		
	}
	
	

}
