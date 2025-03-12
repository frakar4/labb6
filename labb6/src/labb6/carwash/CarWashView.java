package labb6.carwash;

import java.util.Observable;

import labb6.simulator.Event;
import labb6.simulator.SimState;
import labb6.simulator.SimView;
import labb6.simulator.StartEvent;

public class CarWashView extends SimView {
	
	CarWashState state;

	//TODO någon formatter
	public CarWashView(CarWashState state) {
		super(state);
		this.state = state;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Event) {
			Event event = (Event) arg;
			final String spacing = "\t\t";
			
			switch (event.getName()) {
			case "Start":
				System.out.println("\t\t" + event.getTime() + "\t\t"  + event.getName());
				break;
			
			case "Stop":
				System.out.println("\t\t" + event.getTime() + "\t\t"  + event.getName());
				break;
				
			default:
				System.out.println(spacing + event.getTime() + spacing + event.getName() + "0");
				printStateInfo(state.getAvailableFast(),
						state.getAvailableSlow(),
						state.getTotalIdleTime(),
						state.getTotalQueueTime(),
						state.getQueueSize(),
						state.getRejectedCars());
			}
			
			
		}
	}

	@Override
	public void beforeRun() {
		System.out.println("Fast Machines: " + state.getTotalFast());
		System.out.println("Slow Machines: " + state.getTotalSlow());
		System.out.println("Fast Distribution: (" + state.getFastDistribution()[0] + ", " + state.getFastDistribution()[1] + ")");
		
	}

	@Override
	public void afterRun() {
		
		
	}
	
	private void printStateInfo(int fastMachines, int slowMachines, double idleTime, double queueTime, int queueSize, int rejectedCars) {
		final String spacing = "\t\t";
		System.out.print(spacing + fastMachines
				+ spacing + slowMachines
				+ spacing + idleTime
				+ spacing + queueTime
				+ spacing + queueSize
				+ spacing + rejectedCars);
	}
	
	

}
