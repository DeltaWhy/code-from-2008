import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Actually a misnomer, since this is also a controller.
 * (Well, it will be when it's done.)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TetrisApplet extends JApplet implements ActionListener, KeyListener
{
    public final int BLOCK_SIZE;
    public final int WIDTH;
    public final int HEIGHT;
    public static final Color[] COLORS = {null,Color.RED,Color.GREEN,Color.YELLOW,
            Color.BLUE,Color.CYAN,Color.MAGENTA,Color.ORANGE};
    public static final int PIECE_J = 1;
    public static final int PIECE_L = 2; 
    public static final int PIECE_I = 3;
    public static final int PIECE_S = 4;
    public static final int PIECE_O = 5;
    public static final int PIECE_T = 6;
    public static final int PIECE_Z = 7;
    public static final int PIECE_CURRENT = 8;
    private PreviewPanel prevPan;
    private GridPanel gridPan;
    private Grid grid;
    private Game game;
    private JLabel testLabel, linesLabel, scoreLabel;
    private TetrisTask task;
    public TetrisApplet(int blockSize, int width, int height)
    {
        super();
        this.BLOCK_SIZE = blockSize;
        this.WIDTH = width;
        this.HEIGHT = height;
    }
    public TetrisApplet()
    {
        //defaults for running as an applet
        super();
        this.BLOCK_SIZE = 32;
        this.WIDTH = 14;
        this.HEIGHT = 20;
    }
    public void init()
    {
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(new BorderLayout());
        
        gridPan = new GridPanel(BLOCK_SIZE,WIDTH,HEIGHT);
        gridPan.setFocusable(true);
        c.add(gridPan,BorderLayout.CENTER);
        
        grid = new Grid(WIDTH,HEIGHT);
        
        gridPan.setGrid(grid);
        gridPan.setColors(COLORS);
        
        JButton b = new JButton("Button!");
        b.addActionListener(this);
        b.setFocusable(false);
        //c.add(b, BorderLayout.SOUTH);
        
        game = new Game();
        game.setGrid(grid);
        gridPan.setGame(game);
        grid.newPiece(game.currPiece);
        task = new TetrisTask(this,game);
        game.setTask(task);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setFocusable(false);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        
        prevPan = new PreviewPanel(BLOCK_SIZE, game);
        prevPan.setColors(COLORS);
        prevPan.setFocusable(false);
        rightPanel.add(prevPan);
        
        scoreLabel = new JLabel("Score: " + game.score);
        rightPanel.add(scoreLabel);        
        
        linesLabel = new JLabel("Lines: " + game.lines);
        rightPanel.add(linesLabel);        
        
        c.add(rightPanel, BorderLayout.EAST);
        
        Dimension d = c.getLayout().preferredLayoutSize(c);
        d.height += BLOCK_SIZE;
        d.width += BLOCK_SIZE;
        //why won't it size properly??
        this.setSize(d);
        this.setFocusable(false);
        //having more issues
        gridPan.requestFocusInWindow();
        gridPan.addKeyListener(this);
        //note to self: if you don't listen to something, you won't get events!
    }
    public void start()
    {
        super.start();
        gridPan.requestFocus();
        gridPan.requestFocusInWindow();
        gridPan.requestFocus();
    }
    public void paint(Graphics g)
    {
        scoreLabel.setText("Score: " + game.score);
        linesLabel.setText("Lines: " + game.lines);
        super.paint(g);
    }
    public void update(Graphics g)
    {
        paint(g); //TODO - maybe
    }
    public void actionPerformed(ActionEvent e)
    {
        game.test();
        repaint();
        requestFocus();
        requestFocusInWindow();
        requestFocus();
    }
    //KeyListener stuff
    public void keyPressed(KeyEvent e)
    {
        //call the appropriate method of game
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_DOWN) game.downArrow();
        if (key == KeyEvent.VK_LEFT) game.leftArrow();
        if (key == KeyEvent.VK_RIGHT) game.rightArrow();
        if (key == KeyEvent.VK_UP) game.upArrow();
        //that was easy...
        repaint();
    }
    public void keyReleased(KeyEvent e)
    {
        //don't care
        return;
    }
    public void keyTyped(KeyEvent e)
    {
        //still don't care
        return;
    }
}
