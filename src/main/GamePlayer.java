package main;

import org.junit.jupiter.api.MethodOrderer;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Generate a game and output a Board object.
 *
 * @author Isaac Thompson
 */
public class GamePlayer {

    public Board randomGame(int width, int height, int playerCount) {
        Board board = new Board(width, height, playerCount);
        ArrayList<Move> moves;
        Random rand = new Random();
        int randint;
        int playersRemaining;
        while (true) {
            playersRemaining = playerCount;
            for (int i = 0; i < playerCount; i++) {
                moves = board.listMoves(i);
                if (moves.size() == 0) {
                    playersRemaining--;
                } else {
                    randint = rand.nextInt(moves.size());
                    Move nextMove = moves.get(randint);
                    board.pushMove(nextMove);
                }
            }
            if (playersRemaining == 0) {
                break;
            }
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
