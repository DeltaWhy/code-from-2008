import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
public class Console extends JTextArea implements KeyListener
{
    public int inputStart = 0;
    private boolean acceptingInput = true;
    
    public PipedInputStream in;
    protected PipedOutputStream inPipe;
    public PipedOutputStream out;
    protected PipedInputStream outPipe;
    public PipedOutputStream err;
    protected PipedInputStream errPipe;
    protected PrintStream p;
    
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
            p = new PrintStream(inPipe);
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
        inputStart = 0;
    }
    public synchronized void keyPressed(KeyEvent e) //synchronized == magic!
    {
        String t, s;
        if (!acceptingInput || getSelectedText() != null || 
                getCaretPosition() < inputStart)
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
            
            s = t.substring(inputStart,l);
            
            inputStart = getText().length(); 

            try
            {
                inPipe.write((s + "\n").getBytes()); //don't forget that newline!
            } catch (Exception ex) {
                String s2 = ex.getMessage();
                System.out.println(s2);
            }
            
        }
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
    }
    public void append(String str)
    {
        if (str == null || str.equals("")) return;
        super.append(str.replace("\r\n","\n")); //avoid potential problems
        setCaretPosition(getText().length());
        inputStart = getText().length();
    }
    static class ConsoleOutputThread extends Thread
    {
        //this thread checks our output stream and dumps it on the console.
        Console c;
        InputStream is;
        InputStreamReader r;
        public ConsoleOutputThread(Console c)
        {
            super("ConsoleOutputThread");
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
                    synchronized (c) //I still don't know what synchronized does
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
            setName("ConsoleErrorThread");
        }
    }
}

