
/**
 * GUI frontend for CipherSaber.
 * 
 * @author Michael Limiero
 * @version 1
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
public class Katana extends JFrame implements ActionListener
{
    JButton decrypt;
    JButton encrypt;
    JButton decryptM;
    JButton encryptM;
    JButton insert;
    JButton extract;
    JButton fToT;
    JButton tToF;
    
    JPasswordField key;
    JTextField n;
    
    ImageIcon icon;
    public Katana()
    {
        URL imgURL = this.getClass().getResource("Katana-Icon.jpg");
        if (imgURL != null) icon = new ImageIcon(imgURL);
        if (icon != null) setIconImage(icon.getImage());
        
        setBounds(300,300,300,300);
        setTitle("Katana");
        decrypt = new JButton("Decrypt a .cs2 or .cs1 file");
        encrypt = new JButton("Encrypt a file with CS2 or CS1");
        decryptM = new JButton("Decrypt an ASCII-armored message");
        encryptM = new JButton("Encrypt a text message");
        insert = new JButton("Hide a file inside another file");
        extract = new JButton("Extract a hidden file");
        fToT = new JButton("Convert a file to Base64 text");
        tToF = new JButton("Convert Base64 text to a file");
        key = new JPasswordField();
        n = new JTextField();
        n.setText("1");
        
        decrypt.addActionListener(this);
        encrypt.addActionListener(this);
        decryptM.addActionListener(this);
        encryptM.addActionListener(this);
        insert.addActionListener(this);
        extract.addActionListener(this);
        fToT.addActionListener(this);
        tToF.addActionListener(this);
        
        Container c = getContentPane();
        c.setLayout(new GridLayout(10,1));
        c.add(decrypt);
        c.add(encrypt);
        c.add(decryptM);
        c.add(encryptM);
        c.add(key);
        c.add(n);
        c.add(insert);
        c.add(extract);
        c.add(fToT);
        c.add(tToF);
        repaint();
    }
    private String inputDialog()
    {
        JDialog dialog = new JDialog(this,"Enter the message",true);
        dialog.setBounds(300,300,400,600);
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        dialog.add(area);
        dialog.setVisible(true);
        return area.getText();
    }
    private void outputDialog(String message)
    {
        JDialog dialog = new JDialog(this,"Message",true);
        dialog.setBounds(300,300,400,600);
        JTextArea area = new JTextArea(message);
        area.setLineWrap(true);
        area.selectAll();
        dialog.add(area);
        dialog.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if (src == decrypt || src == encrypt)
        {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                try {
                    if (src == decrypt) CipherSaber.decryptFile2(filename, new String(key.getPassword()), Integer.parseInt(n.getText()));
                    if (src == encrypt) CipherSaber.encryptFile2(filename, new String(key.getPassword()), Integer.parseInt(n.getText()));
                } catch (java.io.IOException i) {
                    System.err.println("IO Error! Unsuccessful.");
                }
            }
        } else if (src == decryptM || src == encryptM) {
            String message = inputDialog();
            if (src == decryptM) outputDialog(CipherSaber.decryptMessage2(message, new String(key.getPassword()), Integer.parseInt(n.getText())));
            if (src == encryptM) outputDialog(CipherSaber.encryptMessage2(message, new String(key.getPassword()), Integer.parseInt(n.getText())));
        } else if (src == insert || src == extract) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Host File");
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                try {
                    if (src == insert) 
                    {
                        chooser.setDialogTitle("Guest File");
                        returnVal = chooser.showOpenDialog(this);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            String filename2 = chooser.getSelectedFile().getAbsolutePath();
                            if (!filename.equals(filename2)) Stego.insertFile(filename, filename2);
                        }
                    }
                    if (src == extract) Stego.extractFile(filename);
                } catch (java.io.IOException i) {
                    System.err.println("IO Error! Unsuccessful.");
                }
            }
        } else if (src == fToT) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                try {
                    outputDialog(Base64.encodeFile(filename));
                } catch (java.io.IOException i) {
                    System.err.println("IO Error! Unsuccessful.");
                }
            }
        } else if (src == tToF) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                String message = inputDialog();
                try {
                    Base64.decodeFile(filename,message);
                } catch (java.io.IOException i) {
                    System.err.println("IO Error! Unsuccessful.");
                }
            }            
        }
    }
    public static void main(String[] args)
    {
        Katana a = new Katana();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
    }
}
