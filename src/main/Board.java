package main;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Store data on which players occupy which grid squares.
 */
public class Board {
    private final int width;
    private final int height;
    private final int playerCount;
    private int[][] squares; // 0 if empty, other ints for other players

    private ArrayList<ArrayList<Coordinate>> legalSquares; // Stores legal squares to play on per player
    private Stack<Move> moveStack; // Stack of previously applied moves
    private HashMap<Integer, TileList> tileListMap; // Lists of tiles for all players; start indexing at 1

    /**
     * Create a game board at its entry state.
     * @param width Squares spanning x direction.
     * @param height Squares spanning y direction.
     * @param playerCount Number of players (2-4).
     */
    public Board(int width, int height, int playerCount) {
        // Initialize grid
        this.width = width;
        this.height = height;
        initializeSquares();

        // Verify player count is within valid range
        this.playerCount = playerCount;
        if (playerCount < 2 || playerCount > 4) {
            throw new InvalidParameterException("playerCount must be in range 2 to 4");
        }

        // Initialize full tile lists for all players
        this.tileListMap = new HashMap<>();
        for (int i = 1; i <= playerCount; i++) {
            this.tileListMap.put(i, new TileList());
        }

        // Initialize legal squares (corners) for all players
        this.legalSquares = new ArrayList<>();
        initializeLegalSquares(playerCount);
    }

    /**
     * List all possible moves for specified player.
     * @param player The player to list moves for.
     * @param tileList The tiles available for the player.
     * @return ArrayList of legal moves.
     */
    public ArrayList<Move> listMoves(int player, TileList tileList) {
        throw new UnsupportedOperationException("listMoves() not implemented yet");
    }

    /**
     * Add the specified move to the board.
     * @param move The move object with tile data, location and player.
     * @return True if successful, false if not.
     */
    public boolean pushMove(Move move) {
        Tile tile = move.tile;
        int player = move.player;
        Coordinate[] coordinates = tile.getCoordinates(player);

        moveStack.push(move);
        return false;
    }

    /**
     * Remove the latest move from the board.
     * @return True if successful, false if not.
     */
    public boolean popMove() {
        throw new UnsupportedOperationException("popMove() not implemented yet");
    }

    /**
     * Initialize all squares to -1. This eliminates any confusion with
     * the player number (0, 1, 2 or 3).
     */
    private void initializeSquares() {
        this.squares = new int[this.width][this.height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                squares[x][y] = -1;
            }
        }
    }

    /**
     * Add the corners to the list of legal squares for each player.
     * @param players Number of players in this game.
     */
    private void initializeLegalSquares(int players) {
        // Player 1
        legalSquares.add(0, new ArrayList<Coordinate>());
        legalSquares.get(0).add(new Coordinate(0, 0));
        // Player 2
        legalSquares.add(1, new ArrayList<Coordinate>());
        legalSquares.get(1).add(new Coordinate(width - 1, height - 1));
        if (players >= 3) {
            // Player 3
            legalSquares.add(2, new ArrayList<Coordinate>());
            legalSquares.get(2).add(new Coordinate(width - 1, 0));
        }
        if (players >= 4) {
            // Player 4
            legalSquares.add(3, new ArrayList<Coordinate>());
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
            case -1: return '-';
            case 0: return 'X';
            case 1: return 'O';
            case 2: return '#';
            case 3: return '&';
        }
        return '-';
    }
}
