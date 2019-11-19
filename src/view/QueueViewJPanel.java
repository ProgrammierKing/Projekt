package view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * A JPanel class with JProgressBar and JLabel
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-31
 */
@SuppressWarnings("serial")
public class QueueViewJPanel extends JPanel implements QueueView{
			
	/** the width of all components */
	private static final int WIDTH = 25; 
	
	/** the height of the panel */
	private static final int HEIGHT = 55; 
	
	/** the JProgressBar */
	private JProgressBar theBar = new JProgressBar();
	
	/** the JLabel */
	private JLabel theLabel = new JLabel();
	
	
	/** Creates a JPanel with a JProgressBar and a JLabel
	 * 
	 * @param size number of elements in the queue
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 * 
	 * @return the QueueJPanelView
	 */
	private QueueViewJPanel(int size, int xPos, int yPos){
		
		super();
		
		// this gives us a layout which is controllable by coordinates
		this.setLayout(null);
        
		//set up the panel
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(xPos, yPos);
		this.setBorder(LineBorder.createBlackLineBorder());
		this.setBackground(Color.WHITE);
		
		//set up the label
		theLabel.setSize(WIDTH, 20);
		theLabel.setText(String.valueOf(size));
		theLabel.setLocation(0, 0);
		theLabel.setHorizontalAlignment(SwingConstants.CENTER);
							
		//set up the progress bar
		theBar.setOrientation(SwingConstants.VERTICAL);
		theBar.setSize(WIDTH, HEIGHT - theLabel.getHeight());
		theBar.setMinimum(0);
		theBar.setMaximum(4);
		theBar.setForeground(Color.GREEN);
		theBar.setToolTipText(String.valueOf(size));
		theBar.setLocation(0, theLabel.getHeight());
				
		//add a ChangeListener to the progress bar
		theBar.addChangeListener(new MyChangeListener(theBar, theLabel));
		
		//add the components to the panel
		this.add(theLabel);
		this.add(theBar);
				
		//add the panel to the simulation view
		SimulationView.addActorView(this);
	}
		
	
	/** Creates a JPanel with a JProgressBar and aJLabel
	 * 
	 * @param size number of elements in the queue
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 * 
	 * @return the ProgressBarView
	 */
	public static QueueViewJPanel create(int size, int xPos, int yPos){
	
		return new QueueViewJPanel(size, xPos, yPos);
		
	}
	

	@Override
	public void updateValue(int size) {
		theBar.setValue(size);
		theLabel.setText(String.valueOf(size));
		
	}
	
	
	/**
	 * Inner ChangeListener class to react on changes
	 * 
	 */
	private class MyChangeListener implements ChangeListener {
		
		private JProgressBar theBar;
		private JLabel theLabel ;
		
		/** constructor gets the View Object
		 * @param theBar the progressive bar 
		 * @param theLabel the label
		 */
		public MyChangeListener(JProgressBar theBar, JLabel theLabel) {
			this.theBar = theBar;
			this.theLabel = theLabel;
		}
		
		@Override
		public void stateChanged(ChangeEvent arg0) {
			
			//our progress bar value
			int pbValue = theBar.getValue();
			
			//show the new value in the tool tip text
			theBar.setToolTipText(String.valueOf(pbValue));
			
			//show the new value also in the label
			theLabel.setText(String.valueOf(pbValue));
			
			//a little example for changing the color if the value reaches limits
			double theLimit = theBar.getMaximum() * 0.33;
			double theCriticalLimit = theBar.getMaximum() * 0.66;
						
			if(pbValue >= theLimit)
				if(pbValue >= theCriticalLimit)theBar.setForeground(Color.RED);
				else theBar.setForeground(Color.YELLOW);
			else theBar.setForeground(Color.GREEN);
					
			
		}
			
	}
}
