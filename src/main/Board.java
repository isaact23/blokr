package main;

import java.util.HashMap;

/**
 * Store data on which players occupy which grid squares.
 */
public class Board {
    // Board properties and data
    private int width;
    private int height;
    private int[][] squares;

    // Player count and polyomino bags
    private int playerCount;
    private HashMap<Integer, PolyominoBag> polyominoBagMap;

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
        for (int i = 0; i < playerCount; i++) {
            polyominoBagMap.put(i, new PolyominoBag());
        }
    }

    /**
     * @return All possible moves for a given player.
     */
    public Move[] listMoves(int player) {
        PolyominoBag polyominoBag = polyominoBagMap.get(player);

        throw new UnsupportedOperationException("listMoves() not implemented yet");
    }
}
