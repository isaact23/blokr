package main;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * Store data on which players occupy which grid squares.
 */
public class Board {
    private final int width;
    private final int height;
    private final int playerCount;
    private int[][] squares; // 0 if empty, other ints for other players
    private boolean[][][] legalSquares; // x, y, player

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
        if (playerCount < 1 || playerCount > 4) {
            throw new InvalidParameterException("playerCount must be in range 1 to 4");
        }

        // Initialize legal squares (corners) for all players
        initializeLegalSquares(playerCount);

        // Initialize empty move stack
        this.moveStack = new Stack<Move>();

        // Initialize full tile lists for all players
        this.tileListMap = new HashMap<>();
        for (int i = 0; i < playerCount; i++) {
            this.tileListMap.put(i, new TileList());
        }
    }

    /**
     * List all possible moves for specified player.
     * This implementation DOES NOT ADD TO legalSquares.
     * @param player The player to list moves for.
     * @return ArrayList of legal moves.
     */
    public ArrayList<Move> listMoves(int player) {
        ArrayList<Move> moves = new ArrayList<Move>();
        // Iterate through tiles
        Iterator<Tile> tileIterator = tileListMap.get(player).iterator();

        while (tileIterator.hasNext()) {
            Tile tile = tileIterator.next();

            // Iterate through orientations for each tile
            Coordinate[][] allCoordinates = tile.getAllCoordinates();
            for (int orientation = 0; orientation < allCoordinates.length; orientation++) {
                Coordinate[] coordinates = allCoordinates[orientation];

                // Iterate through valid coordinates on the grid
                Coordinate boundingCoord = Tile.getBoundingCoord(coordinates);
                for (int x = 0; x < width - boundingCoord.x; x++) {
                    for (int y = 0; y < height - boundingCoord.y; y++) {
                        boolean selfCorner = false; // Must be true
                        boolean startingPoint = false; // Can be true to substitute selfCorner
                        boolean selfEdge = false; // Must be false
                        boolean overlap = false; // Must be false

                        // Iterate through squares in this set of coordinates; ensure all represent legal positions
                        for (int j = 0; j < coordinates.length; j++) {
                            Coordinate coord = coordinates[j];
                            // Check if we're starting in our corner
                            if (legalSquares[x + coord.x][y + coord.y][player]) {
                                startingPoint = true;
                            }
                            // Check corners/edges
                            if (squares[x + coord.x][y + coord.y] != -1) {
                                overlap = true;
                                break;
                            }
                            if (x + coord.x > 0) {
                                if (squares[x + coord.x - 1][y + coord.y] == player) {
                                    selfEdge = true;
                                    break;
                                }
                                if (y + coord.y > 0) {
                                    if (squares[x + coord.x - 1][y + coord.y - 1] == player) {
                                        selfCorner = true;
                                    }
                                }
                                if (y + coord.y < height - 1) {
                                    if (squares[x + coord.x - 1][y + coord.y + 1] == player) {
                                        selfCorner = true;
                                    }
                                }
                            }
                            if (y + coord.y > 0 && squares[x + coord.x][y + coord.y - 1] == player) {
                                selfEdge = true;
                                break;
                            }
                            if (y + coord.y < height - 1 && squares[x + coord.x][y + coord.y + 1] == player) {
                                selfEdge = true;
                                break;
                            }
                            if (x + coord.x < width - 1) {
                                if (squares[x + coord.x + 1][y + coord.y] == player) {
                                    selfEdge = true;
                                    break;
                                }
                                if (y + coord.y > 0) {
                                    if (squares[x + coord.x + 1][y + coord.y - 1] == player) {
                                        selfCorner = true;
                                    }
                                }
                                if (y + coord.y < height - 1) {
                                    if (squares[x + coord.x + 1][y + coord.y + 1] == player) {
                                        selfCorner = true;
                                    }
                                }
                            }
                        }
                        // If the move passes the checks, add to list
                        if ((selfCorner || startingPoint) && (!selfEdge) && (!overlap)) {
                            moves.add(
                                    new Move(player, tile, orientation, new Coordinate(x, y))
                            );
                        }
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Evaluate how favorable the position is for each player. Only works correctly in two-player games.
     * @return An integer, positive favoring player 0, negative favoring player 1.
     */
    public int evaluate() {
        throw new UnsupportedOperationException("evaluate() not implemented yet");
    }

    /**
     * Get the tile list for the specified player.
     * @param player The player whose tile list to retrieve.
     * @return The tile list.
     */
    public TileList getTileList(int player) {
        return tileListMap.get(player);
    }

    /**
     * Add the specified move to the board.
     * @param move The move object with tile data, location and player.
     */
    public void pushMove(Move move) {
        Tile tile = move.tile;
        Coordinate[] coordinates = tile.getCoordinates(move.tileOrientation);
        for (int i = 0; i < coordinates.length; i++) {
            Coordinate coord = coordinates[i];
            if (squares[move.coordinate.x + coord.x][move.coordinate.y + coord.y] != -1) {
                throw new RuntimeException("Cannot push move: Move overlaps existing pieces.");
            }
            squares[move.coordinate.x + coord.x][move.coordinate.y + coord.y] = move.player;
            legalSquares[move.coordinate.x + coord.x][move.coordinate.y + coord.y][move.player] = false;
        }
        moveStack.push(move);
        tileListMap.get(move.player).pop(tile.getId());
    }

    /**
     * Remove the latest move from the board.
     * @return True if successful, false if not.
     */
    public boolean popMove() {
        throw new UnsupportedOperationException("popMove() not implemented yet");
    }

    /**
     * @return This board's grid.
     */
    public int[][] getSquares() {
        return squares;
    }

    /**
     * @param move The Move object to analyze
     * @return True if the move is legal.
     */
    private boolean isMoveValid(Move move) {
        throw new UnsupportedOperationException("Not implemented yet");
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
        legalSquares = new boolean[width][height][playerCount];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int p = 0; p < playerCount; p++) {
                    legalSquares[x][y][p] = false;
                }
            }
        }
        legalSquares[0][0][0] = true;
        if (playerCount > 1)
            legalSquares[width - 1][height - 1][1] = true;
        if (playerCount > 2)
            legalSquares[0][height - 1][2] = true;
        if (playerCount > 3)
            legalSquares[width - 1][0][3] = true;
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
            case 2: return '+';
            case 3: return '*';
        }
        return '-';
    }
}
