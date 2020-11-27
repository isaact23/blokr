package main;

/**
 * Store a polyomino, player and its location on the board.
 */
public class Move {
    public final int player;
    public final Tile tile;
    public final int tileIndex;
    public final int tileOrientation;
    public final Coordinate coordinate; // Upper-left coordinate

    /**
     * Instantiate a new Move.
     * @param player Player number
     * @param tile The Tile object to apply in this move.
     * @param tileIndex The index of the Tile in an ArrayList<Tile>.
     * @param tileOrientation The tile's rotation/flip.
     * @param coordinate The location of the tile.
     */
    public Move(int player, Tile tile, int tileIndex, int tileOrientation, Coordinate coordinate) {
        this.player = player;
        this.tile = tile;
        this.tileIndex = tileIndex;
        this.tileOrientation = tileOrientation;
        this.coordinate = coordinate;
    }
}
