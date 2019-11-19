package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Simulation;
import model.StartStation;


/**
 * A simple JButton class for a start button
 * 
 * @author Jaeger, Schmidt
 * @version 2016-07-07
 */
@SuppressWarnings("serial")
public class StartButton extends JButton implements ActionListener{

	public StartButton(){
		super("START");
		this.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//set the simulation on
		Simulation.isRunning = true;
		
		//wake up the start station -> lets the simulation run
		StartStation.getStartStation().wakeUp();
	
	}

	
}
