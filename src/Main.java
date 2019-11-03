import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Calendar;

public class Main extends Application
{

    public static ObservableList<Node> child;
    //
    private static final String title = "JellyBeanci";
    public static final int width = 1200;
    public static final int height = 800;

    public static int cols = 120;
    public static int rows = 80;

    public static int w = width / cols;
    public static int h = height / rows;

    private static Color backcolor = Color.rgb(51, 51, 51);

    ArrayList<Rectangle> rectangles = new ArrayList<>();
    private static Timeline update;

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane root = new Pane();
        child = root.getChildren();


        for (int j = 0; j < rows; j++)
        {
            for (int i = 0; i < cols; i++)
            {
                Cell cell = new Cell(i,j);
                child.add(cell.getNode());
            }
        }
        for (int i = 0; i < 1500; i++)
        {
            int x = Utils.getRandomInt(cols);
            int y = Utils.getRandomInt(rows);
            //
            Cell.grid[x][y].live();
        }

        root.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case F1:
                {
                    // PLAY
                    update.play();
                    break;
                }
                case F2:
                {
                    // PAUSE
                    update.pause();
                    break;
                }
                case F3:
                {
                    // RESET
                    update.pause();
                    for (Cell cell :Cell.cells)
                    {
                        cell.die();
                    }
                    for (int i = 0; i < 1500; i++)
                    {
                        Cell.cells.get(Utils.getRandomInt(Cell.cells.size())).live();
                    }
                    break;
                }
            }
        });
        update = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            Cell.update();
        }));
        update.setCycleCount(Timeline.INDEFINITE);
        update.setRate(1);
        update.setAutoReverse(false);
        //update.play(); //uncomment for play when start
        //
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, width - 10, height - 10, backcolor));
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
