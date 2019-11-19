package model;

import java.util.ArrayList;

/**
 * Superclass for simple stations with just one incoming and one outgoing queue
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-29
 */

public abstract class SimpleStation extends Station {
	
	/** the incoming queue for still to handle objects */
	protected SynchronizedQueue inComingQueue;
	
	/** the outgoing queue for already handled objects */
	protected SynchronizedQueue outGoingQueue;
	
	
	/** Constructor for simple stations
	 * 
	 * @param label of the station 
	 * @param inQueue the incoming queue
	 * @param outQueue the outgoing queue
	 * @param xPos x position of the station 
	 * @param yPos y position of the station 
	 * @param image image of the station 
	 */
	protected SimpleStation(String label, SynchronizedQueue inQueue, SynchronizedQueue outQueue, int xPos, int yPos, String image) {
		super(label, xPos, yPos, image);
		
		//the stations queues
		this.inComingQueue = inQueue;
		this.outGoingQueue = outQueue;
		
	}
	
	
	@Override
	protected int numberOfInQueueObjects() {

		return this.inComingQueue.size();
		
	}
	
	@Override
	protected int numberOfOutQueueObjects() {
		
		return this.outGoingQueue.size();
	}
	
	
	@Override
	protected abstract void handleObject(TheObject theObject) ;

	
	@Override
	protected TheObject getNextInQueueObject(){
		
		//return simply the first object
		return (TheObject) this.inComingQueue.poll();
	}
	
	
	@Override
	protected TheObject getNextOutQueueObject() {
		
		//return simply the first object
		return (TheObject) this.outGoingQueue.poll();
	}
	

	@Override
	public ArrayList<SynchronizedQueue> getAllInQueues() {
		
		// we have just one incoming queue
		ArrayList<SynchronizedQueue> inQueues = new ArrayList<SynchronizedQueue>();
		inQueues.add(inComingQueue);
		return inQueues;
	}

	@Override
	public ArrayList<SynchronizedQueue> getAllOutQueues() {
		
		// we have just one outgoing queue
		ArrayList<SynchronizedQueue> outQueues = new ArrayList<SynchronizedQueue>();
		outQueues.add(outGoingQueue);
		return outQueues;
		
	}

}
