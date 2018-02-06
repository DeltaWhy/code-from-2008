//Michael Limiero
//
// RedJ - a helper program to make BlueJ suck less
//  or it might end up being a BlueJ replacement.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.io.*;
import java.util.Scanner;
import java.util.Hashtable;
public class RedJ extends JFrame implements ActionListener, WindowListener
{
    private ImageIcon icon;
    private JPanel panel1;
    private JPanel panel2;
    private JButton openLast;
    private JButton open;
    private JButton newProject;
    private JButton compile;
    private JButton run;
    private JButton compileRun;
    
    private String currentProject="";
    private String mainClass="";
    private FileList listFrame;
    private ConsoleFrame consoleFrame;
    private Hashtable config;
    private Hashtable projectConfig;
    
    public static String BLUEJPATH;
    public static String JDKPATH;
    public static String REDJPATH;
    
    
    public RedJ(String redJPath)
    {
        REDJPATH = redJPath;
        if (REDJPATH.charAt(REDJPATH.length()-1) != File.separatorChar)
            REDJPATH += File.separator;
                
        try
        {
            ConfigReader cr = new ConfigReader(REDJPATH + "redj-global.cfg");
            config = cr.read();
            cr.close();
        } catch (IOException e) {
            e.printStackTrace();
            config = new Hashtable();
        }
    
        BLUEJPATH = (String)config.get("BLUEJPATH");
                
        JDKPATH = (String)config.get("JDKPATH");
        if (JDKPATH != null && JDKPATH.charAt(JDKPATH.length()-1) != File.separatorChar) 
            JDKPATH += File.separator;
        
        URL imgURL = this.getClass().getResource("redj-icon.gif");
        if (imgURL != null) icon = new ImageIcon(imgURL);
        if (icon != null) setIconImage(icon.getImage());
        
        setBounds(200,300,300,300);
        setTitle("RedJ");
        
        addWindowListener(this);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        panel2.setLayout(new GridLayout(3,1));
        
        openLast = new JButton("Open Last Project");
        open = new JButton("Open Other Project");
        newProject = new JButton("New Project");
        compile = new JButton("Compile Project");
        run = new JButton("Run Project");
        compileRun = new JButton("Compile & Run");
        openLast.addActionListener(this);
        open.addActionListener(this);
        newProject.addActionListener(this);
        compile.addActionListener(this);
        run.addActionListener(this);
        compileRun.addActionListener(this);
        
        panel1.add(openLast);
        panel1.add(open);
        panel1.add(newProject);
        panel2.add(compile);
        panel2.add(run);
        panel2.add(compileRun);
        
        Container c = getContentPane();
        c.setLayout(new CardLayout());
        c.add(panel1, "1");
        c.add(panel2, "2");
        
        setVisible(true);
        

    }
    public RedJ()
    {
        this(System.getProperty("user.dir")+File.separator);
    }
        
    private void open()
    {
        String dirName = openDialog();
        if ("".equals(dirName)) return;
        open(dirName);
    }
    private void open(String dirName)
    {
        if (dirName == null || dirName.trim().equals(""))
        {
            open();
            return;
        }
        currentProject = dirName;
        config.put("LASTPROJECT", currentProject);
        File dir = new File(dirName);
        setTitle("RedJ - " + dir.getName());
        listFrame = new FileList(dirName);
        //consoleFrame = new ConsoleFrame();
        Container p = getContentPane();
        CardLayout l = (CardLayout)p.getLayout();
        l.last(p); //TODO: use name instead... maybe
        File configFile = new File(dirName + File.separator + "redj.cfg");
        try
        {
            ConfigReader cr = new ConfigReader(configFile);
            projectConfig = cr.read();
            cr.close();
            mainClass = (String)projectConfig.get("MAINCLASS");
        } catch (IOException e) { projectConfig = new Hashtable(); } //if the file doesn't exist, etc.
        if (mainClass == null || mainClass.equals(""))
        {
            mainClass = inputDialog("Main Class").trim();
            if ("".equals(mainClass)) mainClass = "Main";
            else
            {
                try
                {
                    projectConfig.put("MAINCLASS", mainClass);
                    ConfigWriter cw = new ConfigWriter(configFile);
                    cw.write(projectConfig);
                    cw.close();
                    listFrame.refresh();
                } catch (IOException e2) { e2.printStackTrace(); }
            }
        }
    }
    private void openLast()
    {
        open((String)config.get("LASTPROJECT"));
    }
    private void newProject()
    {
        String dirName = saveDialog();
        if ("".equals(dirName)) return;
        File dir = new File(dirName);
        dir.mkdirs();
        open(dirName);
    }
    private void compile()
    {
        String[] command = {JDKPATH + 
            "javac", mainClass + ".java"};
        clearTerminal();
        ProcessRunner.exec(command, null, new File(currentProject));
        listFrame.refresh();
    }
    private void run()
    {
        String[] command = {JDKPATH + "java", "-cp", currentProject, mainClass};
        String mode = (String)projectConfig.get("MODE");
        if (mode != null && mode.equalsIgnoreCase("noconsole"))
        {
            ProcessRunner.exec(command, null, new File(currentProject));
        } else {
            clearTerminal();
            ProcessRunner.exec(command, null,
                new File(currentProject)); //TODO
        }
        listFrame.refresh();
    }
    

    
    //actionPerformed
    public void actionPerformed(ActionEvent e)
    {
        //CONTINUE
        Object o = e.getSource();
        Container p = getContentPane();
        CardLayout l = (CardLayout)p.getLayout();
        if (o == open) open();
        if (o == openLast) openLast();
        if (o == newProject) newProject();
        if (o == compile) compile();
        if (o == run) run();
        if (o == compileRun)
        {
            compile();
            run();
        }
        repaint();
    }
    public void windowClosing(WindowEvent e)
    {
        try
        {
            ConfigWriter cw = new ConfigWriter(REDJPATH + "redj-global.cfg");
            cw.write(config);
            cw.close();
        } catch (IOException ex) {
            System.err.println("couldn't write config file");
            ex.printStackTrace();
        }
    }    
    //helper methods - do basically what the names suggest
        public static void clearTerminal()
        {
            //for (int i = 0; i < 26; i++)
              //  System.out.println();
        }
        public String openDialog()
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile().getAbsolutePath();
            } else {
                return "";
            }
        }
        public String saveDialog()
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showSaveDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile().getAbsolutePath();
            } else {
                return "";
            }
        }
        public static String inputDialog(String title)
        {
            //TODO: add "OK" button
            JDialog dlg = new JDialog((JFrame)null,title,true);
            JTextField fld = new JTextField();
            dlg.add(fld);
            dlg.setBounds(450,500,200,60);
            dlg.setVisible(true);
            return fld.getText();
        }
    
    //WindowListener methods
        public void windowActivated(WindowEvent e)
        {
        }
        public void windowClosed(WindowEvent e)
        {
        }
        public void windowDeactivated(WindowEvent e)
        {
        }
        public void windowDeiconified(WindowEvent e)
        {
        }
        public void windowIconified(WindowEvent e)
        {
        }
        public void windowOpened(WindowEvent e)
        {
        }
    public static void main(String[] args)
    {
        JFrame a;
        if (args.length == 0)
        {
            a = new RedJ();
        } else if (args.length == 1) {
            a = new RedJ(args[0]);
        } else {
            System.err.println("Wrong number of arguments. Perhaps you missed some quotation marks?");
            a = new RedJ();
        }
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
    }
}