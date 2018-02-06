import java.io.*;
public class ProcessRunner
{
    public static void exec(String[] command, String[] env, File dir)
    {
        try
        {
            //ConsoleFrame c = new ConsoleFrame();
            //System.setIn(c.console.in);
            Process p = Runtime.getRuntime().exec(command, env, dir);
            (new ProcessReader(p)).start();
            (new ProcessErrorReader(p)).start();
            //(new Thread(new ProcessWriter(p))).start();
        } catch (Exception e) {
            System.err.println("exception in exec()");
            e.printStackTrace();
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
    public static class ProcessWriter implements Runnable
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
                } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
}