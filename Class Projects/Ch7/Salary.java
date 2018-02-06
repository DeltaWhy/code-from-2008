// ***************************************************************
//   Salary.java
//
//   Computes the amount of a raise and the new
//   salary for an employee.  The current salary
//   and a performance rating (a String: "Excellent",
//   "Good" or "Poor") are input.
// ***************************************************************
// Michael Limiero

import java.util.Scanner;
import java.text.NumberFormat;

public class Salary
{
   public static void main (String[] args)
   {
       double currentSalary;
       double raise;
       double newSalary;
       String rating;         // performance rating

       Scanner scan = new Scanner(System.in);

       System.out.print ("Enter the current monthly salary: ");
       currentSalary = scan.nextDouble();
       scan.nextLine(); // consume remaining space after the double is entered
       System.out.print ("Enter the performance rating (Excellent, Good, or Poor): ");
       rating = scan.nextLine();

       // Compute the raise...
       if (rating.equals("Excellent"))
           raise = 0.06 * currentSalary;
       else if (rating.equals("Good"))
           raise = 0.04 * currentSalary;
       else
           raise = 0.015 * currentSalary;
       //6 lines... My work here is done. Where's my paycheck?
       
       newSalary = currentSalary + raise;

       // Print the results
       NumberFormat money = NumberFormat.getCurrencyInstance();
       System.out.println();
       System.out.println("Current Monthly Salary:       " + money.format(currentSalary));
       System.out.println("Amount of your raise: " + money.format(raise));
       System.out.println("Your new Monthly salary:      " + money.format(newSalary));
       System.out.println();
    }
}