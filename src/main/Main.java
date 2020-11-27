package main;

/**
 * 
 * Call other scripts to run the entire engine.
 * 
 * @author Isaac Thompson
 */
public class Main {

    public static void main(String[] args) {
        //Display display = new Display();
        Board board = new Board(8, 8, 2);
        board.print();
    }
}
