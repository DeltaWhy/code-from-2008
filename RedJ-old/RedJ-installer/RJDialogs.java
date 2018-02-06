import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class RJDialogs
{
    public static String openDialog(JFrame f)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(f);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }
    public static String saveDialog(JFrame f)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showSaveDialog(f);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }
    //replaced by JOptionPane.showInputDialog
    /*public static String inputDialog(String title)
    {
        //TODO: add "OK" button
        InputDialog dlg = new InputDialog((JFrame)null,title,true);
        return dlg.getText();
    }
    static class InputDialog extends JDialog implements ActionListener
    {
        JTextField fld;
        public InputDialog(JFrame f, String title, boolean modal)
        {
            super(f,title,modal);
            fld = new JTextField();
            fld.addActionListener(this);
            add(fld);
            setBounds(450,500,200,60);
            setVisible(true);
        }
        public String getText()
        {
            return fld.getText();
        }
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }*/
}