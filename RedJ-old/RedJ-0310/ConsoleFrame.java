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
        final Container c = getContentPane();
        c.add(new JScrollPane(console));
        setBounds(300,300,0,0);
        setSize(console.getPreferredSize());
        setTitle(title);
        
        new Thread(new Runnable(){
            public void run()
            {
                while (true)
                {
                try
                {
                    synchronized(console)
                    {
                        console.wait();
                    }
                } catch (Exception e) {}
                setVisible(true);
                }
            }
        }).start();
    }
}