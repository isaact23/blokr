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
public class GamePlayer {

    public Board randomGame(int width, int height) {
        Board board = new Board(width, height, 1);
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
            board.pushMove(nextMove);
            board.print();
        }
        return board;
    }

    public void timeListMoves() {
        Board board = new Board(8, 8, 2);
        long startTime = System.nanoTime();
        ArrayList<Move> moves = board.listMoves(0);
        long endTime = System.nanoTime();
        System.out.println("That took " + ((endTime - startTime) / 1000000) + " milliseconds");
    }
}
