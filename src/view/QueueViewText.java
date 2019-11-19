package view;


/**
 * A JLabel class for the view of queues
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-31
 */
@SuppressWarnings("serial")
public class QueueViewText extends TextView implements QueueView{
	
	/** Creates a new view with text (JLabel)
	 * 
	 * @param size number of elements in the queue
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 */
	private QueueViewText(int size, int xPos, int yPos){
		
		super(String.valueOf(size), xPos, yPos);
		
	}
			
	/** Creates a new text view of the queue
	 * 
	 * @param size number of elements in the queue
	 * @param xPos x position of the view
	 * @param yPos y position of the view
	 *
	 * @return the QueueView
	 */
	public static QueueViewText create(int size, int xPos, int yPos){
		
		return new QueueViewText(size, xPos, yPos);
		
	}
	
	@Override
	public void updateValue(int size){
	
		super.setText(String.valueOf(size));
		
	}

}
