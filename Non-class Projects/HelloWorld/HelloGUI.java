
/**
 * Puts some junk in a window for no apparent reason.
 * 
 * @author Michael Limiero
 * @version infinity
 */
import java.awt.*;
import javax.swing.*;
public class HelloGUI extends JFrame
{
    public HelloGUI()
    {
        super("Hello World");
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(new FlowLayout());
        JTextField f = new JTextField(" Hello, GUI!", 10);
        c.add(f);
    }
    public static void main(String[] args)
    {
        HelloGUI window = new HelloGUI();
        window.setBounds(300,300,200,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}