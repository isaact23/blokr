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
        Display display = new Display();
        Board board = new Board(12, 12, 1);
        randomGame();
    }

    private static void randomGame() {
        Board board = new Board(12, 12, 1);
        ArrayList<Move> moves;
        Random rand = new Random();
        int randint;
        while (true) {
            moves = board.listMoves(0);
            if (moves.size() == 0) {
                break;
            }
            randint = rand.nextInt(moves.size());
            Move nextMove = moves.get(randint);
            nextMove.print();
            board.pushMove(nextMove);
            board.print();
        }
    }

    private static void timeListMoves() {
        Board board = new Board(8, 8, 2);
        long startTime = System.nanoTime();
        ArrayList<Move> moves = board.listMoves(0);
        long endTime = System.nanoTime();
        System.out.println("That took " + ((endTime - startTime) / 1000000) + " milliseconds");
    }
}
