import java.awt.*;
import javax.swing.*;
public class ConsoleFrame extends JFrame
{
    public Console console;
    public ConsoleFrame()
    {
        super();
        console = new Console();
        Container c = getContentPane();
        c.add(console);
        setBounds(300,300,0,0);
		setSize(console.getPreferredSize());
		setVisible(true);
    }
}