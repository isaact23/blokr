package main;

/**
 * ADT to store polyominos.
 */
public class TileBag implements Cloneable {
    private Tile[] tiles;
    private int polyominoCount;

    public TileBag() {
        this.tiles = TileData.getAllTiles();
        this.polyominoCount = TileData.TILE_COUNT;
    }

    /**
     * @return New TileBag equivalent to this one.
     */
    public TileBag clone() {
        throw new UnsupportedOperationException("clone() not implemented yet");
    }
}
