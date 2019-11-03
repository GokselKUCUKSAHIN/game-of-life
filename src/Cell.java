import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Cell
{

    //Coorinates
    private int x = -1;
    private int y = -1;

    private boolean alive = false;

    //BODY
    Rectangle body;

    public static Cell grid[][] = new Cell[Main.cols][Main.rows];
    public static ArrayList<Cell> cells = new ArrayList<>();


    public Cell(int x, int y)
    {
        if (x >= 0 && x < Main.cols && y >= 0 && y < Main.rows)
        {
            this.x = x;
            this.y = y;
        }
        draw();
        grid[x][y] = this;
        cells.add(this);
    }

    public void draw()
    {
        body = new Rectangle(this.x * Main.w, this.y * Main.h, Main.w, Main.h);
        body.setFill(Color.SNOW);
        body.setStroke(Color.GRAY);
        body.setStrokeWidth(1);
    }


    public int countNeighbours()
    {
        int neighborCount = 0;
        if (x < Main.cols - 1)
        {
            // right
            if (grid[x + 1][y].isAlive())
            {
                neighborCount++;
            }
        }
        if (x > 0)
        {
            // left
            if (grid[x - 1][y].isAlive())
            {
                neighborCount++;
            }
        }
        if (y < Main.rows - 1)
        {
            // up
            if (grid[x][y + 1].isAlive())
            {
                neighborCount++;
            }
        }
        if (y > 0)
        {
            // down
            if (grid[x][y - 1].isAlive())
            {
                neighborCount++;
            }
        }
        //
        if (x > 0 && y > 0)
        {
            // down left
            if (grid[x - 1][y - 1].isAlive())
            {
                neighborCount++;
            }
        }
        if (x < Main.cols - 1 && y > 0)
        {
            // down right
            if (grid[x + 1][y - 1].isAlive())
            {
                neighborCount++;
            }
        }
        if (x > 0 && y < Main.rows - 1)
        {
            // upper left
            if (grid[x - 1][y + 1].isAlive())
            {
                neighborCount++;
            }
        }
        if (x < Main.cols - 1 && y < Main.rows - 1)
        {
            // upper right
            if (grid[x + 1][y + 1].isAlive())
            {
                neighborCount++;
            }
        }
        return neighborCount;
    }

    public Node getNode()
    {
        return this.body;
    }

    public boolean isAlive()
    {
        return this.alive;
    }

    public void live()
    {
        if (!alive)
        {
            //if isnt alive before
            this.alive = true;
            this.body.setFill(Color.BLACK);
        }
    }

    public void die()
    {
        if (alive)
        {
            //if isnt dead before
            this.alive = false;
            this.body.setFill(Color.SNOW);
        }
    }

    public void printNeighbours()
    {
        int count = countNeighbours();
        if (count != 0)
        {
            System.out.printf("x: %2d, y: %2d = %d\n", x, y, count);
        }
    }
}
