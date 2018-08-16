import java.util.Scanner;
/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of
 * rounds. It also keeps track of the number of victories of each player.
 */
public class Competition {

	/** Two objects from the Player class representing the 2 players in the NIM game*/
	private final Player playerOne, playerTwo;
	/** Boolean variable which will be true if at least one of the players is a Human player (in this case,
	 *  messages will be displayed to the screen. */
	private final boolean messageFlag;

	/** Variables that count the number of wins per player. */
	private static int player1Victories, player2Victories, PLAYER_1_ID = 1, PLAYER_2_ID = 2;;
	// Messages to be printed only if at least one of the players is a human player.
	private static final String START_SINGLE_GAME_MSG = "Welcome to the sticks game!", TURN_MSG =
			"Player %s, it is now your turn!", INVALID_MOVE_MSG = "Invalid move. Enter another: ",
			MADE_MOVE_MSG = "Player %s made the move: %s", VICTORY_ROUND_MSG = "Player %s won!",
			START_GAME_MSG = "Starting a Nim competition of %s rounds between a %s player and a %s player.",
			END_OF_GAME_SCORE_MSG = "The results are %s : %s";

	/**
	 * The Competition class represents a Nim competition between two players, consisting of a given number
	 * of rounds. It also keeps track of the number of victories of each player.
	 * @param player1 The Player objects representing the first player.
	 * @param player2 The Player objects representing the second player.
	 * @param displayMessage A flag indicating whether game play messages should be printed to the console.
	 */
	public Competition(Player player1, Player player2, boolean displayMessage) {
		playerOne = player1;
		playerTwo = player2;
		messageFlag = displayMessage;
		player1Victories = 0;
		player2Victories = 0;
	}


	/**
	 * If playerPosition = 1, the results of the first player is returned. If playerPosition = 2, the result
	 * of the second player is returned. If playerPosition is different, -1 is returned.
	 */
	public int getPlayerScore(int playerPosition) {
		switch (playerPosition){
			case 1:
				return player1Victories;
			case 2:
				return player2Victories;
			default:
				return -1;
		}
	}

	/**
	 * Returns the number representing the type of player 1; returns -1 on bad input.
	 */
	private static int parsePlayer1Type(String[] args) {
		try {
			return Integer.parseInt(args[0]);
		} catch (Exception E) {
			return -1;
		}
	}

	/**
	 * Returns the number representing the type of player 1; returns -1 on bad input.
	 */
	private static int parsePlayer2Type(String[] args) {
		try {
			return Integer.parseInt(args[1]);
		} catch (Exception E) {
			return -1;
		}
	}

	/**
	 * Returns the number representing the type of player 1; returns -1 on bad input.
	 */
	private static int parseNumberOfGames(String[] args) {
		try {
			return Integer.parseInt(args[2]);
		} catch (Exception E) {
			return -1;
		}
	}

	/**
	 * This method runs a single Nim game between two players, and returns the winner Id.
	 * @param player1 The first player.
	 * @param player2 The second player.
	 * @return The Id of the winner player.
	 */
	private int singleGame(Player player1, Player player2) {
		Board board = new Board(); // In each game we will reboot a new board.
		Move singleMove; // Will receive the selected move in each turn.
		printIfTheFlagIsTrue(START_SINGLE_GAME_MSG, this.messageFlag);
		Player currentPlayer = player1; // Represent the player whose turn to play.
		while (board.getNumberOfUnmarkedSticks() > 0) {
				printIfTheFlagIsTrue(String.format(TURN_MSG, currentPlayer.getPlayerId()), this.messageFlag);
				singleMove = currentPlayer.produceMove(board);
				while (board.markStickSequence(singleMove) != 0) {
					printIfTheFlagIsTrue(INVALID_MOVE_MSG, this.messageFlag);
					singleMove = currentPlayer.produceMove(board);
				}
				printIfTheFlagIsTrue(String.format(MADE_MOVE_MSG, currentPlayer.getPlayerId(),
						singleMove.toString()), this.messageFlag);
				if (currentPlayer == player1) currentPlayer = player2;
				else currentPlayer = player1;
		}
		printIfTheFlagIsTrue(String.format(VICTORY_ROUND_MSG, currentPlayer.getPlayerId()), this.messageFlag);
		return currentPlayer.getPlayerId();
	}


	/*
	 * This method receives a flag and a message. It will print the message if the flag is true.
	 */
	private void printIfTheFlagIsTrue (String message, boolean flag){
		if (flag){
			System.out.println(message);
		}
	}


	/**
	 * Run the game for the given number of rounds.
	 * @param numRounds number of rounds to play.
	 */
	public void playMultiple(int numRounds) {
		final int PLAYER_ONE_WIN = 1;
		final int PLAYER_TWO_WIN = 2;
		System.out.println();
		for (int i = 0; i < numRounds; i++) {
			if (singleGame(playerOne, playerTwo) == PLAYER_ONE_WIN) player1Victories++;
			if (singleGame(playerOne, playerTwo) == PLAYER_TWO_WIN) player2Victories++;
		}
	}

	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments.
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 * player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 *
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {
		int p1Type = parsePlayer1Type(args);
		int p2Type = parsePlayer2Type(args);
		int numGames = parseNumberOfGames(args);
		boolean displayMessage = ((Integer.parseInt(args[1]) == Player.HUMAN) ||
				(Integer.parseInt(args[2]) == Player.HUMAN));
		Scanner scanner = new Scanner(System.in);
		Player player1;
		Player player2;
		try{
			if (numGames < 1) {
				scanner.close();
				throw new Exception();
			}
			player1 = new Player(p1Type, PLAYER_1_ID, scanner);
			player2 = new Player(p2Type, PLAYER_2_ID, scanner);
		}
		catch (Exception e){
			System.out.println("Invalid Input. There should be 3 arguments:");
			System.out.println("The first two are numbers between 1 and 4.");
			System.out.println("The third should be a number larger than 0");
			scanner.close();
			return;
		}
		System.out.println(String.format(START_GAME_MSG, numGames, player1.getTypeName(), player2.getTypeName()));
		Competition competition = new Competition(player1, player2, displayMessage);
		competition.playMultiple(numGames);
		System.out.println(String.format(END_OF_GAME_SCORE_MSG, competition.getPlayerScore(1),
				competition.getPlayerScore(2)));
		scanner.close();
	}
}