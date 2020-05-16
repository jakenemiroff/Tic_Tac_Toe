/**
 * Class to model the game board
 * 
 * @author jakenemiroff
 *
 */
public class BoardGame {

	/**
	 * Instance variable of a two dimensional array storing character objects
	 */
	char[][] gameBoard;

	/**
	 * Instance variable containing the size of the board game
	 */
	private int size;

	/**
	 * Constructor method to create a representation of the board Initializes every
	 * space on the board to empty
	 * 
	 * @param board_size
	 * @param empty_positions
	 * @param max_levels
	 */

	/**
	 * Variable for the size of the hash table when initializing. A prime number is
	 * used
	 */
	private final int TABLE_SIZE = 9887;

	public BoardGame(int board_size, int empty_positions, int max_levels) {

		this.size = board_size;

		gameBoard = new char[board_size][board_size];

		for (int row = 0; row < board_size; row++) {

			for (int col = 0; col < board_size; col++) {

				savePlay(row, col, 'g'); // initialize empty board
			}
		}

	}

	/**
	 * Method used to create a hash dictionary with a given size
	 * 
	 * @return
	 */
	public HashDictionary makeDictionary() {

		HashDictionary hashDictionary = new HashDictionary(TABLE_SIZE);

		return hashDictionary;

	}

	/**
	 * Method used to check if the string representation of the game boards current
	 * position is in the hash dictionary
	 * 
	 * @param dict
	 * @return
	 */
	public int isRepeatedConfig(HashDictionary dict) {

		String config = "";

		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {

				config = config + gameBoard[row][col]; // turn the board configuration into a string
			}
		}

		if (dict.getScore(config) == -1) {
			return -1;
		}

		return dict.getScore(config);

	}

	/**
	 * Method used to represent the game board as a string and then store that
	 * string and the score in the hash dictionary If the Configuration object is
	 * already in the hash dictionary, DictionaryException is caught
	 * 
	 * @param dict
	 * @param score
	 */
	public void putConfig(HashDictionary dict, int score) {

		String config = "";

		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {

				config = config + gameBoard[row][col];
			}
		}

		Configuration data = new Configuration(config, score);

		try {
			dict.put(data); // try to put the data in the given dictionary
		} catch (DictionaryException e) { // catch potential duplicate key with the DictionaryException
			e.getMessage();
		}

	}

	/**
	 * method used to store a given symbol in the gameBoard at the specified row and
	 * column index
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void savePlay(int row, int col, char symbol) {

		gameBoard[row][col] = symbol;

	}

	/**
	 * Method used to check whether or not a position in the game board at a given
	 * row and column index is empty
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean positionIsEmpty(int row, int col) {

		if (gameBoard[row][col] == 'g') {

			return true;
		}

		else {

			return false;
		}
	}

	/**
	 * Method used to check whether or not the tile at the specified row and column
	 * index on the game board belongs to the computer
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileOfComputer(int row, int col) {

		if (gameBoard[row][col] == 'o') {

			return true;
		}

		else {

			return false;
		}
	}

	/**
	 * Method used to check whether or not the tile at the specified row and column
	 * index on the game board belongs to the human player
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileOfHuman(int row, int col) {

		if (gameBoard[row][col] == 'b') {

			return true;
		}

		else {

			return false;
		}

	}

	/**
	 * Method used to return true if a given symbol is repeated n times either
	 * horizontally, vertically, or on a diagonal It will return false otherwise
	 * 
	 * @param symbol
	 * @return
	 */
	public boolean wins(char symbol) {

		// check diagonal for win
		int row = 0;
		int col = 0;

		while (col < size) {

			if (gameBoard[row][col] == symbol) {
				row++;
				col++;
			}

			else if (gameBoard[row][col] != symbol) {
				col = size;
			}

			if (row == size) {
				return true;
			}
		}

		// check backwards diagonal for win
		row = 0;
		col = size - 1;

		while (col >= 0) {

			if (gameBoard[row][col] == symbol) {
				row++;
				col--;
			} else if (gameBoard[row][col] != symbol) {
				col = -1;
			}

			if (row == size) {
				return true;
			}

		}

		// check each row for a win
		row = 0;
		col = 0;

		while (col < size && row < size) {

			if (gameBoard[row][col] == symbol) {
				col++;
			} else if (gameBoard[row][col] != symbol) {
				col = 0; // if no consecutive pieces, set column back to zero and increase row
				row++; // (check next row for a win)
			}

			if (col == size) {
				return true;
			}
		}

		// check each column for a win
		row = 0;
		col = 0;

		while (row < size && col < size) {

			if (gameBoard[row][col] == symbol) {
				row++;
			} else if (gameBoard[row][col] != symbol) { // if no consecutive pieces, set row back to zero and increase
														// column
				row = 0;
				col++;
			}

			if (row == size) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Method used to determine if the current board configuration is a draw
	 * 
	 * @param symbol
	 * @param empty_positions
	 * @return
	 */
	public boolean isDraw(char symbol, int empty_positions) { // causing issues

		if (wins('o') == false && wins('b') == false) {

			if (empty_positions == 0 && fullBoard(gameBoard) == true) {
				return true;
			}

			if (empty_positions > 0 && checkAdjacent(symbol) == false) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Method used to evaluate the current board configuration and return a score
	 * depending on the configuration
	 * 
	 * @param symbol
	 * @param empty_positions
	 * @return
	 */
	public int evalBoard(char symbol, int empty_positions) {

		if (wins('o') == true) {
			return 3;
		}

		if (wins('b') == true) {
			return 0;
		}

		if (isDraw(symbol, empty_positions) == true) {
			return 2;

		} else {

			return 1;
		}
	}

	/**
	 * Helper method used to determine whether or not the board is full
	 * 
	 * @param boardGame
	 * @return
	 */
	private boolean fullBoard(char[][] boardGame) {

		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {

				if (gameBoard[row][col] == 'g') { // check for any empty squares
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Helper method used to check if the adjacent tiles to empty ones contain the
	 * given symbol
	 * 
	 * @param symbol
	 * @return
	 */
	private boolean checkAdjacent(char symbol) {

		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {

				if (gameBoard[row][col] == 'g') { // if a tile is empty check adjacent ones for the given symbol

					for (int i = row - 1; i <= row + 1; i++) {

						for (int j = col - 1; j <= col + 1; j++) {

							if ((i >= 0 && i < row) && (j >= col && j < col)) {

								if (gameBoard[i][j] == symbol) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
}
