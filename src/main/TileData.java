package main;

import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Stores the shapes of the 21 free polyominos from sizes 1 to 5.
 */
public class TileData {

    public static final int TILE_COUNT = 21;
    private static Tile[] allTiles = null;

    /**
     * @return All 21 free polyominos.
     */
    public static Tile[] getAllTiles() {
        if (allTiles == null) {
            allTiles = new Tile[TILE_COUNT];
            for (int i = 0; i < TILE_COUNT; i++) {
                allTiles[i] = getTileById(i);
            }
        }
        Tile[] tilesClone = new Tile[TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            tilesClone[i] = allTiles[i].clone();
        }
        return tilesClone;
    }

    /**
     * Get list of coordinates for the specified polyomino.
     * @param id The polyomino idber (0 - 20)
     * @return A tile object.
     */
    private static Tile getTileById(int id) {
        if (id < 0 || id > 20) {
            throw new InvalidParameterException("id must be between 0 and 20");
        }

        // Get size of polyomino and initialize a list of coordinates with that size.
        int size;
        if (id < 1) {
            size = 1;
        } else if (id < 2) {
            size = 2;
        } else if (id < 4) {
            size = 3;
        } else if (id < 9) {
            size = 4;
        } else {
            size = 5;
        }
        Coordinate[] coordinates = new Coordinate[size];
        boolean canFlip = false;
        int rotations = 1; // Unique rotations (1, 2 or 4)

        // Get all coordinates and flip/rotation data in the polyomino.

        // For most pieces, the square at (0, 0) is occupied.
        coordinates[0] = new Coordinate(0, 0);

        switch (id) {
            // 2 squares
            case 1: {
                rotations = 2;
                coordinates[1] = new Coordinate(1, 0); break;
            }
            // 3 squares
            case 2: {
                rotations = 2;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0); break;
            }
            case 3: {
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(0, 1); break;
            }
            // 4 squares
            case 4: {
                rotations = 2;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0); break;
            } case 5: {
                canFlip = true;
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(0, 1); break;
            } case 6: {
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(1, 1); break;
            } case 7: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(0, 1);
                coordinates[3] = new Coordinate(1, 1); break;
            } case 8: {
                canFlip = true;
                rotations = 2;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(2, 1); break;
            }
            // 5 squares
            case 9: {
                rotations = 2;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0);
                coordinates[4] = new Coordinate(4, 0); break;
            } case 10: {
                canFlip = true;
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0);
                coordinates[4] = new Coordinate(0, 1); break;
            } case 11: {
                canFlip = true;
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0);
                coordinates[4] = new Coordinate(1, 1); break;
            } case 12: {
                canFlip = true;
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(0, 1);
                coordinates[4] = new Coordinate(1, 1); break;
            } case 13: {
                canFlip = true;
                rotations = 2;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(1, 2);
                coordinates[4] = new Coordinate(2, 2); break;
            } case 14: {
                coordinates[0] = new Coordinate(1, 0);
                coordinates[1] = new Coordinate(1, 1);
                coordinates[2] = new Coordinate(1, 2);
                coordinates[3] = new Coordinate(0, 1);
                coordinates[4] = new Coordinate(2, 1); break;
            } case 15: {
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(2, 1);
                coordinates[4] = new Coordinate(2, 2); break;
            } case 16: {
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(0, 1);
                coordinates[3] = new Coordinate(0, 2);
                coordinates[4] = new Coordinate(1, 2); break;
            } case 17: {
                canFlip = true;
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(1, 2);
                coordinates[4] = new Coordinate(2, 1); break;
            } case 18: {
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(0, 1);
                coordinates[4] = new Coordinate(0, 2); break;
            } case 19: {
                canFlip = true;
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(2, 1);
                coordinates[4] = new Coordinate(3, 1); break;
            } case 20: {
                rotations = 4;
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(1, 1);
                coordinates[4] = new Coordinate(1, 2); break;
            }
        }

        // Ensure none of the entries are null.
        for (Coordinate coordinate : coordinates) {
            if (coordinate == null) {
                throw new RuntimeException("There is a null element in coordinates.");
            }
        }

        return new Tile(coordinates, id, canFlip, rotations);
    }
}
