package main;

import java.util.HashMap;
import java.util.Stack;

/**
 * Store data on a game overall, including
 * board state, current turn, CPUs, etc.
 */
public class GameState {
    // Grid data
    private Board board;

    // Game data
    private final int playerCount;
    private HashMap<Integer, TileBag> tileBagMap; // Start indexing at 1
    private Stack<Move> moveStack;

    /**
     * Initialize a GameState for the beginning of a game.
     * @param boardWidth Squares spanning board in x direction.
     * @param boardHeight Squares spanning board in y direction.
     * @param playerCount Number of players including humans and non-humans.
     */
    public GameState(int boardWidth, int boardHeight, int playerCount) {
        // Initialize empty board
        this.board = new Board(boardWidth, boardHeight);

        this.playerCount = playerCount;

        // Initialize full tile bags for all players
        this.tileBagMap = new HashMap<>();
        for (int i = 1; i <= playerCount; i++) {
            this.tileBagMap.put(i, new TileBag());
        }

        this.moveStack = new Stack<>();
    }

    /**
     * Print the board to the terminal.
     */
    public void printBoard() {
        board.print();
    }

    /**
     * @return All possible moves for a given player.
     */
    public Move[] listMoves(int player) {
        TileBag tileBag = tileBagMap.get(player);

        throw new UnsupportedOperationException("listMoves() not implemented yet");
    }

    /**
     * Apply the given move to the board.
     * @param move The Move object to add to this Board.
     * @return True if successful, false if not.
     */
    public boolean pushMove(Move move) {
        throw new UnsupportedOperationException("pushMove() not implemented yet");
    }

    /**
     * Remove the latest move from the board.
     * @return True if successful, false if not.
     */
    public boolean popMove() {
        throw new UnsupportedOperationException("popMove() not implemented yet");
    }
}
