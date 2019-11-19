package view;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * A JLabel class for views with text
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-27
 */
@SuppressWarnings("serial")
public abstract class TextView extends JLabel {
	
	/** the width of the JLabel */
	private int width = 30; 
	
	/** the height of the JLabel */
	private int height = 30; 
	
	/** Creates a new view with text (JLabel)
	 * 
	 * @param text the text content of the view
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 */
	protected TextView(String text, int xPos, int yPos){
		
		super();
		
		this.setSize(width, height);
		this.setBorder(LineBorder.createBlackLineBorder());
		this.setHorizontalAlignment(CENTER);
		this.setText(text);
		this.setLocation(xPos, yPos);
						
		//add the label to the simulation view
		SimulationView.addActorView(this);
		
		
	}
	
	/** Updates the text of the view
	 * 
	 * @param text the text content of the view
	 * 
	 */
	public void updateValue(String text){
	
		this.setText(text);
		
	}
	
}
