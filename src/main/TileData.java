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
                allTiles[i] = getTileByNumber(i);
            }
        }
        return allTiles;
    }

    /**
     * Get a tile by its number.
     * @param num The number of the tile.
     * @return A tile object.
     */
    private static Tile getTileByNumber(int num) {
        Coordinate[] coordinates = getCoordinatesByNumber(num);
        return new Tile(coordinates);
    }

    /**
     * Get list of coordinates for the specified polyomino.
     * @param num The polyomino number (0 - 20)
     * @return List of squares in the polyomino.
     */
    private static Coordinate[] getCoordinatesByNumber(int num) {
        if (num < 0 || num > 20) {
            throw new InvalidParameterException("num must be between 0 and 20");
        }

        // Get size of polyomino and initialize a list of coordinates with that size.
        int size;
        if (num < 1) {
            size = 1;
        } else if (num < 2) {
            size = 2;
        } else if (num < 4) {
            size = 3;
        } else if (num < 8) {
            size = 4;
        } else {
            size = 5;
        }
        Coordinate[] coordinates = new Coordinate[size];

        System.out.printf("Polyomino %s has size %s \n", num, size);

        // Get all coordinates in the polyomino.

        // For most pieces, the square at (0, 0) is occupied.
        coordinates[0] = new Coordinate(0, 0);

        switch (num) {
            // 2 squares
            case 1: {
                coordinates[1] = new Coordinate(1, 0); break;
            }
            // 3 squares
            case 2: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0); break;
            }
            case 3: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(0, 1); break;
            }
            // 4 squares
            case 4: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0); break;
            } case 5: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(0, 1); break;
            } case 6: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(1, 1); break;
            } case 7: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(0, 1);
                coordinates[3] = new Coordinate(1, 1); break;
            } case 8: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(2, 1); break;
            }
            // 5 squares
            case 9: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0);
                coordinates[4] = new Coordinate(0, 1); break;
            } case 10: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0);
                coordinates[4] = new Coordinate(1, 1); break;
            } case 11: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(0, 1);
                coordinates[4] = new Coordinate(1, 1); break;
            } case 12: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(1, 2);
                coordinates[4] = new Coordinate(2, 2); break;
            } case 13: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(0, 1);
                coordinates[3] = new Coordinate(0, 2);
                coordinates[4] = new Coordinate(1, 2); break;
            } case 14: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(2, 1);
                coordinates[4] = new Coordinate(2, 2); break;
            } case 15: {
                coordinates[0] = new Coordinate(1, 0);
                coordinates[1] = new Coordinate(1, 1);
                coordinates[2] = new Coordinate(1, 2);
                coordinates[3] = new Coordinate(0, 1);
                coordinates[4] = new Coordinate(2, 1); break;
            } case 16: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(3, 0);
                coordinates[4] = new Coordinate(4, 0); break;
            } case 17: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(1, 2);
                coordinates[4] = new Coordinate(2, 1); break;
            } case 18: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(0, 1);
                coordinates[4] = new Coordinate(0, 2); break;
            } case 19: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(1, 1);
                coordinates[3] = new Coordinate(2, 1);
                coordinates[4] = new Coordinate(3, 1); break;
            } case 20: {
                coordinates[1] = new Coordinate(1, 0);
                coordinates[2] = new Coordinate(2, 0);
                coordinates[3] = new Coordinate(1, 1);
                coordinates[4] = new Coordinate(1, 2); break;
            }
        }

        return coordinates;
    }
}
