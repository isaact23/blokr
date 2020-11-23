package main;

import java.util.HashMap;
import java.util.Stack;

/**
 * Store data on which players occupy which grid squares.
 */
public class Board {
    // Board properties and data
    private final int width;
    private final int height;
    private int[][] squares;

    // Player count and polyomino bags
    private final int playerCount;
    private HashMap<Integer, TileBag> tileBagMap;
    private Stack<Move> moveStack;

    /**
     * Create a game board at its entry state.
     * @param width Squares spanning x direction.
     * @param height Squares spanning y direction.
     * @param playerCount Number of players including humans and non-humans.
     */
    public Board(int width, int height, int playerCount) {
        // Initialize grid
        this.width = width;
        this.height = height;
        this.squares = new int[width][height];

        // Initialize full polyomino bags
        this.playerCount = playerCount;
        this.tileBagMap = new HashMap<>();
        for (int i = 0; i < playerCount; i++) {
            this.tileBagMap.put(i, new TileBag());
        }
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
