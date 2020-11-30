package main;

public class TileList {
    private Tile[] tileArray;

    /**
     * Initialize a TileList with all starting tiles as defined in TileData.
     */
    public TileList() {
        this.tileArray = TileData.getAllTiles();
    }
}
