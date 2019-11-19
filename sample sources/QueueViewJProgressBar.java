package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A JProgressBar class for the view of queues
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-28
 */
@SuppressWarnings("serial")
public class QueueViewJProgressBar extends JProgressBar implements QueueView{
			
	/** the width of the JProgressBar */
	private int width = 25; 
	
	/** the height of the JProgressBar */
	private int height = 35; 
	
	/** the minimum value of the JProgressBar */
	private int minimum = 0; 
	
	/** the maximum value of the JProgressBar */
	private int maximum = 4; 
	
	
	/** Creates a new view with a JProgressBar
	 * 
	 * @param size number of elements in the queue (the value of the JProgressBar)
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 */
	private QueueViewJProgressBar(int size, int xPos, int yPos){
		
		super();
				
		//set up the progress bar
		this.setOrientation(VERTICAL);
		this.setSize(width, height);
				
		this.setMinimum(minimum);
		this.setMaximum(maximum);
		this.setForeground(Color.GREEN);
		
		//set the value
		this.setValue(size);
		
		//show the value in progress bar
		this.setString(String.valueOf(size));
		this.setStringPainted(true);
						
		this.setLocation(xPos, yPos);
				
		//add the progress bar to the simulation view
		SimulationView.addActorView(this);
		
		//add a ChangeListener
		this.addChangeListener(new MyChangeListener(this));
			
	}
		
	/** Creates a new view with a JProgressBar
	 * 
	 * @param size number of elements in the queue
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 * 
	 * @return the ProgressBarView
	 */
	public static QueueViewJProgressBar create(int size, int xPos, int yPos){
	
		return new QueueViewJProgressBar(size, xPos, yPos);
		
	}

	@Override
	public void updateValue(int size){
	
		this.setValue(size);
		
	}
	
	/**
	 * Inner ChangeListener class to react on changes
	 * 
	 */
	private class MyChangeListener implements ChangeListener {
		
		private JProgressBar theJProgressBar;
		
		/** constructor gets the View Object
		 * @param v the View object
		 */
		public MyChangeListener(Component theComponent) {
			this.theJProgressBar = (JProgressBar) theComponent;
		}
		
		@Override
		public void stateChanged(ChangeEvent arg0) {
			
			//our progress bar value
			int pbValue = theJProgressBar.getValue();
									
			//a little example for changing the color if the value reaches limits
			double theLimit = theJProgressBar.getMaximum() * 0.4;
			double theCriticalLimit = theJProgressBar.getMaximum() * 0.6;
						
			if(pbValue >= theLimit)
				if(pbValue >= theCriticalLimit)theJProgressBar.setForeground(Color.RED);
				else theJProgressBar.setForeground(Color.YELLOW);
			else theJProgressBar.setForeground(Color.GREEN);
			
		
			//show the new value in the tool tip text
			theJProgressBar.setToolTipText(String.valueOf(pbValue));
			
			//update the value in the progress bar
			theJProgressBar.setString(String.valueOf(pbValue));
			
		}
			
	}
	
}
