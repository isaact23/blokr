package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    // Object references
    private Board board;
    private Rectangle[][] rectangles;

    // Graphic properties
    private int boardWidth = 12;
    private int boardHeight = 12;
    private int squareSize = 40;

    // Colors
    private Color backgroundColor = Color.web("#050505");

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialize application.
     */
    @Override
    public void start(Stage primaryStage) {
        GamePlayer player = new GamePlayer();
        board = player.randomGame(boardWidth, boardHeight);

        // Initialize JavaFX GUI
        primaryStage.setTitle("blokr");

        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        rectangles = new Rectangle[boardWidth][boardHeight];
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                Rectangle rec = new Rectangle();
                rectangles[x][y] = rec;
                rec.setWidth(squareSize);
                rec.setHeight(squareSize);
                rec.setFill(Color.WHITE);
                GridPane.setRowIndex(rec, x);
                GridPane.setColumnIndex(rec, y);
                grid.getChildren().addAll(rec);
            }
        }

        Pane root = new Pane(grid);
        primaryStage.setScene(new Scene(root, squareSize * boardWidth, squareSize * boardHeight));
        primaryStage.show();
        update();
    }

    /**
     * Update the grid based on the given board.
     */
    public void update() {
        int[][] squares = board.getSquares();
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                int square = squares[x][y];
                Color color = Color.WHITE;
                switch (square) {
                    case -1: { break; }
                    case 0: {
                        color = Color.BLUE; break;
                    } case 1: {
                        color = Color.RED; break;
                    } case 2: {
                        color = Color.GREEN; break;
                    } case 3: {
                        color = Color.YELLOW; break;
                    }
                }
                rectangles[x][y].setFill(color);
            }
        }
        //primaryStage.show();
    }
}