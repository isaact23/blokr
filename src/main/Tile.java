package main;

/**
 * A 1 to 5 square piece that can be fitted onto a board.
 */
public class Tile implements Cloneable {

    private int tileSize;

    // List of coordinates for all orientations of the piece.
    private Coordinate[][] allCoordinates;

    // Properties defining how a tile can be flipped/rotated to create new tiles.
    private boolean can_flip;
    private boolean can_rotate_two; // Two orientations only
    private boolean can_rotate_four; // All four orientations
    
    public Tile(Coordinate[] coordinates,
                boolean can_flip, boolean can_rotate_two, boolean can_rotate_four) {
        this.tileSize = coordinates.length;

        this.can_flip = can_flip;
        this.can_rotate_two = can_rotate_two;
        this.can_rotate_four = can_rotate_four;

        this.generateCoordinates(coordinates);
    }

    /**
     * Rotate and flip the piece to generate all unique sets of coordinates.
     * @param coordinates The coordinates for the squares in the tile.
     */
    private void generateCoordinates(Coordinate[] coordinates) {
        int orientations = 1;
        if (this.can_flip)
            orientations = 2;
        if (this.can_rotate_four)
            orientations *= 4;
        else if (this.can_rotate_two)
            orientations *= 2;

        // Initialize 2D array of coordinates for every orientation
        this.allCoordinates = new Coordinate[orientations][this.tileSize];
    }

    /**
     * Flip the piece over the x axis.
     * @param coordinates Coordinates to flip.
     * @return Copied flipped coordinates.
     */
    private Coordinate[] flip(Coordinate[] coordinates) {
        Coordinate boundingCoord = getBoundingCoord(coordinates);
        Coordinate[] newCoords = new Coordinate[coordinates.length];

        for (int i = 0; i < coordinates.length; i++) {
            int newX = coordinates[i].x;
            int newY = boundingCoord.y - coordinates[i].y;
            newCoords[i] = new Coordinate(newX, newY);
        }

        return newCoords;
    }

    /**
     * Rotate the given coordinates 90 degrees clockwise.
     * @param coordinates Coordinates to rotate.
     * @return Copied rotated coordinates.
     */
    private Coordinate[] rotate(Coordinate[] coordinates) {
        Coordinate boundingCoord = getBoundingCoord(coordinates);
        Coordinate[] newCoords = new Coordinate[coordinates.length];

        for (int i = 0; i < coordinates.length; i++) {
            int newX = coordinates[i].y;
        }

        throw new UnsupportedOperationException("rotate() not implemented yet");
    }

    /**
     * Get the largest X and Y values in the set of coordinates.
     * @param coordinates Coordinates to search.
     * @return Largest X and Y values as a coordinate.
     */
    private Coordinate getBoundingCoord(Coordinate[] coordinates) {
        Coordinate largestCoord = new Coordinate(0, 0);
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i].x > largestCoord.x) {
                largestCoord.x = coordinates[i].x;
            }
            if (coordinates[i].y > largestCoord.y) {
                largestCoord.y = coordinates[i].y;
            }
        }
        return largestCoord;
    }

    /**
     * @return New Tile equivalent to this one.
     */
    public Tile clone() {
        throw new UnsupportedOperationException("clone() not implemented yet");
    }
}
