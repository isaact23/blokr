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
        GameState gameState = new GameState(8, 8, 2);
        gameState.print();
    }
}
