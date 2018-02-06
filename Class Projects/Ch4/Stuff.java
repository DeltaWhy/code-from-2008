
/**
 * That stuff from the textbook. (Because pseudocode is a huge waste of time.)
 * Specifically Ex. 7 & 8
 * 
 * @author Michael
 * @version 10-20
 */
import java.util.Scanner;
public class Stuff
{
    public static void eightA(int n)
    {
        if (n < 1) return;
        for (int i=1; i <= n; i++)
        {
            printStars(i);
        }
    }
    public static void eightB(int n)
    {
        if (n > 1) eightB(n-1);
        printStars(n);
    }
    public static void eightC(int n)
    {
        printStars(n);
        if (n > 1) eightC(n-1);
    }
        
    public static void printStars(int n)
    {
        for(int i = 1; i <= n; i++)
        {
            System.out.print("*");
        }
        System.out.print("\n");
    }
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Input? ");
        int n = kb.nextInt();
        System.out.println("A: ");
        eightA(n);
        System.out.println("B: ");
        eightB(n);
        System.out.println("C: ");
        eightC(n);
    }
}
