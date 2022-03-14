/**
 * 
 */
package aero.sita.digitaltwins.exceptions;

/**
 * Custom exception class to pass the exception to parent RuntimeException class..
 * Mar 14, 2022
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see RuntimeException
 */
@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {
	/**
	 * Method to pass exception to parent class.
	 * @param message
	 */
	public FrameworkException(String message) {
		super(message); 
	}
	/**
	 * Constructor if calling method wants to send the exception cause as well.
	 * @param message
	 * @param cause
	 */
	public FrameworkException(String message, Throwable cause) {  // This is also constructor in case if user want to send cause message also
		super(message,cause);
	}

}
