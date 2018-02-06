import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
public class Console extends JTextArea implements KeyListener
{
	private int inputLength = 0;
    private boolean acceptingInput = true; //TEMP
    
    public PipedInputStream in;
    private PipedOutputStream inPipe;
    public PipedOutputStream out;
    private PipedInputStream outPipe;
    public PipedOutputStream err;
    private PipedInputStream errPipe;
	public Console()
	{
		this(26,80);
	}
	public Console(int rows, int columns)
	{
		super(rows, columns);
        try
        {
        in = new PipedInputStream();
        inPipe = new PipedOutputStream(in);
        out = new PipedOutputStream();
        outPipe = new PipedInputStream(out);
        err = new PipedOutputStream();
        errPipe = new PipedInputStream(err);
        } catch (Exception e) { e.printStackTrace(); }
		Font x = new Font("Monospaced", Font.PLAIN, 12);
		setFont(x);
		addKeyListener(this);
	}
	public void clear()
	{
		setText("");
	}
	public void keyPressed(KeyEvent e)
	{
	    String t, s;
		if (!acceptingInput || getSelectedText() != null || 
            getCaretPosition() < getText().length() - inputLength)
        {
            setEditable(false);
            return;
        }
        setEditable(true);
        char c = e.getKeyChar();
        int i = e.getKeyCode();
        if (c == '\n')
        {
            t = getText();
            int l = t.length();
            s = t.substring(l-inputLength+1,l);
            try
            {
            inPipe.write(s.getBytes());
            inPipe.flush();
            in.notify();
            } catch (Exception ex) { ex.printStackTrace(); }
            
            inputLength = 0;
        } else if (i == 8 || i == 127) { //backspace
            if (inputLength == 0 || (i == 8 && getCaretPosition() < getText().length() - inputLength + 1))
            {
                setEditable(false);
                return;
            }
            inputLength--;
        } else if (e.isActionKey()) {
        } else inputLength++;
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e)
	{
	}
    public void append(String str)
    {
        super.append(str);
        setCaretPosition(getText().length());
        inputLength = 0;
    }
}

