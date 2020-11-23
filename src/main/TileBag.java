package main;

import java.util.Iterator;

/**
 * ADT to store polyominos.
 */
public class TileBag implements Iterable<Tile> {
    private Tile[] tiles;
    private int polyominoCount;

    public TileBag() {
        this.tiles = TileData.getAllTiles();
        this.polyominoCount = TileData.TILE_COUNT;
    }

    @Override
    public Iterator<Tile> iterator() {
        return null;
    }
}
