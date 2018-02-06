import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GridPanel extends JPanel
{
    public final int BLOCK_SIZE;
    public final int WIDTH;
    public final int HEIGHT;
    public Grid grid;
    private Color[] colors;
    private Game game;
    public GridPanel(int blockSize, int width, int height)
    {
        super();
        this.BLOCK_SIZE = blockSize;
        this.WIDTH = width;
        this.HEIGHT = height;
        
        Dimension d = new Dimension(width*blockSize+3,height*blockSize+3);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setSize(d);
        this.setBackground(Color.WHITE);
    }
    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }
    public void setGame(Game game)
    {
        this.game = game;
    }
    public void setColors(Color[] colors)
    {
        this.colors = colors;
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        if (colors != null) paintBlocks(g);
        paintGrid(g);
    }
    private void paintGrid(Graphics g)
    {
        g.setColor(Color.BLACK);
        for (int x = 0; x <= WIDTH; x++)
        {
            g.drawLine(x*BLOCK_SIZE+1, 1,  x*BLOCK_SIZE+1, HEIGHT*BLOCK_SIZE+1);
        }
        for (int y = 0; y <= HEIGHT; y++)
        {
            g.drawLine(1, y*BLOCK_SIZE+1, WIDTH*BLOCK_SIZE+1,  y*BLOCK_SIZE+1);
        }
        g.drawRect(0,0,BLOCK_SIZE*WIDTH+2,BLOCK_SIZE*HEIGHT+2);
    }
    private void paintBlocks(Graphics g)
    {
        if (grid == null) return;
        for (int y = 0; y < grid.data.length; y++)
            for (int x = 0; x < grid.data[0].length; x++)
            {
                if (grid.data[y][x] != 0)
                    paintBlock(g,grid.data[y][x],new Point(x,y));
            }
    }
    private void paintBlock(Graphics g, int color, Point p)
    {
        if (colors == null) return;
        if (color == TetrisApplet.PIECE_CURRENT) color = game.currPiece;
        Color c = colors[color];
        int x,y;
        x = BLOCK_SIZE * p.x + 1;
        y = BLOCK_SIZE * p.y + 1;
        g.setColor(c);
        g.fillRect(x,y,BLOCK_SIZE,BLOCK_SIZE);
    }
}
