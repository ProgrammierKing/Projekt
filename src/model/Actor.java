package model;

import io.Statistics;

import java.util.ArrayList;

import controller.Simulation;

/**
 * Superclass for all Actors
 * 
 * @author Jaeger, Schmidt
 * @version 2016-07-07
 */
public abstract class Actor extends Thread {
	
	/** x position of the actor */
	protected int xPos;
	
	/** y position of the actor */
	protected int yPos;
		
	/** label */
	protected String label;
			
	/** all the Actor objects */
	private static ArrayList<Actor> allActors = new ArrayList<Actor>();

	
	/** Constructor for all actors
	 * 
	 * @param label of the actor 
	 * @param xPos x position of the actor 
	 * @param yPos y position of the actor 
	 *
	 */
	protected Actor(String label, int xPos, int yPos){
		
		allActors.add(this);
		
		//type of the actor
		this.label = label;
		
		//position of the actor
		this.xPos = xPos;
		this.yPos = yPos;
							
	}
			
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
				
		//run the actor
		while(true){
					
			try {
							
			//let the thread sleep for a little time
			//without that we've got a running problem 
			Actor.sleep(Simulation.CLOCKBEAT);
					
				act(); 
				
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/** Acting method for all actors
	 *  Waits until the wakeUp() method notifies the thread, then the work() method is called
	 *
	 */
	private synchronized void act(){
		
		/* 
		 * Let the thread wait only, if the simulation is still not running or, 
		 * more important, if there is no more work to do for the moment
		 */
		
		if ((!Simulation.isRunning) || (!work())){	
			
			//wait until a wake up (notify) instruction comes in
			try {
				Statistics.show(this.getLabel() + " wait()");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
		
	/** Working method of the actor
	 * 
	 * @return true if the actor has more work to do, and
	 * false if the actor has no more work to do for the moment, so the thread can fall into the wait() mode
	 */
	protected abstract boolean work();
	

	/** Wakes up the actor thread. This is the method where the notify() call must be placed
	 * 
	 */
	public synchronized void wakeUp() {
		Statistics.show(this.getLabel() + " notify()");
		notify();
	}

	
	/** Get all Actors
	 * 
	 * @return the allActors
	 */
	public static ArrayList<Actor> getAllActors() {
		return allActors;
	}
	
	/** Get the actors label
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**get the actors x position
	 * 
	 * @return the xPos
	 */
	public int getXPos() {
		return xPos;
	}

	/**get the actors y position
	 * 
	 * @return the xPos
	 */
	public int getYPos() {
		return yPos;
	}
	

}
