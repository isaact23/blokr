package main;

/**
 * A 1 to 5 square piece that can be fitted onto a board.
 */
public class Tile implements Cloneable {

    private int tileSize;

    // List of coordinates for all rotations of the piece.
    private Coordinate[][] allCoordinates;

    // Properties defining how a tile can be flipped/rotated to create new tiles.
    private boolean canFlip;
    private int rotations; // Number of unique rotations
    
    public Tile(Coordinate[] coordinates, boolean canFlip, int rotations) {
        this.tileSize = coordinates.length;
        this.canFlip = canFlip;
        this.rotations = rotations;

        this.generateCoordinates(coordinates);
    }

    /**
     * Rotate and flip the piece to generate all unique sets of coordinates.
     * @param coordinates The coordinates for the squares in the tile.
     */
    private void generateCoordinates(Coordinate[] coordinates) {
        int orientations = rotations;
        if (canFlip)
            orientations *= 2;

        // Initialize 2D array of coordinates for every orientation
        allCoordinates = new Coordinate[orientations][tileSize];
        allCoordinates[0] = coordinates;

        // Generate all rotations first
        for (int i = 1; i < rotations; i++) {
            allCoordinates[i] = rotate(allCoordinates[i - 1]);
        }

        // Flip pieces if applicable
        if (canFlip) {
            for (int i = 0; i < rotations; i++) {
                allCoordinates[i + rotations] = flip(allCoordinates[i]);
            }
        }
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
            int newX = boundingCoord.y - coordinates[i].y - 1;
            int newY = coordinates[i].x;
            newCoords[i] = new Coordinate(newX, newY);
        }

        return newCoords;
    }

    /**
     * Get the largest X and Y values in the set of coordinates.
     * @param coordinates Coordinates to search.
     * @return Largest X and Y values as a coordinate.
     */
    private Coordinate getBoundingCoord(Coordinate[] coordinates) {
        Coordinate largestCoord = new Coordinate(0, 0);
        for (Coordinate coordinate : coordinates) {
            if (coordinate.x > largestCoord.x) {
                largestCoord.x = coordinate.x;
            }
            if (coordinate.y > largestCoord.y) {
                largestCoord.y = coordinate.y;
            }
        }
        return largestCoord;
    }

    public Coordinate[] getCoordinates(int player) {
        return allCoordinates[player];
    }

    /**
     * @return New Tile equivalent to this one.
     */
    public Tile clone() {
        throw new UnsupportedOperationException("clone() not implemented yet");
    }
}
