package main;

import org.junit.jupiter.api.MethodOrderer;

import java.util.ArrayList;
import java.util.Random;

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
        ArrayList<Move> moves = board.listMoves(0);
        //for (int i = 0; i < moves.size(); i++) {
        //    System.out.printf("Piece %d\n", moves.get(i).tile.getId());
        //}

        Random rand = new Random();
        int randint = rand.nextInt(moves.size());
        board.pushMove(moves.get(randint));

        board.print();
    }
}
