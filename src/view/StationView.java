package view;


/**
 * Class for the view of stations
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-28
 */
@SuppressWarnings("serial")
public class StationView extends ImageView {

	/** Creates a new view with an image (JLabel)
	 * 
	 * @param label of the station 
	 * @param image image of the view
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 */
	private StationView(String label, String image, int xPos, int yPos){
		
		super(image, xPos, yPos);
		
		this.setToolTipText(label);
		
	}
	
	
	/** Creates a new view of the station
	 * 
	 * @param label of the station 
	 * @param image image of the view
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 * 
	 * @return the StationView
	 */
	public static StationView create(String label, String image, int xPos, int yPos){
	
		return new StationView(label, image, xPos, yPos);
		
	}

}
