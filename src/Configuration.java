/**
 * Defines the Configuration object
 * 
 * @author jakenemiroff
 *
 */
public class Configuration {
	
	/**
	 * Instance variable config, a string representing the board positioning
	 * This variable will be used as the 'key' in our implementation of a dictionary.
	 */
	private String config;
	
	/**
	 * Instance variable score, an integer representing the likelihood that the computer will make a given move
	 */
	private int score;
	
	/**
	 * Constructor used to create a Configuration object containing a String and an int
	 * 
	 * @param config
	 * @param score
	 */
	public Configuration(String config, int score) {
		
		this.config = config;
		this.score = score;
		
	}
	
	/**
	 * Method to return the string configuration stored in Configuration objects
	 * @return
	 */
	public String getStringConfiguration() {
		return config;
	}
	
	/**
	 * Method to return the score stored in Configuration objects
	 * @return
	 */
	public int getScore() {
		return score;
	}


}
