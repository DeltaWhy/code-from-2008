import java.awt.*;
import javax.swing.*;
/**
 * The entry point into the mass of extremely awesome code (I hope).
 * Makes a Frame with the Applet inside. Also defines important constants
 *  and passes them on.
 * 
 * @author Michael Limiero
 * @version 0
 */
public class Tetris
{
    public static final int BLOCK_SIZE = 32;
    public static final int WIDTH = 14;
    public static final int HEIGHT = 20;
    public static void main(String[] args)
    {
        TetrisApplet a = new TetrisApplet(BLOCK_SIZE,WIDTH,HEIGHT);
        TetrisFrame window = new TetrisFrame(a);
        Dimension d = a.getSize();
        window.setBounds(300,300,d.width,d.height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
