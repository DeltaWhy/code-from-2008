import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FileField extends JPanel implements ActionListener, ComponentListener
{
    JTextField path;
    JButton browse;
    public FileField()
    {
        this("");
    }
    public FileField(String text)
    {
        path = new JTextField(text);
        browse = new JButton("Browse");
        browse.addActionListener(this);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(path);
        add(browse);
        
        addComponentListener(this);
    }
    private void fixSizes()
    {
        if (path == null) return;
        Dimension d = path.getMinimumSize();
        d.width = getWidth() - browse.getWidth() - 20;
        path.setMinimumSize(d);
        path.setPreferredSize(d);
        path.setMaximumSize(d);
        path.setSize(d);
        int y = path.getY() - (browse.getHeight()-path.getHeight()) / 2;
        //center path and browse
        browse.setLocation(path.getWidth() + 10, y);
    }
    public String getText()
    {
        return path.getText();
    }
    public void setText(String s)
    {
        path.setText(s);
    }
    public void actionPerformed(ActionEvent e)
    {
        String s = RJDialogs.openDialog((JFrame)null);
        if (s != null && !s.equals("")) setText(s);
    }
    public void componentResized(ComponentEvent e)
    {
        fixSizes();
    }
    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
}