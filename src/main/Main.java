package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    // Object references
    private Board board;
    private Rectangle[][] rectangles;

    // Graphic properties
    private int boardWidth = 20;
    private int boardHeight = 20;
    private int squareSize = 30;
    private int spacing = 2;

    // Colors
    private static final Color BLACK = Color.web("#050505");
    private static final Color WHITE = Color.web("#fafafa");
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
        board = player.randomGame(boardWidth, boardHeight, 4);

        // Initialize JavaFX GUI
        primaryStage.setTitle("blokr");

        GridPane grid = new GridPane();
        grid.setHgap(spacing);
        grid.setVgap(spacing);
        rectangles = new Rectangle[boardWidth][boardHeight];
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                Rectangle rec = new Rectangle();
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
        //primaryStage.show();
    }
}