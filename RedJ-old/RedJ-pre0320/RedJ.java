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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class RedJ extends JFrame implements ActionListener, WindowListener, MouseListener
{
    public static RedJ instance; //there should only be one instance
    
    private ImageIcon icon;
    private JPanel panel1;
    private JPanel panel2;
    private JButton openLast;
    private JButton open;
    private JButton newProject;
    private JButton compile;
    private JButton run;
    private JButton compileRun;
    private JProgressBar pBar;
    
    private String currentProject="";
    private String mainClass="";
    
    private FileList listFrame;
    private ConsoleFrame consoleFrame;
    
    private Hashtable config;
    private Hashtable projectConfig;
    private Process projectProcess;
    
    public static String BLUEJPATH;
    public static String JDKPATH;
    public static String REDJPATH;
    public static String JEDITPATH;
    
    
    public RedJ(String redJPath)
    {
        REDJPATH = redJPath;
        if (REDJPATH.charAt(REDJPATH.length()-1) != File.separatorChar)
            REDJPATH += File.separator;
        
        consoleFrame = new ConsoleFrame("RedJ console");
        try
        {
            System.setIn(consoleFrame.console.in);
            System.setOut(new PrintStream(consoleFrame.console.out));
            System.setErr(new PrintStream(consoleFrame.console.err));
        } catch (Exception e) {
            System.err.println("Could not open console.");
            e.printStackTrace();
        }
                
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
        
        JEDITPATH = (String)config.get("JEDITPATH");
        if (JEDITPATH != null && JEDITPATH.charAt(JEDITPATH.length()-1) != File.separatorChar) 
            JEDITPATH += File.separator;
        if (JEDITPATH == null || JEDITPATH.equals(""))
        {
            JEDITPATH = REDJPATH + ".." + File.separator + "jEdit" + File.separator;
            config.put("JEDITPATH",JEDITPATH);
        }
        
        URL imgURL = this.getClass().getResource("redj-icon.gif");
        if (imgURL != null) icon = new ImageIcon(imgURL);
        if (icon != null) 
        {
            setIconImage(icon.getImage());
            consoleFrame.setIconImage(icon.getImage());
        }
        
        setBounds(200,300,300,300);
        setTitle("RedJ");
        
        addWindowListener(this);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        panel2.setLayout(new GridLayout(4,1));
        
        openLast = new JButton("Open Last Project");
        open = new JButton("Open Other Project");
        newProject = new JButton("New Project");
        compile = new JButton("Compile Project");
        run = new JButton("Run Project");
        compileRun = new JButton("Compile & Run");
        pBar = new JProgressBar();
        
        openLast.addActionListener(this);
        open.addActionListener(this);
        newProject.addActionListener(this);
        compile.addActionListener(this);
        run.addActionListener(this);
        compileRun.addActionListener(this);
        pBar.addMouseListener(this);
        
        panel1.add(openLast);
        panel1.add(open);
        panel1.add(newProject);
        panel2.add(compile);
        panel2.add(run);
        panel2.add(compileRun);
        panel2.add(pBar);
        
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
        listFrame.addWindowListener(this);
        consoleFrame.setTitle("RedJ console - " + dir.getName());
        
        Container p = getContentPane();
        CardLayout l = (CardLayout)p.getLayout();
        l.last(p);
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
    public void compile(String fileName)
    {
        String[] command = {JDKPATH + 
            "javac", fileName};
        clearTerminal();
        setProcess(ProcessRunner.exec(command, null, new File(currentProject)));
        pBar.setString("Compiling");
        listFrame.refresh();
    }
    public void compile()
    {
        String build = (String)projectConfig.get("BUILD");
        int buildNum = 0;
        if (build != null) buildNum = Integer.parseInt(build);
        compile(mainClass + ".java");
        buildNum++;
        projectConfig.put("BUILD",Integer.toString(buildNum));
        projectConfig.put("BUILDDATE", new Date().toString());
        File configFile = new File(currentProject + File.separator + "redj.cfg");
        try
        {
            ConfigWriter cw = new ConfigWriter(configFile);
            cw.write(projectConfig);
            cw.close();
            listFrame.refresh();
        } catch (IOException e2) { e2.printStackTrace(); }
    }
    public void run(String className)
    {
        String[] command = {JDKPATH + "java", "-cp", currentProject, className};
        String mode = (String)projectConfig.get("MODE");
        clearTerminal();
        setProcess(ProcessRunner.exec(command, null, new File(currentProject)));
        listFrame.refresh();
    }
    public void run()
    {
        run(mainClass);
    }
    private void setProcess(Process p)
    {
        projectProcess = p;
        pBar.setIndeterminate(true);
        pBar.setString("Running Project");
        pBar.setStringPainted(true);
        new Thread(new Runnable(){
            public void run() {
                try {
                projectProcess.waitFor();
                pBar.setIndeterminate(false);
                pBar.setStringPainted(false);
                listFrame.refresh();
                } catch (Exception e) {}
            }
        }).start();
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
    
    //from WindowListener - for this and listFrame
    public void windowClosing(WindowEvent e)
    {
        if (e.getSource() == this)
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
        } else { //listFrame
            Container p = getContentPane();
            CardLayout l = (CardLayout)p.getLayout();
            l.first(p);
        }
    }    
    //helper methods - do basically what the names suggest
        public static void clearTerminal()
        {
            RedJ.instance.consoleFrame.console.clear();
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
        public static String getDateTime() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            return dateFormat.format(date);
        }
    
    //unused WindowListener methods
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
            if (e.getSource() == listFrame)
                this.setState(Frame.NORMAL);
        }
        public void windowIconified(WindowEvent e)
        {
            if (e.getSource() == this)
                listFrame.setState(Frame.ICONIFIED);
        }
        public void windowOpened(WindowEvent e)
        {
        }
        
    //MouseListener methods for pBar
        public void mouseClicked(MouseEvent e) {
            //on pBar
            if (e.getClickCount() == 2)
                projectProcess.destroy();
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        
    public static void main(String[] args)
    {
        RedJ a;
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
        instance = a;
    }
}