import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Cell
{

    // Coorinates
    private int x = -1;
    private int y = -1;

    // State
    private boolean alive = false;

    // BODY
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

        if (x > 0 && y < Main.rows - 1)
        {
            // upper left
            if (grid[x - 1][y + 1].isAlive())
            {
                neighborCount++;
            }
        }
        //
        if (y < Main.rows - 1)
        {
            // up
            if (grid[x][y + 1].isAlive())
            {
                neighborCount++;
            }
        }
        //
        if (x < Main.cols - 1 && y < Main.rows - 1)
        {
            // upper right
            if (grid[x + 1][y + 1].isAlive())
            {
                neighborCount++;
            }
        }
        //
        if (x > 0)
        {
            // left
            if (grid[x - 1][y].isAlive())
            {
                neighborCount++;
            }
        }
        //
        if (x < Main.cols - 1)
        {
            // right
            if (grid[x + 1][y].isAlive())
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
        //
        if (y > 0)
        {
            // down
            if (grid[x][y - 1].isAlive())
            {
                neighborCount++;
            }
        }
        //
        if (x < Main.cols - 1 && y > 0)
        {
            // down right
            if (grid[x + 1][y - 1].isAlive())
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

    public static void update()
    {
        int neighbours = 0;
        boolean[] bitString = new boolean[cells.size()];
        for (int i = 0; i < cells.size(); i++)
        {
            Cell cell = cells.get(i);
            neighbours = cell.countNeighbours(); //count neighbours
            bitString[i] = false; //reset all next-gen
            // Rule Number 1
            // if cell dead and has 3 neighbours then live
            if (!cell.isAlive() && neighbours == 3)
            {
                bitString[i] = true;
            }
            // Rule Number 2
            // if cell lives and has less than 2 or greater than 3 neighbours then die
            else if (cell.isAlive() && (neighbours < 2 || neighbours > 3))
            {
                bitString[i] = false;
            } else
            {
                bitString[i] = cell.isAlive();
            }
        }

        for (int i = 0; i < bitString.length; i++)
        {
            if (bitString[i])
            {
                cells.get(i).live();
            } else
            {
                cells.get(i).die();
            }
        }
    }
}