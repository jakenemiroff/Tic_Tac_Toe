/**
 * Represents a situation where searching for an object in the dictionary is invalid, or placing an object in the dictionary is invalid.
 * either a duplicate key, or the object cannot be found in the dictionary
 * 
 * @author jakenemiroff
 *
 */
public class DictionaryException extends Exception {
	
	/**
	 * Sets up this exception.
	 * Message to be determined later
	 * 
	 * @param message
	 */
	public DictionaryException(String message) {
		super(message);
		
	}

}
