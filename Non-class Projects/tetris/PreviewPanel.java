import java.awt.*;
import javax.swing.*;

public class PreviewPanel extends JPanel
{
    public final int BLOCK_SIZE;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;
    private Color[] colors;
    private Game game;
    public PreviewPanel(int blockSize, Game game)
    {
        super();
        this.BLOCK_SIZE = blockSize;
        
        Dimension d = new Dimension(WIDTH*blockSize+3,HEIGHT*blockSize+3);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setSize(d);
        this.setBackground(Color.WHITE);
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
        /*for (int x = 0; x <= WIDTH; x++)
        {
            g.drawLine(x*BLOCK_SIZE+1, 1,  x*BLOCK_SIZE+1, HEIGHT*BLOCK_SIZE+1);
        }
        for (int y = 0; y <= HEIGHT; y++)
        {
            g.drawLine(1, y*BLOCK_SIZE+1, WIDTH*BLOCK_SIZE+1,  y*BLOCK_SIZE+1);
        }*/
        g.drawRect(0,0,BLOCK_SIZE*WIDTH+1,BLOCK_SIZE*HEIGHT+1);
        g.drawRect(1,1,BLOCK_SIZE*WIDTH-1,BLOCK_SIZE*HEIGHT-1);
    }
    private void paintBlocks(Graphics g)
    {
        boolean[][] piece = Pieces.PIECES[game.nextPiece]; //brain-sprain!
        int w = piece[0].length;
        int h = piece.length;
        int xo = (BLOCK_SIZE*WIDTH - BLOCK_SIZE*w)/2+1; //center the piece
        int yo = (BLOCK_SIZE*HEIGHT - BLOCK_SIZE*h)/2+1;
        
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                if (piece[y][x]) paintBlock(g,game.nextPiece,new Point(xo+x*BLOCK_SIZE,yo+y*BLOCK_SIZE));
                //the point is a pixel, not a grid point
    }
    private void paintBlock(Graphics g, int color, Point p)
    {
        //p is a pixel-point, not a grid-point
        if (colors == null) return;
        Color c = colors[color];
        g.setColor(c);
        g.fillRect(p.x,p.y,BLOCK_SIZE,BLOCK_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(p.x,p.y,BLOCK_SIZE,BLOCK_SIZE);
    }
}
