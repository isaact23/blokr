package main;

public class Board {

    private int width;
    private int height;
    
    private int[][] squares;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.squares = new int[width][height];
    }

}
