import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
public class Console extends JTextArea implements KeyListener
{
    private int inputLength = 0;
    private boolean acceptingInput = true;
    
    public PipedInputStream in;
    protected PipedOutputStream inPipe;
    public PipedOutputStream out;
    protected PipedInputStream outPipe;
    public PipedOutputStream err;
    protected PipedInputStream errPipe;
    
    public Object outputLock = new Object();
    
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
        setLineWrap(true);
        addKeyListener(this);
        (new ConsoleOutputThread(this)).start();
        (new ConsoleErrorThread(this)).start();
    }
    public void clear()
    {
        setText("");
    }
    public synchronized void keyPressed(KeyEvent e)
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
            
            try
            {
            s = t.substring(l-inputLength,l);
            } catch (Exception ex) { append("Err"); return; }
            //I was getting weird errors here...
            
            PrintStream p = new PrintStream(inPipe);
            p.println(s); //for some reason it only works
                            // when I wrap it in PrintStream...
                            // Yay voodoo programming!
            
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
    static class ConsoleOutputThread extends Thread
    {
        //this thread checks our output stream and dumps it on the console.
        Console c;
        InputStream is;
        InputStreamReader r;
        public ConsoleOutputThread(Console c)
        {
            this.c = c;
            is = c.outPipe;
            r = new InputStreamReader(is);
        }
        public void run()
        {
            while (true) {
            int i;
            try
            {
                while ((i = r.read()) != -1)
                {
                    synchronized (c)
                    {
                        c.notifyAll(); //tell ConsoleFrame to become visible
                    }
                    c.append(Character.valueOf((char)i).toString());
                }
            } catch (IOException e) { 
                if (e.getMessage().indexOf("Pipe broken") != -1);
                else if (e.getMessage().indexOf("Write end dead") != -1);
                else e.printStackTrace(); 
            }
            }
        }
    }
    static class ConsoleErrorThread extends ConsoleOutputThread
    {
        //ditto for the error stream
        public ConsoleErrorThread(Console c)
        {
            super(c);
            this.c = c;
            is = c.errPipe;
            r = new InputStreamReader(is);
        }
    }
}

