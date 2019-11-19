package io;
import view.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.EndStation;
import model.ProcessStation;
import model.SimpleProcessStation;
import model.StartStation;
import model.SynchronizedQueue;
import model.TheObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * This is an abstract factory that creates instances
 * of actor types like objects, stations and their queues 
 * 
 * @author Jaeger, Schmidt
 * @version 2019-11-02
 */
public class Factory {
	
	/** the objects XML data file */
	private static String theObjectDataFile = "xml/object.xml"; 
	
	/** the process stations XML data file */
	private static String theProcessStationDataFile = "xml/process_station.xml"; 
	
	/** the simple process stations XML data file */
	private static String theSimpleProcessStationDataFile = "xml/simple_process_station.xml";
	
	/** the start station XML data file */
	private static String theStartStationDataFile = "xml/startstation.xml"; 
	
	/** the end station XML data file */
	private static String theEndStationDataFile = "xml/endstation.xml"; 
	
	/** the x position of the starting station, also position for all starting objects */
	private static int XPOS_STARTSTATION;
	
	/** the y position of the starting station, also position for all starting objects */
	private static int YPOS_STARTSTATION; 
		
	
	/**
     * create the actors for the starting scenario
     * 
     */
	public static void createStartScenario(){
		
		/*NOTE: The start station must be created first,
		* because the objects constructor puts the objects into the start stations outgoing queue
		*/ 
		createStartStation(); 
		createObjects();
		createProcessStations();
		createSimpleProcessStations();
		createEndStation();
	}
	
