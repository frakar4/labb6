package labb6.simulator;

public abstract class Event {
	
	public double time;
	
	public abstract void Execute(SortedSequence queue, SimState state);
}
