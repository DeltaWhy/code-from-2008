import java.util.Scanner;
import java.io.*;
public class InputTest
{
    public static synchronized void main(String[] args)
    {
        /*ConsoleFrame f = new ConsoleFrame();
        try
        {
            System.setIn(f.console.in);
            System.setOut(new PrintStream(f.console.out));
            System.setErr(new PrintStream(f.console.err));
        } catch (Exception e) { e.printStackTrace(); }*/
        System.out.println(System.in.getClass().getName());
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter some text: ");
        String s = kb.nextLine();
        System.out.println("You entered: \"" + s + "\"");
    }
}
