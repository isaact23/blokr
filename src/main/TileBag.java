package main;

/**
 * ADT to store polyominos.
 */
public class TileBag {
    private Tile[] tiles;
    private int polyominoCount;

    public TileBag() {
        this.tiles = TileData.getAllTiles();
        this.polyominoCount = TileData.TILE_COUNT;
    }
}
