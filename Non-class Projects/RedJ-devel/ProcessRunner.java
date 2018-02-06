//this class could be useful for other projects...
import java.io.*;
public class ProcessRunner
{
    private static ProcessWriter w;
    private static int i=0;    
    public static Process exec(String[] command, String[] env, File dir) throws IOException
    {
            Process p = Runtime.getRuntime().exec(command, env, dir);
            (new ProcessReader(p)).start();
            (new ProcessErrorReader(p)).start();
            
            if (w == null) 
            {
                w = new ProcessWriter(p);
                w.setName("PWriter" + Integer.toString(i));
                i++;
                w.start();
            } else {
                w.setProcess(p); //w is persistent but we can change the process it points at
            }
            return p;
    }

    public static class ProcessReader extends Thread
    {   //read the process's output and do stuff with it
        // (right now it dumps it to the screen)
        public Process p; //temp
        public ProcessReader(Process p)
        {
            super("PReader");
            this.p = p;
        }        
        public void run()
        {
            InputStreamReader stdInput = new InputStreamReader(p.getInputStream());
            //misnomer: this is the process's stdOut, but from our point of view it is an input
            InputStreamReader stdError = new InputStreamReader(p.getErrorStream());
            OutputStreamWriter sysOut = new OutputStreamWriter(System.out);
            OutputStreamWriter sysErr = new OutputStreamWriter(System.err);
            
            try
            {
                char c;
                int i;
                boolean go = true; //continue was a reserved word
                while (go)
                {
                    i = stdInput.read();
                    if (i != -1) sysOut.write((char)i);
                    else go = false;
                    sysOut.flush();
                }

            } catch (IOException e) { System.out.println("Error'd!"); }
        }
    }
    public static class ProcessErrorReader extends Thread //TODO: this should extend ProcessReader
    {   //read the process's output and do stuff with it
        // (right now it dumps it to the screen)
        public Process p; //temp
        public ProcessErrorReader(Process p)
        {
            super("PEReader");
            this.p = p;
        }        
        public void run()
        {
            InputStreamReader stdInput = new InputStreamReader(p.getInputStream());
            //misnomer: this is the process's stdOut, but from our point of view it is an input
            InputStreamReader stdError = new InputStreamReader(p.getErrorStream());
            OutputStreamWriter sysOut = new OutputStreamWriter(System.out);
            OutputStreamWriter sysErr = new OutputStreamWriter(System.err);
            
            try
            {
                char c;
                int i;
                boolean go = true; //continue was a reserved word
                while (go)
                {
                    i = stdError.read();
                    if (i != -1) sysErr.write((char)i);
                    else go = false;
                    sysErr.flush();
                }

            } catch (IOException e) { System.out.println("Error'd!"); }
        }
    }
    public static class ProcessWriter extends Thread
    {   //check for console input and send it to the process
        Process p;
        Process oldP;
        public boolean stop = false;
        public ProcessWriter(Process p)
        {
            super("PWriter");
            this.p = p;
            this.oldP = p;
        }        
        public void setProcess(Process p)
        {
            this.oldP = this.p;
            this.p = p;
        }
        public void run()
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            IOException ex;
            int i;
            while (true)
            {
                if (p != oldP)
                {
                    out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                    oldP = p;
                }
                try
                {
                    i = System.in.available();  
                    if (i > 0)
                    {
                        out.write(System.in.read());
                        out.flush(); //DANG! That is ANNOYING!
                        //I've spent about two weeks trying to get this to work
                        // and I was only missing that single line. Argh!
                        // MORAL: When in doubt, flush the buffers!
                    }
                } catch (IOException e) { 
                    ex = e;
                    if (e.getMessage().indexOf("pipe") != -1);
                    else 
                        e.printStackTrace(); 
                }
            }
        }
    }
}