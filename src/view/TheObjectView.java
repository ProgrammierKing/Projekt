package view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import io.Statistics;

/**
 * Class for the view of objects
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-28
 */
@SuppressWarnings("serial")
public class TheObjectView extends ImageView {
	
	
	/** Creates a new view with an image (JLabel)
	 * 
	 * @param label of the object 
	 * @param image image of the view
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 */
	private TheObjectView(String label, String image, int xPos, int yPos){
		
		super(image, xPos, yPos);
						
		//a simple example of installing a MouseAdapter
		this.addMouseListener(new MyMouseListener(this));
	
	}
	
	
	/** Creates a new view of the object
	 * 
	 * @param label of the object 
	 * @param image image of the view
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 * 
	 * @return the ObjectView
	 */
	public static TheObjectView create(String label, String image, int xPos, int yPos){
	
		return new TheObjectView(label, image, xPos, yPos);
		
	}
	
	/**
	 * Inner MouseAdapter class to react on mouse events
	 * 
	 */
	private class MyMouseListener extends MouseAdapter {
		
		private Component theComponent;
		
		/** constructor gets the Component
		 * @param theComponent the Component object
		 */
		public MyMouseListener(Component theComponent) {
			this.theComponent = theComponent;
		}
		
		/**
		 * get the coordinates of the object and show it in the console
		 * 
		 * @param e the event
		 */
		public void mouseClicked(MouseEvent e){
		    	
			Statistics.show("The coordinates are " +  theComponent.getX() + " , " + theComponent.getY());
		    	
		 }  
			
	}
	
}
