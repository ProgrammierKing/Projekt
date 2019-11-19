package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Class for our main window 
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-26
 */

@SuppressWarnings("serial")
public class SimulationView extends JFrame {
	
	/** main window width */
	private final static int WIDTH = 950;
	
	/** main window height */
	private final static int HEIGHT = 600;
	
	/** main window title */
	private final static String TITLE = "Prototyp: allgemeine Objekt/Queue/Station Simulation";
			
	/** a panel where the views of our actors can run */
	private static SimulationPanel simulationPanel = new SimulationPanel();
	
	
	/** Creates a JFrame main window for our simulation
	* 
	*/
	public SimulationView(){
		this.init();
		
	}
	
	/**
	 * initialize the main window
	 * 
	 */
	private void init(){
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		
		//create a start button
		StartButton startButton = new StartButton();
		
		//put the simulation panel into our JFrame
		this.getContentPane().add(simulationPanel, BorderLayout.CENTER);
		//put the start button into our JFrame
		this.getContentPane().add(startButton, BorderLayout.PAGE_END);
		
        this.setVisible(true);
		
	}
			
	/**Add an actor view to the simulation view
	 * 
	 * @param theView the actor view
	 */
	public static void addActorView(Component theView){
				
		//adds the view to the panel
		simulationPanel.addActorView(theView);
		
	}
		
	/**
	 * Inner JPanel class where the simulation runs
	 * 
	 */
	private static class SimulationPanel extends JPanel{
		
		/**
		 * Constructor initializes the panel
		 * 
		 */
		public SimulationPanel() {

			this.setBackground(Color.WHITE);
	        
	        // this gives us a layout which is controllable by coordinates,
	        this.setLayout(null);
	        
		}
		
		/**Adds a new actor view to the panel 
		 *  
		 * @param theView the actor view
		 */
		private void addActorView(Component theView){
			this.add(theView);
			this.repaint();
		
		}
		
	}
	
}
