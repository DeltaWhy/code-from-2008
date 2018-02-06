//Michael Limiero
import java.io.*;
public class Helper
{
    public static String exec(String command)
    {
        String s;
        String out = "";
        try
        {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            //read the output from the command
            while ((s = stdInput.readLine()) != null)
            {
                out += s;
            }
            
            //read any errors from the attempted command
            while ((s = stdError.readLine()) != null)
            {
                System.err.println(s); //maybe change this
            }
        } catch (IOException e) {
            System.err.println("exception in method Helper.exec()");
            e.printStackTrace();
        } finally {
            return out;
        }
    }
}
