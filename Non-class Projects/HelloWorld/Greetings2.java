/**
 * Another pointless thing that gets junk from the user and
 * splatters it all over the screen.
 * 
 * @author Michael Limiero 
 * @version 1x10^-9
 */
import java.util.Scanner;
public class Greetings2
{
    public static void main(String[] args)
    {
        Scanner kboard = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstName = kboard.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = kboard.nextLine();
        for (int i = 0; i>=0; i++);
        System.out.println("Hello, " + firstName + " " + lastName + ".");
        System.out.println("Welcome to Java!");
    }
}