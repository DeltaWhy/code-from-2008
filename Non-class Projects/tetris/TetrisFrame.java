import java.awt.*;
import javax.swing.*;
public class TetrisFrame extends JFrame
{
//totally stolen out of my attempt at Battleship
    private TetrisApplet a;
    public TetrisFrame(TetrisApplet a)
    {
        super("TETRIS!!!!1");
        this.setResizable(false);
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(new GridLayout());
        this.a = a;
        c.add(a);
        a.init();
        a.start();
        this.setFocusable(false);
        a.requestFocusInWindow();
        this.toFront();
    }
}