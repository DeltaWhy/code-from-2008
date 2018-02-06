
/**
 * Write a description of class BandTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class BandTest
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        //should use an array but it's probably not worth the trouble for 3
        System.out.print("Enter the name of the band booster: ");
        BandBooster bb1 = new BandBooster(kb.next());
        System.out.print("Enter the name of the band booster: ");
        BandBooster bb2 = new BandBooster(kb.next());
        System.out.print("Enter the name of the band booster: ");
        BandBooster bb3 = new BandBooster(kb.next());
        
        System.out.print("How many weeks in the current campaign? ");
        int weeks = kb.nextInt();
        
        for (int count = 1; count <= weeks; count++)
        {
            System.out.print("How many boxes did " + bb1.getName() +
                " sell this week? ");
            bb1.updateSales(kb.nextInt());
            System.out.print("How many boxes did " + bb2.getName() +
                " sell this week? ");
            bb2.updateSales(kb.nextInt());
            System.out.print("How many boxes did " + bb3.getName() +
                " sell this week? ");
            bb3.updateSales(kb.nextInt());
        }
        
        System.out.println("\nTotal sales:");
        System.out.println(bb1);
        System.out.println(bb2);
        System.out.println(bb3);
    }
}