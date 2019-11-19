package model;

import view.TheObjectView;

/**
 * An example class for a simple Actor object that can be created during simulation
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-27
 */

//TIP: This is just a simple example how to create an actor during simulation
//new NewActor("NewActorExample", this.getXPos() + 10 * numberOfOutQueueObjects(), this.getYPos() + 100, "newactor.png");

public class NewActor extends Actor {
	
	/** the view of the new object */
	private TheObjectView theView;
	
	public NewActor(String label, int xPos, int yPos, String image) {
			
			super(label, xPos, yPos);
		
			//create the actor's view
			this.theView = TheObjectView.create(label, image, xPos, yPos);
			
			//start the thread
			super.start();
		
	}

	@Override
	protected boolean work() {
		// in this case we have an absolutely passive actor 
		return false;
	}

}
