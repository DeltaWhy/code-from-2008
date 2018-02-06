import java.io.*;
public class ProcessRunner
{
    public static Process exec(String[] command, String[] env, File dir)
    {
        try
        {
            /*ConsoleFrame c = new ConsoleFrame();
            System.setIn(c.console.in);
            System.setOut(new PrintStream(c.console.out));
            System.setErr(new PrintStream(c.console.err));*/
            //no longer needed since RedJ() creates a single console
            
            Process p = Runtime.getRuntime().exec(command, env, dir);
            (new ProcessReader(p)).start();
            (new ProcessErrorReader(p)).start();
            (new ProcessWriter(p)).start();
            return p;
        } catch (Exception e) {
            System.err.println("exception in exec()");
            e.printStackTrace();
            return null;
        }
    }

    public static class ProcessReader extends Thread
    {   //read the process's output and do stuff with it
        // (right now it dumps it to the screen)
        public Process p; //temp
        public ProcessReader(Process p)
        {
            super();
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
    public static class ProcessErrorReader extends Thread
    {   //read the process's output and do stuff with it
        // (right now it dumps it to the screen)
        public Process p; //temp
        public ProcessErrorReader(Process p)
        {
            super();
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
        public ProcessWriter(Process p)
        {
            this.p = p;
        }        
        public void run()
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            while (true)
            {
                try
                {
                    out.write(System.in.read());
                    out.flush(); //DANG! That is ANNOYING!
                    //I've spent about two weeks trying to get this to work
                    // and I was only missing that single line. Argh!
                    // MORAL: When in doubt, flush the buffers!
                } catch (IOException e) { 
                    if (e.getMessage().indexOf("pipe") != -1);
                    else e.printStackTrace(); 
                }
            }
        }
    }
}