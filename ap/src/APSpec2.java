/*
	The main class of the application.
	Simulates a large intersection via the terminal
	complete with cars going in ever direction that
	cannot occupy the same space. This particular 
	simulation enables further customization and prints
	statistics upon termination.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.*;

public class APSpec2 extends Simulation
{
	private int nSlots = 20;
	private int mSlots = 10;
	private int maxRefreshes = 2000;
	private int refreshInterval = 20;
	private ArrayList<CarGenerator> generators;
	private static APSpec2 sim;
	private Statistics stats;
	private Grid grid;
	
	
	public static void main (String[] args)
	{	
		sim = new APSpec2();
		sim.start();
	}
	
	
	//Creates a grid and populates it.
	public void start ()
	{
		grid = new Grid (nSlots, mSlots);
		generators = new ArrayList<CarGenerator>();
		
		//Generates the actual generators. Sample values provided.
		generators.add(new EastGenerator(grid));
		generators.add(new WestGenerator(grid, 700));
		generators.add(new NorthGenerator(grid, 900));
		generators.add(new SouthGenerator(grid, 1200, 800, 5));
		generators.add(new SouthGeneratorFast(grid));
	
		//Starts printing the simulation.
		grid.refresh(maxRefreshes, refreshInterval, this);
	}
	
	
	//Prints the final statistics once the application finishes.
	//Serves as a callback for when the simulation ends.
	public void completed ()
	{
		stats = new Statistics(generators);
		System.out.println(stats.tabulate());
	}
}
