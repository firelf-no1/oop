import java.util.Random;
import java.util.Scanner;


/**
 * The Player class represents a player in the Nim game, producing Moves as a response to a Board state.
 * Each player is initialized with a type, either human or one of several computer strategies, which defines
 * the move he produces when given a board in some state. The heuristic strategy of the player is already
 * implemented. You are required to implement the rest of the player types according to the exercise
 * description.
 * @author OOP course staff
 */
public class Player {

	//Constants that represent the different players.
	/** The constant integer representing the Random player type. */
	public static final int RANDOM = 1, HEURISTIC = 2, SMART = 3, HUMAN = 4;
	
	private static final int BINARY_LENGTH = 4;	//Used by produceHeuristicMove() for binary representation of
	// board rows.

	//Messages to be printed for a Human player:
	private static final String MOVE_OR_BOARD_MSG = "Please make your decision to make a move press 1 to" +
			" display the board press 2: ", ENTER_ROW_MSG = "Please enter the row number you want: ",
			ENTER_LEFT_STICK_MSG = "Please enter the leftmost stick in your selection: ",
			ENTER_RIGHT_STICK_MSG = "Please enter the rightmost stick in your selection: ",
			ERROR_MSG = "Unsupported command", PLAYER_ERROR = "Received an unknown player type as a parameter"
			+ " in Player constructor. Terminating.";

	private final int playerType, playerId; // A number between 1 and 4 representing the player type / Id.

	private Scanner scanner;
	
	/**
	 * Initializes a new player of the given type and the given id, and an initialized scanner.
	 * @param typeVer The type of the player to create.
	 * @param idVer The id of the player (either 1 or 2).
	 * @param scannerVerInt The Scanner object through which to get user input
	 * for the Human player type. 
	 */
	public Player(int typeVer, int idVer, Scanner scannerVerInt){
		// Check for legal player type (we will see better ways to do this in the future).
		if (typeVer != RANDOM && typeVer != HEURISTIC && typeVer != SMART && typeVer != HUMAN){
			System.out.println(PLAYER_ERROR);
			System.exit(-1);
		}		
		this.playerType = typeVer;
		this.playerId = idVer;
		this.scanner = scannerVerInt;
	}
	
	/**
	 * @return an integer matching the player type.
	 */	
	public int getPlayerType(){
		return this.playerType;
	}
	
	/**
	 * @return the players id number.
	 */	
	public int getPlayerId(){
		return this.playerId;
	}
	
	
	/**
	 * @return a String matching the player type.
	 */
	public String getTypeName(){
		switch(getPlayerType()){
			case RANDOM:
				return "Random";
			case SMART: 
				return "Smart";
			case HEURISTIC:
				return "Heuristic";
			case HUMAN:			
				return "Human";
			default:
				return "UnknownPlayerType";
		}
	}
	
	/**
	 * This method encapsulates all the reasoning of the player about the game. The player is given the 
	 * board object, and is required to return his next move on the board. The choice of the move depends
	 * on the type of the player: a human player chooses his move manually; the random player should 
	 * return some random move; the Smart player can represent any reasonable strategy; the Heuristic 
	 * player uses a strong heuristic to choose a move. 
	 * @param board - a Board object representing the current state of the game.
	 * @return a Move object representing the move that the current player will play according to his
	 * strategy.
	 */
	public Move produceMove(Board board){
		switch(getPlayerType()){
			case RANDOM:
				return this.produceRandomMove(board);
			case SMART: 
				return this.produceSmartMove(board);
			case HEURISTIC:
				return this.produceHeuristicMove(board);
			case HUMAN:
				return this.produceHumanMove(board);
			default: 
				return null;			
		}
	}

