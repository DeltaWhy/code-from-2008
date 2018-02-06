import java.awt.*;
import javax.swing.*;
public class FrameView extends JFrame
{
    private AppletView a;
    public FrameView(AppletView a)
    {
        super("Battleship");
        this.setResizable(false);
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(new GridLayout());
        this.a = a;
        c.add(a);
        a.init();
        a.start();
    }
/*  Probably unnecessary and currently broken.
    public static void main(String[] args)
    {
        FrameView window = new FrameView(new AppletView());
        window.setBounds(300,300,480,640);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }*/
}