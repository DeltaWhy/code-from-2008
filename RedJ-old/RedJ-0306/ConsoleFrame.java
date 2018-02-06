import java.awt.*;
import javax.swing.*;
public class ConsoleFrame extends JFrame
{
    public Console console;
    public ConsoleFrame()
    {
        this("");
    }
    public ConsoleFrame(String title)
    {
        super();
        console = new Console();
        Container c = getContentPane();
        c.add(new JScrollPane(console));
        setBounds(300,300,0,0);
        setSize(console.getPreferredSize());
        setTitle(title);
        setVisible(true);
    }
}