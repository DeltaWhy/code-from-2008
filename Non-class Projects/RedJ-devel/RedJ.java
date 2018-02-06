//Michael Limiero
//
// RedJ - a minimalistic IDE
//TODO'S:
//  -system out/err conflict - how about an error dialog?
//  -dialogs should move to RJDialogs.java, then I can add OK buttons and such.
//  -potentially move some code into other files...
//  -JAR maker would be way cool
//  -maybe new project is borken... it seems fine but earlier it was being weird
//  -graphical configurator/installer
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
    private JButton jar;
    private JProgressBar pBar;
    
    private String currentProject="";
    private String mainClass="";
    
    private FileList listFrame;
    private ConsoleFrame consoleFrame;
    
    private Hashtable<String,String> config;
    private Hashtable<String,String> projectConfig;
    private Process projectProcess;
    
    public static String BLUEJPATH;
    public static String JDKPATH;
    public static String REDJPATH;
    public static String JEDITPATH;
    public static boolean DEVEL;
    
    //constructors and helpers
    public RedJ(String redJPath)
    {
        //Set REDJPATH
        REDJPATH = redJPath;
        if (REDJPATH.charAt(REDJPATH.length()-1) != File.separatorChar)
            REDJPATH += File.separator;
        
        createConsole();
        readConfig();
        setGlobalOptions();
        
        //set up window
        URL imgURL = this.getClass().getResource("redj-icon.gif");
        if (imgURL != null) icon = new ImageIcon(imgURL);
        if (icon != null) 
        {
            setIconImage(icon.getImage());
            consoleFrame.setIconImage(icon.getImage());
        }
        
        setSize(300,300);
        setTitle("RedJ");
        if (DEVEL) setTitle("RedJ (devel)");
        
        addWindowListener(this);
        
        //add controls
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        panel2.setLayout(new GridLayout(4,1));
        
        openLast = new JButton("Open Last Project");
        open = new JButton("Open Other Project");
        newProject = new JButton("New Project");
        compile = new JButton("Compile Project");
        run = new JButton("Run Project");
        jar = new JButton("Create JAR");
        pBar = new JProgressBar();
        
        openLast.addActionListener(this);
        open.addActionListener(this);
        newProject.addActionListener(this);
        compile.addActionListener(this);
        run.addActionListener(this);
        jar.addActionListener(this);
        pBar.addMouseListener(this);
        
        panel1.add(openLast);
        panel1.add(open);
        panel1.add(newProject);
        panel2.add(compile);
        panel2.add(run);
        panel2.add(jar);
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
        //use the current directory and hope it's where RedJ is
    }
    private void createConsole()
    {
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
    }
    private void readConfig()
    {
        try
        {
            ConfigReader cr = new ConfigReader(REDJPATH + "redj-global.cfg");
            config = cr.read();
            cr.close();
        } catch (IOException e) {
            config = new Hashtable<String,String>();
            setup();
        }
    }
    private void setup()
    {
        JOptionPane.showMessageDialog(this,"You need to set some paths. If the config window\ndoesn't show up, you need to run\nsetup.jar manually.","First Run",JOptionPane.INFORMATION_MESSAGE);
        String[] command = {System.getProperty("java.home") + File.separator + "bin" + File.separator + "java", "-cp", REDJPATH + "RedJ.jar", "ConfigFrame"};
        try
        {
            ProcessRunner.exec(command, null, new File(REDJPATH));
        } catch (IOException e) { e.printStackTrace(); }
        System.exit(0);
    }
    private void setGlobalOptions()
    {
        //move frames around
        String xs = config.get("CONSOLEX");
        String ys = config.get("CONSOLEY");
        if (xs == null || ys == null || xs.equals("") || ys.equals("")) {} else {
            int x = Integer.parseInt(xs);
            int y = Integer.parseInt(ys);
            Dimension d = consoleFrame.getSize();
            consoleFrame.setBounds(x,y,0,0);
            consoleFrame.setSize(d);
        }
        xs = config.get("MAINX");
        ys = config.get("MAINY");
        if (xs == null || ys == null || xs.equals("") || ys.equals("")) {} else {
            int x = Integer.parseInt(xs);
            int y = Integer.parseInt(ys);
            Dimension d = getSize();
            setBounds(x,y,0,0);
            setSize(d);
        }
        
        //set paths
        BLUEJPATH = (String)config.get("BLUEJPATH");
                
        JDKPATH = (String)config.get("JDKPATH");
        if (JDKPATH != null && JDKPATH.charAt(JDKPATH.length()-1) != File.separatorChar) 
            JDKPATH += File.separator;
        
        JEDITPATH = (String)config.get("JEDITPATH");
        if (JEDITPATH != null && JEDITPATH.charAt(JEDITPATH.length()-1) != File.separatorChar) 
            JEDITPATH += File.separator;
        if (JEDITPATH == null || JEDITPATH.equals(""))
        {
            JEDITPATH = REDJPATH + ".." + File.separator + "jEdit" + File.separator; //TODO - ".." is not safe at all.
            config.put("JEDITPATH",JEDITPATH);
        }
        
        //set devel
        DEVEL = Boolean.valueOf((String)config.get("DEVEL"));
    }
    
    //button handlers
    private void open()
    {
        String dirName = RJDialogs.openDialog(this);
        if ("".equals(dirName)) return;
        open(dirName);
    }
    private void open(String dirName) //TODO: maybe too long!
    {
        if (dirName == null || dirName.trim().equals(""))
        {
            open();
            return; //open() calls here so we can quit
        }
        currentProject = dirName;
        config.put("LASTPROJECT", currentProject);
        File dir = new File(dirName);
        setTitle("RedJ - " + dir.getName());
        if (DEVEL) setTitle("RedJ (devel) - " + dir.getName());
        listFrame = new FileList(dirName);
        
        String xs = config.get("LISTX");
        String ys = config.get("LISTY");
        if (xs == null || ys == null || xs.equals("") || ys.equals("")) {} else {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        Dimension d = listFrame.getSize();
        listFrame.setBounds(x,y,0,0);
        listFrame.setSize(d);
        }
        
        listFrame.addWindowListener(this);
        consoleFrame.setTitle("RedJ console - " + dir.getName());
        
        Container p = getContentPane();
        CardLayout l = (CardLayout)p.getLayout();
        l.last(p);
        readProjectConfig();
    }
    private void readProjectConfig()
    {
        File configFile = new File(currentProject + File.separator + "redj.cfg");
        try
        {
            ConfigReader cr = new ConfigReader(configFile);
            projectConfig = cr.read();
            cr.close();
            mainClass = (String)projectConfig.get("MAINCLASS");
        } catch (IOException e) { projectConfig = new Hashtable<String,String>(); } //if the file doesn't exist, etc.
        if (mainClass == null || mainClass.equals(""))
        {
            //mainClass = RJDialogs.inputDialog("Main Class").trim();
            mainClass = JOptionPane.showInputDialog(this,"Main Class","Main Class",
                    JOptionPane.QUESTION_MESSAGE);
            if ("".equals(mainClass) || mainClass == null) mainClass = "Main";
            else
            {
                try
                {
                    projectConfig.put("MAINCLASS", mainClass.trim());
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
        String dirName = RJDialogs.saveDialog(this);
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
        try
        {
            setProcess(ProcessRunner.exec(command, null, new File(currentProject)));
            pBar.setString("Compiling");
            listFrame.refresh();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void compile()
    {
        readProjectConfig(); //in case the main class has changed...
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
        try
        {
            setProcess(ProcessRunner.exec(command, null, new File(currentProject)));
            listFrame.refresh();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void run()
    {
        readProjectConfig(); //in case the main class has changed...
        run(mainClass);
    }
    public void jar()
    {
        readProjectConfig();
        try
        {
            File manifest = new File(currentProject + File.separator + "Manifest.mf");
            if (!manifest.exists())
            {
                PrintStream ps = new PrintStream(manifest);
                ps.println("Main-Class: " + mainClass);
                ps.flush();
                ps.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
        File dir = new File(currentProject);
        String dirName = dir.getName();
        String[] command = {JDKPATH + "jar", "cfm", dirName + ".jar", "Manifest.mf", "*.*"};
        try
        {
            File oldJar = new File(dirName + ".jar");
            oldJar.delete();
        } catch (Exception e) {} //do nothing since it isn't important
        clearTerminal();
        try
        {
            setProcess(ProcessRunner.exec(command, null, new File(currentProject)));
        } catch (IOException e) { e.printStackTrace(); }
    }
            
    
    //event handlers
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
        if (o == jar) jar();
        repaint();
    }
    //WindowListener methods for this and listFrame
        public void windowClosing(WindowEvent e) //TODO: potentially too long
        {
            if (e.getSource() == this)
            {
                config.put("MAINX",Integer.toString(this.getX()));
                config.put("MAINY",Integer.toString(this.getY()));
                if (listFrame != null)
                {
                    config.put("LISTX",Integer.toString(listFrame.getX()));
                    config.put("LISTY",Integer.toString(listFrame.getY()));
                }
                if (consoleFrame != null)
                {
                    config.put("CONSOLEX",Integer.toString(consoleFrame.getX()));
                    config.put("CONSOLEY",Integer.toString(consoleFrame.getY()));
                }
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

    //helper methods - do basically what the names suggest
        public static void clearTerminal()
        {
            RedJ.instance.consoleFrame.console.clear();
        }
        public static String getDateTime() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            return dateFormat.format(date);
        }
        public void browse(String fileName)
        {
            String browser = config.get("BROWSER");
            if (browser == null || browser.equals(""))
            {
                if (System.getProperty("os.name").indexOf("Windows") != -1)
                {
                    browser = "C:\\Program Files\\Internet Explorer\\iexplore.exe";
                    config.put("BROWSER", browser);
                } else return;
            }
            try
            {
                String[] command = {browser, currentProject + File.separator + fileName};
                ProcessRunner.exec(command, null, new File(currentProject));
            } catch (Exception e) { e.printStackTrace(); }
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
            },"PBar").start();
        }
        
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
            System.err.println("Ignoring your arguments...");
            a = new RedJ();
        }
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
        instance = a;
    }
}