	/**
	 *This method returns a move for a random player.
	 *To do this, it checks how much unmarked sticks on the board (say n sticks) and randomly selects a
	 *number between 1 and n.
     *Then it goes through all the sticks and checks who is the n unmarked stick, and that will be the
     *left stick.
     * Next, we'll look at the maximum sequence you can select to the right of the selected stick,
      * and select the length of the sequence.
	 * @param boardVer The game board.
	 * @return Move object representing the selected move
	 */
	private Move produceRandomMove(Board boardVer){
		int numberOfUnmarkedSticks = boardVer.getNumberOfUnmarkedSticks();
		Random myLocalRandom = new Random();
		//Because the random function returns a number in the range of 0-(n-1) I added the number 1 at the end
		int randomStick = myLocalRandom.nextInt(numberOfUnmarkedSticks) + 1;
		int numberOfRows = boardVer.getNumberOfRows();
		int unmarkedSticks = 0, rowNumber = 0, leftStick = 0, rightStick;
		boolean check = false;
		for (int rowVer = 1; rowVer <= numberOfRows && !check; rowVer++) {
			for (rightStick = 1; rightStick <= boardVer.getRowLength(rowVer); rightStick++){
				if (boardVer.isStickUnmarked(rowVer, rightStick)) unmarkedSticks ++;
				if (unmarkedSticks == randomStick){ // The loop reached the n unmarked stick.
					rowNumber = rowVer;
					leftStick = rightStick;
					rowVer = numberOfRows; // To finish the outer loop.
					check = true;
					break;
				}
			}
		}
		for (rightStick = leftStick; rightStick < boardVer.getRowLength(rowNumber) &&
				boardVer.isStickUnmarked(rowNumber, rightStick + 1);) rightStick++;
		return new Move(rowNumber, leftStick, rightStick);
	}


	/**
	 * Interact with the user to make his move.
	 * @param boardVer The game board.
	 * @return the selected Move.
	 */
	private Move produceHumanMove(Board boardVer) {
		final int MAKE_MOVE = 1, SHOW_BOARD = 2;
		int totalRows = boardVer.getNumberOfRows();
		System.out.println(MOVE_OR_BOARD_MSG);
		int input = scanner.nextInt();
		while (input != MAKE_MOVE) {
			if (input == SHOW_BOARD) System.out.println(boardVer);
			else System.out.println(ERROR_MSG);
			System.out.println(MOVE_OR_BOARD_MSG);
			input = scanner.nextInt();
		}
		System.out.println(ENTER_ROW_MSG);
		int row = scanner.nextInt();
		while ((row > totalRows) || (row < 1)) {
			System.out.println(ERROR_MSG);
			System.out.println(ENTER_ROW_MSG);
			row = scanner.nextInt();
		}
		System.out.println(ENTER_LEFT_STICK_MSG);
		int leftStick = scanner.nextInt();
		System.out.println(ENTER_RIGHT_STICK_MSG);
		int rightStick = scanner.nextInt();
		return new Move(row, leftStick, rightStick);
	}

	/**
	 * Choose a move for the smart player.
	 * @param boardVer The game board.
	 * @return the selected Move.
	 */
	private Move produceSmartMove(Board boardVer) {
		int rowNumber = 0, leftStick = 0, rightStick = 0, lengthRow;
		int totalRows = boardVer.getNumberOfRows();
		Move moveVer = null;
		for (int rowVer = 1; rowVer <= totalRows; ++rowVer) {
			int rightVer = 0, leftVer = 0;
			lengthRow = boardVer.getRowLength(rowVer);
			for (int stickVer = 1; stickVer <= lengthRow; ++stickVer) {
				if (boardVer.isStickUnmarked(rowVer, stickVer)) {
					++rightStick;
					if (leftVer == 0) {
						leftVer = stickVer;
					}
					rowNumber = rowVer;
					leftStick = stickVer;
					++rightVer;
				}
			}
			if (rightVer > 1) {
				moveVer = new Move(rowVer, leftVer, leftVer + rightVer - 2);
				rightStick -= rightVer - 1;
			}
		}
		if (moveVer != null && rightStick % 2 == 1) return moveVer;
		else return new Move(rowNumber, leftStick, rightStick);
	}
	



