package io;

/**
 * A class for printing statistics
 * 
 * @author Jaeger, Schmidt
 * @version 2015-11-18
 */
public class Statistics {

	private static String buffer; 
	
	
	/** appends the given String to the buffer
	 *
	 * @param message the message to append
	 */
	public static void update(String message) {
		
		buffer = buffer + message + "\n";
	}
	
	/** writes the given String to console
	 *
	 * @param message the message to write to console
	 */
	public static void show(String message) {
		
		System.out.println(message);
	}
	
}
