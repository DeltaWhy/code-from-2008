
/**
 * RedJ's file list dialog
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.awt.event.*;
public class FileList extends JFrame implements ActionListener
{
    private String dirName;
    private File[] files;
    private String[] fileNames = {""};
    private String[] filePaths = {""};
    private JScrollPane scrollPane;
    private ActionJList list;
    private ImageIcon icon;
    
    private JButton refresh;
    private JButton newFile;
    private JButton edit;
    private JButton delete;
    private JButton bluej;
    private JButton print;
    private JButton compile;
    private JButton run;
    private JPanel buttonPanel;
    
    public FileList(String dirName)
    {
        this.dirName = dirName;
        File dir = new File(dirName);
        list = new ActionJList(fileNames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refresh = new JButton("Refresh");
        newFile = new JButton("New");
        edit = new JButton("Edit");
        delete = new JButton("Delete");
        bluej = new JButton("BlueJ");
        print = new JButton("Print");
        compile = new JButton("Compile");
        run = new JButton("Run");
        
        list.addActionListener(this);
        refresh.addActionListener(this);
        newFile.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
        bluej.addActionListener(this);
        print.addActionListener(this);
        compile.addActionListener(this);
        run.addActionListener(this);
        
        scrollPane = new JScrollPane(list);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,4));
        buttonPanel.add(refresh);
        buttonPanel.add(newFile);
        buttonPanel.add(edit);
        buttonPanel.add(delete);
        if (RedJ.BLUEJPATH != null && !"".equals(RedJ.BLUEJPATH)) buttonPanel.add(bluej);
        //if (System.getProperty("os.name").indexOf("Windows") != -1) buttonPanel.add(print);
        //I need another button to make this even...
        buttonPanel.add(compile);
        buttonPanel.add(run);
        
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(scrollPane,BorderLayout.CENTER);
        c.add(buttonPanel,BorderLayout.SOUTH);
        
        URL imgURL = this.getClass().getResource("redj-icon.gif");
        if (imgURL != null) icon = new ImageIcon(imgURL);
        if (icon != null) setIconImage(icon.getImage());
        setBounds(500,300,400,600);
        setTitle("RedJ - " + dir.getName() + " - Files");
        refresh();
        setVisible(true);
    }
    private void click()
    {
        //do the right thing when a file is double-clicked
        if (list.getSelectedIndex() < 0) return;
        String fileName = fileNames[list.getSelectedIndex()];
        if (fileName.toLowerCase().indexOf(".java") != -1) edit();
        if (fileName.toLowerCase().indexOf(".cfg") != -1) edit();
        if (fileName.toLowerCase().indexOf(".txt") != -1) edit();
        if (fileName.toLowerCase().indexOf("bluej") != -1) bluej();
        if (fileName.toLowerCase().indexOf(".class") != -1) run();
        if (fileName.toLowerCase().indexOf(".htm") != -1) browse();
        //technically we should only match the endings, but it's not a big deal.
    }
    public void refresh()
    {
        File dir = new File(dirName);
        if (dir.isDirectory())
        {
            files = dir.listFiles();
            fileNames = new String[files.length];
            filePaths = new String[files.length];
            for (int i = 0; i < files.length; i++)
            {
                fileNames[i] = files[i].getName();
                filePaths[i] = files[i].getPath();
            }
        } else {
            System.out.println("dirName is not a directory");
        }
        java.util.Arrays.sort(fileNames); //surprisingly easy!
        list.setListData(fileNames);
        repaint();
    }
    private void newFile()
    {
        //String name = RJDialogs.inputDialog("New File");
        String name = JOptionPane.showInputDialog(this,"New File","New File",JOptionPane.QUESTION_MESSAGE);
        if (name == null || name.equals("")) return;
        if (name.indexOf('.') < 0) name += ".java";
        try
        {
            FileOutputStream fos = 
                new FileOutputStream(dirName + File.separator + name);
            fos.close();
        } catch (IOException e) {}
        refresh();
    }
    private void edit()
    {
        if (list.getSelectedIndex() < 0) return;
        String fileName = fileNames[list.getSelectedIndex()];
        String[] command = {RedJ.JDKPATH + "java",
        "-jar", RedJ.JEDITPATH + "jedit.jar", 
        "-settings=" + RedJ.JEDITPATH + ".jedit",
        dirName + File.separator + fileName};
        try
        {
        ProcessRunner.exec(command, null, null);
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void delete()
    {
        if (list.getSelectedIndex() < 0) return;
        String fileName = fileNames[list.getSelectedIndex()];
        File f = new File(dirName + File.separator + fileName);
        f.delete();
        refresh();
    }
    private void bluej()
    {
        if ("".equals(RedJ.BLUEJPATH)) return;
        String[] command = {RedJ.BLUEJPATH, dirName};
        try
        {
        ProcessRunner.exec(command, null, null);
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void print() //TODO: no longer necessary...
    {
        if (System.getProperty("os.name").indexOf("Windows") == -1) return; //only necessary on Winsuck
        if (list.getSelectedIndex() < 0) return;
        String filePath = filePaths[list.getSelectedIndex()];
        String[] command = {RedJ.REDJPATH + "print", filePath};
        try
        {
        ProcessRunner.exec(command, null, new File(dirName));
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void compile()
    {
        if (list.getSelectedIndex() < 0) return;
        String fileName = fileNames[list.getSelectedIndex()];
        if (fileName.indexOf(".java") == -1) return;
        RedJ.instance.compile(fileName);
        refresh();
    }
    private void run()
    {
        if (list.getSelectedIndex() < 0) return;
        String fileName = fileNames[list.getSelectedIndex()];
        if (fileName.indexOf(".class") == -1) return;
        RedJ.instance.run(fileName.substring(0,fileName.length()-6));
        refresh();
    }
    private void browse()
    {
        if (list.getSelectedIndex() < 0) return;
        String fileName = fileNames[list.getSelectedIndex()];
        if (fileName.indexOf(".htm") == -1) return;
        RedJ.instance.browse(fileName);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();
        if (o == refresh) refresh();
        else if (o == newFile) newFile();
        else if (o == edit) edit();
        else if (o == delete) delete();
        else if (o == bluej) bluej();
        else if (o == print) print();
        else if (o == compile) compile();
        else if (o == run) run();
        else click();
    }
}