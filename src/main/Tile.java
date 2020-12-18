package main;

/**
 * A 1 to 5 square piece that can be fitted onto a board.
 */
public class Tile implements Cloneable {

    // List of coordinates for all rotations of the piece.
    private final Coordinate[][] allCoordinates;

    // The UNIQUE ID of this tile. Any tile with a different shape will have a different ID.
    private final int tileId;

    // Number of squares in a tile.
    private final int tileSize;

    // Properties defining how a tile can be flipped/rotated to create new tiles.
    private final boolean canFlip;
    private final int rotations; // Number of unique rotations
    private final int orientations; // Rotations * canFlip (2 if true, 1 if false)

    /**
     * Construct a tile with only one set of coordinates.
     * @param coordinates All coordinates within this tile.
     * @param tileId The unique identifier for this tile.
     * @param canFlip Whether flipping returns a unique form.
     * @param rotations How many rotations produces a unique form.
     */
    public Tile(Coordinate[] coordinates, int tileId, boolean canFlip, int rotations) {
        this.tileId = tileId;
        this.tileSize = coordinates.length;
        this.canFlip = canFlip;
        this.rotations = rotations;
        if (canFlip) {
            this.orientations = rotations * 2;
        } else {
            this.orientations = rotations;
        }
        this.allCoordinates = generateCoordinates(coordinates);
    }

    /**
     * Construct a tile with all sets of coordinates.
     * @param allCoordinates All coordinates within this tile in all orientations.
     * @param tileId The unique identifier for this tile.
     * @param canFlip Whether flipping returns a unique form.
     * @param rotations How many rotations produces a unique form.
     */
    public Tile(Coordinate[][] allCoordinates, int tileId, boolean canFlip, int rotations) {
        this.tileId = tileId;
        this.tileSize = allCoordinates[0].length;
        this.canFlip = canFlip;
        this.rotations = rotations;
        if (canFlip) {
            this.orientations = rotations * 2;
        } else {
            this.orientations = rotations;
        }
        this.allCoordinates = allCoordinates;
    }

    /**
     * @return This tile's unique ID.
     */
    public int getId() {
        return tileId;
    }

    /**
     * @return Coordinate sets for each orientation.
     */
    public Coordinate[][] getAllCoordinates() { return allCoordinates; }

    public Coordinate[] getCoordinates(int orientation) {
        return allCoordinates[orientation];
    }

    /**
     * Deep copy this tile.
     * @return New Tile equivalent to this one.
     */
    public Tile clone() {
        int boardWidth = allCoordinates.length;
        int boardHeight = allCoordinates[0].length;
        Coordinate[][] newCoordinates = new Coordinate[boardWidth][boardHeight];
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                newCoordinates[x][y] = allCoordinates[x][y].clone();
            }
        }
        return new Tile(newCoordinates, this.tileId, this.canFlip, this.rotations);
    }

    /**
     * @return Maximum number of ways to orient this tile.
     */
    public int getOrientations() {
        return orientations;
    }

    /**
     * Rotate and flip the piece to generate all unique sets of coordinates.
     * @param coordinates The coordinates for the squares in the tile.
     * @return A two-dimensional array of all sets of coordinates.
     */
    private Coordinate[][] generateCoordinates(Coordinate[] coordinates) {
        int orientations = rotations;
        if (canFlip)
            orientations *= 2;

        // Initialize 2D array of coordinates for every orientation
        Coordinate[][] coordinateArray = new Coordinate[orientations][tileSize];
        coordinateArray[0] = coordinates;

        // Generate all rotations first
        for (int i = 1; i < rotations; i++) {
            coordinateArray[i] = rotate(coordinateArray[i - 1]);
        }

        // Flip pieces if applicable
        if (canFlip) {
            for (int i = 0; i < rotations; i++) {
                coordinateArray[i + rotations] = flip(coordinateArray[i]);
            }
        }
        return coordinateArray;
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
            int newX = boundingCoord.y - coordinates[i].y;
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
    public static Coordinate getBoundingCoord(Coordinate[] coordinates) {
        int largestX = 0;
        int largestY = 0;
        for (Coordinate coordinate : coordinates) {
            if (coordinate.x > largestX) {
                largestX = coordinate.x;
            }
            if (coordinate.y > largestY) {
                largestY = coordinate.y;
            }
        }
        return new Coordinate(largestX, largestY);
    }
}
