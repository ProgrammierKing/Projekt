package model;

import io.Statistics;
import java.util.ArrayList;
import java.util.Collection;
import controller.Simulation;

/**
 * Class for a simple process station (with just one incoming and one outgoing queue)
 * 
 * @author Jaeger, Schmidt
 * @version 2019-11-01
 */
public class SimpleProcessStation extends SimpleStation {
	
	/** a parameter that affects the speed of the treatment for an object */
	private double troughPut;
	
	/** the instance of our static inner Measurement class*/ 
	Measurement measurement = new Measurement();
				
	/** (private!) Constructor, creates a new process station 
	 * 
	 * @param label of the station 
	 * @param inQueue the incoming queue
	 * @param outQueue the outgoing queue
	 * @param xPos x position of the station 
	 * @param yPos y position of the station 
	 * @param image image of the station 
	 */
	private SimpleProcessStation(String label, SynchronizedQueue inQueue, SynchronizedQueue outQueue, double troughPut, int xPos, int yPos, String image){
		
		super(label, inQueue, outQueue, xPos, yPos, image);
		
		//the troughPut parameter 
		this.troughPut = troughPut;
		
	}
	
	/** create a new simple process station and add it to the station list
	 *
	 * @param label of the station 
	 * @param inQueue the incoming queue
	 * @param outQueue the outgoing queue
	 * @param troughPut a stations parameter that affects treatment of an object
	 * @param xPos x position of the station
	 * @param yPos y position of the station
	 * @param image image of the station 
	 */
	public static void create(String label,  SynchronizedQueue inQueue, SynchronizedQueue outQueue, double troughPut, int xPos, int yPos, String image){
	
		new SimpleProcessStation(label, inQueue, outQueue, troughPut, xPos, yPos, image);
		
	}
	
	/*
	protected void handleObject(TheObject theObject){
		
		//the object chooses an outgoing queue and enter it
		theObject.enterOutQueue(this);
		
		//let the next objects start with a little delay
		try {
			Thread.sleep(Simulation.CLOCKBEAT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	*/
	
	
	@Override
	protected void handleObject(TheObject theObject){
										
		//count all the visiting objects
		measurement.numbOfVisitedObjects++; 
		
		Statistics.show(this.getLabel() + " behandelt: " + theObject.getLabel());
		
		//the processing time of the object
		int processTime = theObject.getProcessTime(); 
		
		//the time to handle the object
		int theObjectsTreatingTime = (int) (processTime/this.troughPut); 
				
		//get the starting time of the treatment
		long startTime = Simulation.getGlobalTime(); 
				
		//the elapsed time of the treatment
		int elapsedTime = 0;
				
		//while treating time is not reached
		while (!(theObjectsTreatingTime <= elapsedTime)){
				
			//the elapsed time since the start of the treatment
			elapsedTime = (int) (Simulation.getGlobalTime() - startTime); 
									
			//let the thread sleep for the adjusted clock beat 
			//This is just needed to notice the different treatment duration in the view
			try {
				Thread.sleep(Simulation.CLOCKBEAT);
						
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
					
		}
		
		//increase the time the object was treated
		theObject.measurement.myTreatmentTime = theObject.measurement.myTreatmentTime + elapsedTime;
				
		//increase the stations in use time
		measurement.inUseTime = measurement.inUseTime + elapsedTime; 
						
		//the treatment is over, now the object chooses the outgoing queue and enter it
		theObject.enterOutQueue(this);
			
		//just to see the view of the outgoing queue works
		try {
			Thread.sleep(500);
					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * A (static) inner class for measurement jobs. The class records specific values of the station during the simulation.
	 * These values can be used for statistic evaluation.
	 */
	private static class Measurement {
		
		/** the total time the station is in use */
		private int inUseTime = 0;
		
		/** the number of all objects that visited this station*/ 
		private int numbOfVisitedObjects = 0;
		
		
		/**Get the average time for treatment
		 * 
		 * @return the average time for treatment
		 */
		private int avgTreatmentTime() {
			
			if(numbOfVisitedObjects == 0) return 0; //in case that a station wasn't visited
			else
			return inUseTime/numbOfVisitedObjects;
			
		}
		
	}
	
	
	/**
	 * get and print some statistics out of the Measurement class
	 * 
	 */
	public void printStatistics() {
		
		String theString = "\nStation Typ: " + this.label;
		theString = theString + "\nAnzahl der behandelten Objekte: " + measurement.numbOfVisitedObjects;
		theString = theString + "\nZeit zum Behandeln aller Objekte: " + measurement.inUseTime;
		theString = theString + "\nDurchnittliche Behandlungsdauer: " + measurement.avgTreatmentTime();
		
		Statistics.show(theString);
		
	}
	
		
	/** Get all simple process stations
	 * 
	 * @return the allSimpleProcessStations
	 */
	public static ArrayList<SimpleProcessStation> getAllSimpleProcessStations() {
		
		// the simple process station list
		ArrayList<SimpleProcessStation> allSimpleProcessStations = new ArrayList<SimpleProcessStation>();
		
		//filter the simple process stations out of the station list
		for (Station station : Station.getAllStations()) {
			
			if(station instanceof SimpleProcessStation) allSimpleProcessStations.add((SimpleProcessStation) station);
			
		}
				
		return allSimpleProcessStations;
	}
	
	
	@Override
	protected void handleObjects(Collection<TheObject> theObjects) {
				
	}

	@Override
	protected Collection<TheObject> getNextInQueueObjects() {
		return null;
	}

	@Override
	protected Collection<TheObject> getNextOutQueueObjects() {
		return null;
	}
			
}
