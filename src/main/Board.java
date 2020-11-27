package main;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Store data on which players occupy which grid squares.
 */
public class Board {
    // Board properties and data
    private final int width;
    private final int height;
    private int[][] squares; // 0 if empty, other ints for other players
    private ArrayList<ArrayList<Coordinate>> legalSquares; // Stores legal squares to play on per player
    private Stack<Move> moveStack; // Stack of previously applied moves

    /**
     * Create a game board at its entry state.
     * @param width Squares spanning x direction.
     * @param height Squares spanning y direction.
     * @param players Number of players.
     */
    public Board(int width, int height, int players) {
        // Initialize grid
        this.width = width;
        this.height = height;
        this.squares = new int[width][height];

        // Initialize legal squares (corners) for all players
        this.legalSquares = new ArrayList<>();
        initializeLegalSquares(players);
    }

    /**
     * List all possible moves for specified player.
     * @param player The player to list moves for.
     * @param tileList The tiles available for the player.
     * @return ArrayList of legal moves.
     */
    public ArrayList<Move> listMoves(int player, ArrayList<Tile> tileList) {
        throw new UnsupportedOperationException("listMoves() not implemented yet");
    }

    /**
     * Add the specified move to the board.
     * @param move The move object with tile data, location and player.
     * @return True if successful, false if not.
     */
    public boolean applyMove(Move move) {
        throw new UnsupportedOperationException("applyMove() not implemented yet");
    }

    /**
     * Remove the latest move from the board.
     * @return True if successful, false if not.
     */
    public boolean popMove() {
        throw new UnsupportedOperationException("popMove() not implemented yet");
    }

    /**
     * Add the corners to the list of legal squares for each player.
     * @param players Number of players in this game.
     */
    private void initializeLegalSquares(int players) {
        // Player 1
        legalSquares.set(0, new ArrayList<Coordinate>());
        legalSquares.get(0).add(new Coordinate(0, 0));
        // Player 2
        legalSquares.set(1, new ArrayList<Coordinate>());
        legalSquares.get(1).add(new Coordinate(width - 1, height - 1));
        if (players >= 3) {
            // Player 3
            legalSquares.set(2, new ArrayList<Coordinate>());
            legalSquares.get(2).add(new Coordinate(width - 1, 0));
        }
        if (players >= 4) {
            // Player 4
            legalSquares.set(3, new ArrayList<Coordinate>());
            legalSquares.get(3).add(new Coordinate(0, height - 1));
        }
    }

    /**
     * Print board to terminal.
     */
    public void print() {
        System.out.println();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.printf("%c  ", getSymbolForPlayer(
                        squares[x][y]
                ));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Get symbol for a specified player.
     * @param player The player number, 0 for none.
     */
    private char getSymbolForPlayer(int player) {
        switch (player) {
            case 0: return '-';
            case 1: return 'X';
            case 2: return 'O';
            case 3: return '#';
            case 4: return '&';
        }
        return '-';
    }
}
