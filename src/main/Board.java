package main;

import java.util.HashMap;
import java.util.Stack;

/**
 * Store data on which players occupy which grid squares.
 */
public class Board {
    // Board properties and data
    private final int width;
    private final int height;
    private int[][] squares; // 0 if empty, others ints for other players

    /**
     * Create a game board at its entry state.
     * @param width Squares spanning x direction.
     * @param height Squares spanning y direction.
     */
    public Board(int width, int height) {
        // Initialize grid
        this.width = width;
        this.height = height;
        this.squares = new int[width][height];
    }

    /**
     * Print board to terminal.
     */
    public void print() {
        System.out.println();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.printf("%c  ", getSymbolForPlayer(
                        squares[x][y]
                ));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Get symbol for a specified player.
     * @param player The player number, 0 for none.
     */
    private char getSymbolForPlayer(int player) {
        switch (player) {
            case 0: return '-';
            case 1: return 'X';
            case 2: return 'O';
            case 3: return '#';
            case 4: return '&';
        }
        return '-';
    }
}
