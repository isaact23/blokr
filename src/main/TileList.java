package main;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stores all tiles remaining for a particular player.
 */
public class TileList implements Iterable<Tile> {
    private Tile[] tileArray;
    private int tileCount;

    /**
     * Initialize a TileList with all starting tiles as defined in TileData.
     */
    public TileList() {
        this.tileArray = TileData.getAllTiles();
        this.tileCount = TileData.TILE_COUNT;
    }

    /**
     * Add a tile to the list.
     * @param tile The Tile object to add.
     * @precondition There must not be a tile with the same ID as the given tile
     * already in the list.
     */
    public void add(Tile tile) {
        int id = tile.getId();
        if (tileArray[id] == null) {
            tileArray[id] = tile;
        } else {
            throw new InvalidParameterException("Cannot add this tile: There is already a tile with this ID.");
        }
        tileCount++;
    }

    /**
     * Get the tile with the specified ID and remove it from the list.
     * @param id The unique ID of the tile.
     * @return The tile, which is removed from the list.
     * @precondition The tile must be in the list.
     */
    public Tile pop(int id) {
        Tile tile = tileArray[id];
        if (tile == null) {
            throw new InvalidParameterException("Cannot pop this tile: The tile is not in the list.");
        }
        tileArray[id] = null;
        tileCount--;
        return tile;
    }

    /**
     * @return A TileIterator that iterates through the TileList.
     */
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    /**
     * An iterator that iterates through the Tiles in tileArray.
     */
    private class TileIterator implements Iterator<Tile> {
        private int index = -1;
        private int tilesCounted = 0;

        /**
         * @return True if there is another Tile to be returned by next().
         */
        @Override
        public boolean hasNext() {
            return tilesCounted < tileCount;
        }

        /**
         * @return The next non-null Tile.
         */
        @Override
        public Tile next() {
            index++;
            while (index < TileList.this.tileArray.length &&
                    TileList.this.tileArray[index] == null) {
                index++;
            }
            if (index >= TileList.this.tileArray.length) {
                throw new NoSuchElementException("There are no more tiles.");
            }
            tilesCounted++;
            return TileList.this.tileArray[index];
        }
    }
}