	/**
     * create the start station
     * 
     */
     private static void createStartStation(){
    	
    	try {
    		
    		//read the information from the XML file into a JDOM Document
    		Document theXMLDoc = new SAXBuilder().build(theStartStationDataFile);
    		
    		//the <settings> ... </settings> node
    		Element root = theXMLDoc.getRootElement();
    		
    		//get the start_station into a List object
    		Element startStation = root.getChild("start_station");
    		
    		//get the label
    		String label = startStation.getChildText("label");
    		    		    		
    		//get the position
    		XPOS_STARTSTATION = Integer.parseInt(startStation.getChildText("x_position"));
    		YPOS_STARTSTATION = Integer.parseInt(startStation.getChildText("y_position"));
    		
    		//the <view> ... </view> node
    		Element viewGroup = startStation.getChild("view");
    		// the image
    		String image = viewGroup.getChildText("image");
    		
    		//CREATE THE INQUEUE
    		//the <inqueue> ... </inqueue> node
    		Element inqueueGroup = startStation.getChild("inqueue");
    		
    		// the positions
    		int xPosInQueue = Integer.parseInt(inqueueGroup.getChildText("x_position"));
    		int yPosInQueue = Integer.parseInt(inqueueGroup.getChildText("y_position"));
    		
    		//create the inqueue
    		SynchronizedQueue theInQueue = SynchronizedQueue.createQueue(QueueViewText.class, xPosInQueue, yPosInQueue);
    		
    		//CREATE THE OUTQUEUE
    		//the <outqueue> ... </outqueue> node
    		Element outqueueGroup = startStation.getChild("outqueue");
    		
    		// the positions
    		int xPosOutQueue = Integer.parseInt(outqueueGroup.getChildText("x_position"));
    		int yPosOutQueue = Integer.parseInt(outqueueGroup.getChildText("y_position"));
    		
    		//create the outqueue
    		SynchronizedQueue theOutQueue = SynchronizedQueue.createQueue(QueueViewText.class, xPosOutQueue, yPosOutQueue);
    		    		
    		//creating a new StartStation object
    		StartStation.create(label, theInQueue, theOutQueue, XPOS_STARTSTATION, YPOS_STARTSTATION, image);
    	    
    	
    	} catch (JDOMException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
     }
	
	/**
     * create some objects out of the XML file
     * 
     */
     private static void createObjects(){
    	
    	try {
		
    		//read the information from the XML file into a JDOM Document
    		Document theXMLDoc = new SAXBuilder().build(theObjectDataFile);
    		
    		//the <settings> ... </settings> node, this is the files root Element
    		Element root = theXMLDoc.getRootElement();
    		
    		//get all the objects into a List object
    		List <Element> allObjects = root.getChildren("object");
    		
    		//separate every JDOM "object" Element from the list and create Java TheObject objects
    		for (Element theObject : allObjects) {
    			
    			// data variables:
    			String label = null;
    			int processtime = 0;
    			int speed = 0;
    			String image = null;
    			    			
    			// read data
    			label = theObject.getChildText("label");
    			processtime = Integer.parseInt(theObject.getChildText("processtime"));
    			speed = Integer.parseInt(theObject.getChildText("speed"));
        		        		
        		//the <view> ... </view> node
        		Element viewGroup = theObject.getChild("view");
        		// read data
        		image = viewGroup.getChildText("image");
        		
        		//get all the stations, where the object wants to go to
        		//the <sequence> ... </sequence> node
        		Element sequenceGroup = theObject.getChild("sequence");
        		
        		List <Element> allStations = sequenceGroup.getChildren("station");
        		
        		//get the elements into a list
        		ArrayList<String> stationsToGo = new ArrayList<String>();
        		
        		for (Element theStation : allStations) {
        			
        			stationsToGo.add(theStation.getText());
        			
        		}
        	  		
        		//creating a new TheObject object
        		TheObject.create(label, stationsToGo, processtime, speed, XPOS_STARTSTATION, YPOS_STARTSTATION, image);
        		
			}
    	
    	} catch (JDOMException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
    }
    
    /**
     * create some process stations out of the XML file
     * 
     */
     private static void createProcessStations(){
    	
    	try {
    		
    		//read the information from the XML file into a JDOM Document
    		Document theXMLDoc = new SAXBuilder().build(theProcessStationDataFile);
    		
    		//the <settings> ... </settings> node
    		Element root = theXMLDoc.getRootElement();
    		
    		//get all the stations into a List object
    		List <Element> stations = root.getChildren("station");
    		
    		//separate every JDOM "station" Element from the list and create Java Station objects
    		for (Element station : stations) {
    			
    			// data variables:
    			String label = null;
    			double troughPut = 0;
    			int xPos = 0;
    			int yPos = 0;
    			String image = null;
    			    			
    			// read data
    			label = station.getChildText("label");
    			troughPut = Double.parseDouble(station.getChildText("troughput"));
        		xPos = Integer.parseInt(station.getChildText("x_position"));
        		yPos = Integer.parseInt(station.getChildText("y_position"));
        		        		
        		//the <view> ... </view> node
        		Element viewGroup = station.getChild("view");
        		// read data
        		image = viewGroup.getChildText("image");
        		        		
        		//CREATE THE INQUEUES
        		
        		//get all the inqueues into a List object
        		List <Element> inqueues = station.getChildren("inqueue");
        		
        		//create a list of the stations inqueues 
        		ArrayList<SynchronizedQueue> theInqueues = new ArrayList<SynchronizedQueue>(); //ArrayList for the created inqueues
        		
        		for (Element inqueue : inqueues) {
        			
        			int xPosInQueue = Integer.parseInt(inqueue.getChildText("x_position"));
            		int yPosInQueue = Integer.parseInt(inqueue.getChildText("y_position"));
            		
            		//create the actual inqueue an add it to the list
            		theInqueues.add(SynchronizedQueue.createQueue(QueueViewJPanel.class, xPosInQueue, yPosInQueue));
            	}
        		        		
        		//CREATE THE OUTQUEUES
        		
        		//get all the outqueues into a List object
        		List <Element> outqueues = station.getChildren("outqueue");
        		
        		//create a list of the stations outqueues 
        		ArrayList<SynchronizedQueue> theOutqueues = new ArrayList<SynchronizedQueue>(); //ArrayList for the created outqueues
        		
        		for (Element outqueue : outqueues) {
        			
        			int xPosOutQueue = Integer.parseInt(outqueue.getChildText("x_position"));
            		int yPosOutQueue = Integer.parseInt(outqueue.getChildText("y_position"));
            		
            		//create the actual outqueue an add it to the list
            		theOutqueues.add(SynchronizedQueue.createQueue(QueueViewText.class, xPosOutQueue, yPosOutQueue));
            	}
        		
        		//creating a new process station object
        		ProcessStation.create(label, theInqueues, theOutqueues, troughPut, xPos, yPos, image);
        		
			}
    		
    	
    	} catch (JDOMException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
    	
    }
     
     /**
      * create some simple process stations out of the XML file
      * 
      */
      private static void createSimpleProcessStations(){
     	
     	try {
     		
     		//read the information from the XML file into a JDOM Document
     		Document theXMLDoc = new SAXBuilder().build(theSimpleProcessStationDataFile);
     		
     		//the <settings> ... </settings> node
     		Element root = theXMLDoc.getRootElement();
     		
     		//get all the stations into a List object
     		List <Element> stations = root.getChildren("station");
     		
     		//separate every JDOM "station" Element from the list and create Java Station objects
     		for (Element station : stations) {
     			
     			// data variables:
     			String label = null;
     			double troughPut = 0;
     			int xPos = 0;
     			int yPos = 0;
     			String image = null;
     			    			
     			// read data
     			label = station.getChildText("label");
     			troughPut = Double.parseDouble(station.getChildText("troughput"));
         		xPos = Integer.parseInt(station.getChildText("x_position"));
         		yPos = Integer.parseInt(station.getChildText("y_position"));
         		        		
         		//the <view> ... </view> node
         		Element viewGroup = station.getChild("view");
         		// read data
         		image = viewGroup.getChildText("image");
         		
         		//CREATE THE INQUEUE
        		//the <inqueue> ... </inqueue> node
        		Element inqueueGroup = station.getChild("inqueue");
        		
        		// the positions
        		int xPosInQueue = Integer.parseInt(inqueueGroup.getChildText("x_position"));
        		int yPosInQueue = Integer.parseInt(inqueueGroup.getChildText("y_position"));
        		
        		//create the inqueue
        		SynchronizedQueue theInQueue = SynchronizedQueue.createQueue(QueueViewJPanel.class, xPosInQueue, yPosInQueue);
        		
        		//CREATE THE OUTQUEUE
        		//the <outqueue> ... </outqueue> node
        		Element outqueueGroup = station.getChild("outqueue");
        		
        		// the positions
        		int xPosOutQueue = Integer.parseInt(outqueueGroup.getChildText("x_position"));
        		int yPosOutQueue = Integer.parseInt(outqueueGroup.getChildText("y_position"));
        		
        		//create the outqueue
        		SynchronizedQueue theOutQueue = SynchronizedQueue.createQueue(QueueViewText.class, xPosOutQueue, yPosOutQueue);
        		    		
        		     		         		
         		//creating a new simple process station object
         		SimpleProcessStation.create(label, theInQueue, theOutQueue, troughPut, xPos, yPos, image);
         		
 			}
     		
     	
     	} catch (JDOMException e) {
 				e.printStackTrace();
 		} catch (IOException e) {
 				e.printStackTrace();
 		}
     	
     }
    
     /**
     * create the end station
     * 
     */
     private static void createEndStation(){
    	
    	try {
    		
    		//read the information from the XML file into a JDOM Document
    		Document theXMLDoc = new SAXBuilder().build(theEndStationDataFile);
    		
    		//the <settings> ... </settings> node
    		Element root = theXMLDoc.getRootElement();
    		
    		//get the end_station into a List object
    		Element endStation = root.getChild("end_station");
    		
    		//get label
    		String label = endStation.getChildText("label");
    		    		    		
    		//position
    		int xPos = Integer.parseInt(endStation.getChildText("x_position"));
    		int yPos = Integer.parseInt(endStation.getChildText("y_position"));
    		
    		//the <view> ... </view> node
    		Element viewGroup = endStation.getChild("view");
    		// the image
    		String image = viewGroup.getChildText("image");
    		
    		//CREATE THE INQUEUE
    		//the <inqueue> ... </inqueue> node
    		Element inqueueGroup = endStation.getChild("inqueue");
    		
    		// the positions
    		int xPosInQueue = Integer.parseInt(inqueueGroup.getChildText("x_position"));
    		int yPosInQueue = Integer.parseInt(inqueueGroup.getChildText("y_position"));
    		
    		//create the inqueue
    		SynchronizedQueue theInQueue = SynchronizedQueue.createQueue(QueueViewText.class, xPosInQueue, yPosInQueue);
    		
    		//CREATE THE OUTQUEUE
    		//the <outqueue> ... </outqueue> node
    		Element outqueueGroup = endStation.getChild("outqueue");
    		
    		// the positions
    		int xPosOutQueue = Integer.parseInt(outqueueGroup.getChildText("x_position"));
    		int yPosOutQueue = Integer.parseInt(outqueueGroup.getChildText("y_position"));
    		
    		//create the outqueue
    		SynchronizedQueue theOutQueue = SynchronizedQueue.createQueue(QueueViewText.class, xPosOutQueue, yPosOutQueue);
    		
    		//creating a new EndStation object
    		EndStation.create(label, theInQueue, theOutQueue, xPos, yPos, image);
    	    
    	
    	} catch (JDOMException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
     }
        
}
