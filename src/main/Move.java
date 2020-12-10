package main;

/**
 * Store a polyomino, player and its location on the board.
 */
public class Move {
    public final int player;
    public final Tile tile;
    public final int tileOrientation;
    public final Coordinate coordinate; // Upper-left coordinate

    /**
     * Instantiate a new Move.
     * @param player Player number
     * @param tile The Tile object to apply in this move.
     * @param tileOrientation The tile's rotation/flip.
     * @param coordinate The location of the tile.
     */
    public Move(int player, Tile tile, int tileOrientation, Coordinate coordinate) {
        this.player = player;
        this.tile = tile;
        this.tileOrientation = tileOrientation;
        this.coordinate = coordinate; // Upper-left corner of piece
    }

    /**
     * Print this move to the terminal.
     */
    public void print() {
        System.out.printf("Player %s, Tile %s, Orientation %s, Coordinate %s%n",
                player, tile.getId(), tileOrientation, coordinate.toString());
    }
}
