import java.awt.*;
import javax.swing.*;
public class ConsoleFrame extends JFrame
{
    public Console console;
    //public JLabel debugLabel; //TEMP
    public ConsoleFrame()
    {
        this("");
    }
    public ConsoleFrame(String title)
    {
        super();
        console = new Console();
        //debugLabel = new JLabel("Waiting"); //TEMP
        final Container c = getContentPane();
        //c.setLayout(new GridLayout(2,1)); //TEMP
        c.add(new JScrollPane(console));
        //c.add(debugLabel); //TEMP
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
                //debugLabel.setText(Integer.toString(console.inputStart)); //TEMP
                }
            }
        }, "VisibleThing").start();
    }
}