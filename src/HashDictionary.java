/**
 * Class to create the hash table by implementing the DictionaryADT
 * 
 * @author jakenemiroff
 *
 */
public class HashDictionary implements DictionaryADT {

	/**
	 * Instance variable storing the size of the hash table
	 */
	private int size;

	/**
	 * Instance variable storing an array of nodes
	 */
	private Node<Configuration>[] hashDictionary;

	/**
	 * Constructor used to create a hash table with a given size
	 * 
	 * @param size
	 */
	public HashDictionary(int size) {

		Node<Configuration> emptyNode = new Node<Configuration>();

		hashDictionary = new Node[size];

		this.size = size;

		for (int i = 0; i < size; i++) {
			hashDictionary[i] = emptyNode;
		}

	}

	/**
	 * Method used to add a Node containing a Configuration object to the hash table
	 * at a given index throws an exception if a duplicate key exists
	 */
	public int put(Configuration data) throws DictionaryException {

		String key = data.getStringConfiguration();

		int index = hashFunction(key);

		Node<Configuration> newNode = new Node<Configuration>(data);

		Node<Configuration> current = hashDictionary[index];

		if (hashDictionary[index].getConfiguration() == null) {
			hashDictionary[index] = newNode;
			hashDictionary[index].incrementSize();
			return 0;
		}

		else {

			if (data.getStringConfiguration() == current.getConfiguration().getStringConfiguration()) {
				throw new DictionaryException("Cannot insert a duplicate");

			}

			else {

				while (current.getNext() != null) {

					if (data.getStringConfiguration() == current.getNext().getConfiguration()
							.getStringConfiguration()) {
						throw new DictionaryException("Cannot insert duplicate");

					}

					else {

						current = current.getNext();
					}

				}
				current.setNext(newNode);
				hashDictionary[index].incrementSize();
				return 1;
			}
		}
	}

	/**
	 * Method used to remove a Node from an index in the hash table, throws an
	 * exception if no node at the given index matches the key config
	 */
	public void remove(String config) throws DictionaryException {

		boolean done = false;

		int index = hashFunction(config);

		if (hashDictionary[index].getSize() < 1) {
			throw new DictionaryException("No data found in the hash table");
		}

		if (hashDictionary[index].getSize() == 1) {

			if (hashDictionary[index].getConfiguration().getStringConfiguration().equals(config)) {

				hashDictionary[index].setConfiguration(null);
				hashDictionary[index].decrementSize();
			}

			else {

				throw new DictionaryException("No data found in the hash table");
			}

		}

		else {

			while (hashDictionary[index].getConfiguration() != null && !done) {

				if (hashDictionary[index].getConfiguration().getStringConfiguration().equals(config)) {

					int size = hashDictionary[index].getSize();

					hashDictionary[index] = hashDictionary[index].getNext(); // ** doesn't remove the element at all

					hashDictionary[index].setSize(size - 1);

					done = true;
				}

				else {

					if (hashDictionary[index].getNext() != null) {

						hashDictionary[index] = hashDictionary[index].getNext();
					}
				}
			}
		}

	}

	/**
	 * Method used to return the score of a node matching the key config
	 */
	public int getScore(String config) {

		int index = hashFunction(config);

		Node<Configuration> current = hashDictionary[index];

		while (current.getConfiguration() != null) {

			if (current.getConfiguration().getStringConfiguration().equals(config)) {

				return current.getConfiguration().getScore();
			}

			else if (current.getNext() != null) {

				current = current.getNext();
			}

			else {

				return -1;
			}

		}
		return -1;
	}

	/**
	 * Helper method, computes the Hash Function used to determine the index in the
	 * hash table For the purpose of locating a Node to remove or adding a Node to
	 * the table
	 * 
	 * @param config
	 * @return
	 */
	private int hashFunction(String config) {

		int hashValue = 0;
		for (int i = 0; i < config.length(); i++) {
			hashValue = (31 * hashValue + ((int) config.charAt(i))) % this.size;
		}

		return hashValue;
	}

}
