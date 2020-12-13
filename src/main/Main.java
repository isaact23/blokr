package main;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main extends Application {
    // Object references
    private Board board;
    private Rectangle[][] rectangles;

    // Gameplay variables
    private Iterator<Tile> playerTileIterator;
    private Tile selectedTile;
    private int orientation;
    private boolean isPlayerTurn = false;
    private int currentPlayer;
    private Random random = new Random();

    // Graphic properties
    private int boardWidth = 20;
    private int boardHeight = 20;
    private int squareSize = 30;
    private int spacing = 2;
    private int mouseGridX = 0;
    private int mouseGridY = 0;

    // Colors
    private static final Color BLACK = Color.web("#050505");
    private static final Color WHITE = Color.web("#c7c7c7");
    private static final Color BLUE = Color.web("#4985e6");
    private static final Color RED = Color.web("#e64e49");
    private static final Color GREEN = Color.web("#49e65b");
    private static final Color YELLOW = Color.web("#ebeb31");

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialize application.
     */
    @Override
    public void start(Stage primaryStage) {
        GamePlayer player = new GamePlayer();
        //board = new Board(boardWidth, boardHeight, 1);
        board = player.randomGame(boardWidth, boardHeight, 4);

        // Initialize JavaFX GUI
        primaryStage.setTitle("blokr");

        // Initialize the grid and all rectangles within it
        GridPane grid = new GridPane();
        grid.setHgap(spacing);
        grid.setVgap(spacing);
        rectangles = new Rectangle[boardWidth][boardHeight];
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                Rectangle rec = new Rectangle();
                int finalX = x;
                int finalY = y;
                rec.hoverProperty().addListener((
                        ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
                    mouseGridX = finalX;
                    mouseGridY = finalY;
                    update();
                });
                // Add On Mouse Click listener
                rectangles[x][y] = rec;
                rec.setWidth(squareSize);
                rec.setHeight(squareSize);
                rec.setFill(Color.WHITE);
                GridPane.setRowIndex(rec, y);
                GridPane.setColumnIndex(rec, x);
                grid.getChildren().addAll(rec);
            }
        }
        BackgroundFill backgroundFill = new BackgroundFill(BLACK,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        grid.setBackground(background);

        // Initialize the Stage
        Pane root = new Pane(grid);
        int sceneWidth = (squareSize + spacing) * boardWidth - spacing;
        int sceneHeight = (squareSize + spacing) * boardHeight - spacing;
        primaryStage.setScene(new Scene(root, sceneWidth, sceneHeight));
        primaryStage.show();
        update();

        // Add key bindings
        primaryStage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                System.out.println("Previous tile");
            } else if (e.getCode() == KeyCode.D) {
                System.out.println("Next tile");
            } else if (e.getCode() == KeyCode.W) {
                System.out.println("Next orientation");
            } else if (e.getCode() == KeyCode.S) {
                System.out.println("Previous orientation");
            }
        });
        primaryStage.getScene().setOnMouseClicked(e -> {
            //if (e.)
        });
    }

    /**
     * Update the grid based on the given board.
     */
    public void update() {
        int[][] squares = board.getSquares();
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                int square = squares[x][y];
                Color color = WHITE;
                switch (square) {
                    case -1: { break; }
                    case 0: {
                        color = BLUE; break;
                    } case 1: {
                        color = RED; break;
                    } case 2: {
                        color = GREEN; break;
                    } case 3: {
                        color = YELLOW; break;
                    }
                }
                rectangles[x][y].setFill(color);
            }
        }
        if (isPlayerTurn) {
            if (playerTileIterator == null || !playerTileIterator.hasNext()) {
                playerTileIterator = board.getTileList(currentPlayer).iterator();
                selectedTile = playerTileIterator.next();
            }
            rectangles[mouseGridX][mouseGridY].setFill(BLACK);
        }
    }

    /**
     * Create the grid of rectangles to replace the game settings GUI.
     */
    public void initializeGameWindow() {
        throw new UnsupportedOperationException("initializeGameWindow() not implemented yet");
    }

    /**
     * Alternate between Player and CPU.
     */
    public void gameLoop() throws InterruptedException {
        while (true) {
            if (!playerTurn(0)) {
                break;
            }
            while (isPlayerTurn) {
                Thread.sleep(100);
            }
            if (!cpuTurn(1)) {
                break;
            }
        }
    }

    /**
     * Allow the player to input a move.
     * @param player The player index to play as.
     * @return Whether the player is able to move.
     */
    public boolean playerTurn(int player) {
        currentPlayer = player;
        isPlayerTurn = true;
    }

    /**
     * Call the CPU to make a move.
     * @param player The player index to play as.
     * @return Whether the CPU is able to move.
     */
    public boolean cpuTurn(int player) {
        ArrayList<Move> moves;
        Random rand = new Random();
        moves = board.listMoves(player);
        if (moves.size() == 0) {
            return false;
        }
        int randint = rand.nextInt(moves.size());
        Move nextMove = moves.get(randint);
        board.pushMove(nextMove);
    }
}