import java.util.Scanner;
public class InputTest
{
    public static void main(String[] args)
    {
        ConsoleFrame f = new ConsoleFrame();
        try
        {
            System.setIn(f.console.in);
        } catch (Exception e) { e.printStackTrace(); }
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter some text: ");
        String s = kb.nextLine();
        System.out.println(s);
    }
}
