package view;

/**
 * Interface for queue views
 * 
 * @author Jaeger, Schmidt
 * @version 2017-10-31
 */
public interface QueueView {
	
	/** Updates the value of the view (number of elements in the queue)
	 * 
	 * @param size number of elements in the queue
	 * 
	 */
	public void updateValue(int size);

}
