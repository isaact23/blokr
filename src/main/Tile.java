package main;

/**
 * A 1 to 5 square piece that can be fitted onto a board.
 */
public class Tile {
    private Coordinate[] coordinates;
    
    public Tile(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }
    
    /**
     * @return New polyomino equivalent to this one.
     */
    public Tile copy() {
        throw new UnsupportedOperationException("copy() not implemented yet");
    }
}
