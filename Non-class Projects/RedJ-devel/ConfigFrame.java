import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Hashtable;
public class ConfigFrame extends JFrame implements ActionListener
{
    ImageIcon icon;
    Hashtable<String,String> config;
    JButton save;
    JButton quit;
    FileField jdkPath;
    FileField jeditPath;
    FileField bluejPath;
    Object lock;
    
    public static String REDJPATH;
    
    public ConfigFrame()
    {
        this(System.getProperty("user.dir")+File.separator);
        //use the current directory and hope it's where RedJ is
    }
    public ConfigFrame(String redJPath)
    {
        lock = new Object();
        
        REDJPATH = redJPath;
        setBounds(300,300,500,300);
        URL imgURL = this.getClass().getResource("redj-icon.gif");
        if (imgURL != null) icon = new ImageIcon(imgURL);
        if (icon != null) 
        {
            setIconImage(icon.getImage());
        }
        setTitle("RedJ config");
        readConfig();
        
        save = new JButton("Save");
        quit = new JButton("Quit");
        jdkPath = new FileField();
        jeditPath = new FileField();
        bluejPath = new FileField();
        
        String s;
        s = config.get("JDKPATH");
        if (s != null && !s.equals("")) jdkPath.setText(s);
        s = config.get("JEDITPATH");
        if (s != null && !s.equals("")) jeditPath.setText(s);
        s = config.get("BLUEJPATH");
        if (s != null && !s.equals("")) bluejPath.setText(s);
        
        Dimension d = jdkPath.getSize();
        d.width = this.getWidth();
        jdkPath.setSize(d);
        jeditPath.setSize(d);
        bluejPath.setSize(d);
        
        save.addActionListener(this);
        quit.addActionListener(this);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(save);
        buttonPanel.add(quit);
        JPanel mainPanel = new JPanel(new GridLayout(7,1));
        mainPanel.add(new JLabel("JDK Path (ex: C:\\Program Files\\Java\\jdk1.5.0_09\\bin)"));
        mainPanel.add(jdkPath);
        mainPanel.add(new JLabel("jEdit Path (ex: C:\\Program Files\\jEdit)"));
        mainPanel.add(jeditPath);
        mainPanel.add(new JLabel("BlueJ Path (optional - ex: C:\\BlueJ\\bluej.exe)"));
        mainPanel.add(bluejPath);
        
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private void readConfig()
    {
        try
        {
            ConfigReader cr = new ConfigReader(REDJPATH + "redj-global.cfg");
            config = cr.read();
            cr.close();
        } catch (IOException e) {
            //e.printStackTrace();
            config = new Hashtable<String,String>();
        }
    }
    private void writeConfig()
    {
        config.put("JDKPATH",jdkPath.getText());
        config.put("JEDITPATH",jeditPath.getText());
        config.put("BLUEJPATH",bluejPath.getText());
        try
        {
            ConfigWriter cw = new ConfigWriter(REDJPATH + "redj-global.cfg");
            cw.write(config);
            cw.close();
            JOptionPane.showMessageDialog(this,"File saved.","Success",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.err.println("couldn't write config file");
            ex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == save) writeConfig();
        if (e.getSource() == quit) System.exit(0);
    }
    public void setVisible(boolean b)
    {
        super.setVisible(b);
        if (!b) lock.notifyAll();
    }
    public static void main(String[] args)
    {
        ConfigFrame a;
        if (args.length == 0)
        {
            a = new ConfigFrame();
        } else if (args.length == 1) {
            a = new ConfigFrame(args[0]);
        } else {
            System.err.println("Wrong number of arguments. Perhaps you missed some quotation marks?");
            System.err.println("Ignoring your arguments...");
            a = new ConfigFrame();
        }
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
    }
}