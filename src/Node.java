/**
 * Represents a node storing Configuration objects in a linked list
 * 
 * @author jakenemiroff
 *
 * @param <Configuration>
 */
public class Node<Configuration> {
	/**
	 * instance variable next
	 */
	private Node<Configuration> next;
	
	/**
	 * instance variable config
	 */
    private Configuration config;
    
    /**
     * instance variable count
     */
    private int count;
    
    /**
     * Constructor used to create an empty node.
     */
    public Node() {
        next = null;
        config = null;
        count = 0;
    }
    
    /**
     * Constructor used to create a node storing the a Configuration object.
     *
     * @param config  the element to be stored within the new node
     */
    public Node (Configuration config) {
        next = null;
        this.config = config;
        count = 0;
        
    }
    
    
    /**
     * Returns the node that follows this one.
     *
     * @return  the node that follows the current one
     */
    public Node<Configuration> getNext() {
        return next;
    }
    
    /**
     * Sets the node that follows this one.
     *
     * @param node  the node to be set to follow the current one
     */
    public void setNext (Node<Configuration> node) {
        next = node;
    }
    
    /**
     * Returns the Configuration stored in this node.
     *
     * @return  the Configuration stored in this node
     */
    public Configuration getConfiguration() {
        return config;
    }
    
    /**
     * Sets the Configuration stored in this node.
     *
     * @param config  the Configuration to be stored in this node
     */
    public void setConfiguration (Configuration config) {
    	this.config = config;
    }
    
    /**
     * Returns the size contained in the Node
     * 
     * @return the size
     */
    public int getSize() {
    	return count;
    }
    
    /**
     * Increase the size of the current Node
     */
    public void incrementSize() {
    	count++;
    }
    
    /**
     * Decrease the size of the current node
     */
    public void decrementSize() {
    	count--;
    }
    
    /**
     * Set the size of the current node
     * @param size
     */
    public void setSize(int size) {
    	this.count = size;
    }
    
}
