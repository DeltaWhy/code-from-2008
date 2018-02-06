// ****************************************************************
// Sales.java
//
// Reads in and stores sales for each of 5 salespeople.  Displays
// sales entered by salesperson id and total sales for all salespeople.
//
// ****************************************************************
import java.util.Scanner;

public class Sales
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of salespeople: ");
        final int SALESPEOPLE = scan.nextInt();
        int[] sales = new int[SALESPEOPLE];
        int sum, min, max, compare, exceeded;
    
        for (int i=0; i<sales.length; i++)
        {
            System.out.print("Enter sales for salesperson " + (i+1) + ": ");
            sales[i] = scan.nextInt();
        }
    
        System.out.println("\nSalesperson   Sales");
        System.out.println("--------------------");
        sum = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
        for (int i=0; i<sales.length; i++)
        {
            System.out.println("     " + (i+1) + "         " + sales[i]);
            sum += sales[i];
            if (sales[i] < min) min = sales[i];
            if (sales[i] > max) max = sales[i];
        }
        System.out.println("\nTotal sales: " + sum);
        System.out.println("Average sales: " + ((double)sum)/sales.length);
        System.out.println("Minimum sales: " + min);
        System.out.println("Maximum sales: " + max);
        
        System.out.print("\nEnter a value to compare sales: ");
        compare = scan.nextInt();
        exceeded = 0;
        
        System.out.println("\nSalesperson   Sales");
        System.out.println("--------------------");
        for (int i=0; i<sales.length; i++)
            if (sales[i] >= compare)
            {
                System.out.println("     " + (i+1) + "         " + sales[i]);
                exceeded++;
            }
            
        System.out.println("\n" + exceeded + " salespeople exceeded " + compare);
    }
}