	/*
	 * Uses a winning heuristic for the Nim game to produce a move.
	 */
	private Move produceHeuristicMove(Board boardVer) {

		int numberOfRows = boardVer.getNumberOfRows();
		int[][] binsVer = new int[numberOfRows][BINARY_LENGTH];
		int[] binarySum = new int[BINARY_LENGTH];
		int bitVerIndex, largerThenOne = 0, totalOnes = 0, lastRow = 0, lastLeft = 0, lastSize = 0, lastOneRow = 0, lastOneLeft = 0;

		for (bitVerIndex = 0; bitVerIndex < BINARY_LENGTH; bitVerIndex++) binarySum[bitVerIndex] = 0;

		for (int j = 0; j < numberOfRows; j++) {
			int curRowLength = boardVer.getRowLength(j + 1);
			int i = 0;
			int onesCount = 0;
			for (bitVerIndex = 0; bitVerIndex < BINARY_LENGTH; bitVerIndex++) binsVer[j][bitVerIndex] = 0;
			do {
				if (i < curRowLength && boardVer.isStickUnmarked(j + 1, i + 1)) onesCount++;
				else {
					if (onesCount > 0) {
						String verNum;
						for (verNum = Integer.toBinaryString(onesCount); verNum.length() < BINARY_LENGTH; )
							verNum = "0" + verNum;
						for (bitVerIndex = 0; bitVerIndex < BINARY_LENGTH; bitVerIndex++)
							binsVer[j][bitVerIndex] += verNum.charAt(bitVerIndex) - 48; //Convert from char to int
						if (onesCount > 1) {
							largerThenOne++;
							lastRow = j + 1;
							lastLeft = i - onesCount + 1;
							lastSize = onesCount;
						}
						else totalOnes++;
						lastOneRow = j + 1;
						lastOneLeft = i;
						onesCount = 0;
					}
				}
				i++;
			}
			while (i <= curRowLength);
			for (bitVerIndex = 0; bitVerIndex < BINARY_LENGTH; bitVerIndex++)
				binarySum[bitVerIndex] = (binarySum[bitVerIndex] + binsVer[j][bitVerIndex]) % 2;
		}

		//a single sticks is left
		if (largerThenOne == 0) return new Move(lastOneRow, lastOneLeft, lastOneLeft);
		//We are at a finishing state
		if (largerThenOne <= 1) {
			if (totalOnes == 0) return new Move(lastRow, lastLeft, lastLeft + (lastSize - 1) - 1);
			else return new Move(lastRow, lastLeft, lastLeft + (lastSize - 1) - (1 - totalOnes % 2));
		}
		else {
			for (bitVerIndex = 0; bitVerIndex < BINARY_LENGTH - 1; bitVerIndex++) {
				if (binarySum[bitVerIndex] > 0) {
					int finalSum = 0, eraseRow = 0, eraseSize = 0, numRemove = 0, eraseIndex;
					for (int k = 0; k < numberOfRows; k++) {
						if (binsVer[k][bitVerIndex] > 0) {
							eraseRow = k + 1;
							eraseSize = (int) Math.pow(2, (double) (BINARY_LENGTH - bitVerIndex - 1));
							for (eraseIndex = bitVerIndex + 1; eraseIndex < BINARY_LENGTH; eraseIndex++) {
								if (binarySum[eraseIndex] > 0) {
									if (binsVer[k][eraseIndex] == 0) finalSum = finalSum + (int) Math.pow(2,
											BINARY_LENGTH - eraseIndex - 1);
									else finalSum = finalSum - (int) Math.pow(2, BINARY_LENGTH - eraseIndex - 1);
								}
							}
							break;
						}
					}
					numRemove = eraseSize - finalSum;
					int checker = 0;
					//finding the part and removing it from the piece
					for (eraseIndex = 0; checker < eraseSize; eraseIndex++) {
						if (boardVer.isStickUnmarked(eraseRow, eraseIndex + 1)) checker++;
						else checker = 0;
					}
					return new Move(eraseRow, eraseIndex - checker + 1, eraseIndex - checker +
							numRemove);
				}
			}
			//if the board is not symmetric we only need to erase a single stick
			if (binarySum[BINARY_LENGTH - 1] > 0) return new Move(lastOneRow, lastOneLeft, lastOneLeft);

				//If the board is already symmetric we only need to erase a single stick from the last sequence we saw:
			else return new Move(lastRow, lastLeft, lastLeft);
		}
	}

	/**
	 * A input checker
	 * @param rowVer the row the player choose
	 * @param stickVer the stick he chose
	 * @return true if the input is ok and false
	 */
	private Boolean stickCheck(int rowVer, int stickVer, Board boardVer) {
		int minStick = 1;
		int maxStick = boardVer.getRowLength(rowVer);
		return ((stickVer <= maxStick) || (stickVer >= minStick));
	}
}
