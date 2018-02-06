// ****************************************************************
// Dates.java
//
// Determine whether a 2nd-millenium date entered by the user
// is valid
//          
// ****************************************************************
// Michael Limiero
//   so we're purposely putting in a Y2K bug??????
import java.util.Scanner;

public class Dates
{
    public static void main(String[] args)
    {
        int month, day, year;   //date read in from user
        int daysInMonth;        //number of days in month read in 
        boolean monthValid = false, yearValid = false, dayValid = false;
        //true if input from user is valid
        boolean leapYear = false;  //true if user's year is a leap year
        Scanner kb = new Scanner(System.in);  // create a scanner input object
        int[] monthDays = {0,31,28,31,30,31,30,31,31,30,31,30,31};

        //Get integer month, day, and year from user
        System.out.print("Input month, day, and year as integers separated by spaces: ");
        month = kb.nextInt();
        day = kb.nextInt();
        year = kb.nextInt();
        
        //Check to see if month is valid
        if (month >= 1 && month <= 12) monthValid = true;
        
        //Check to see if year is valid
        if (year >= 1000 && year <= 1999) yearValid = true;
        //Program to the requirements, even though they're stupid...
        
        //Determine whether it's a leap year
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) leapYear = true;
        //Bad idea if we're being paid by lines of code - could be 6 lines
        
        //Determine number of days in month
        if (monthValid) 
        {
            daysInMonth = monthDays[month];
            //Even worse idea if we're paid by the line - could be nearly 50 lines
            if (leapYear && month == 2) daysInMonth++;
            //User number of days in month to check to see if day is valid
            if (day >= 1 && day <= daysInMonth) dayValid = true;
        }

        //Determine whether date is valid and print appropriate message
        if (monthValid && yearValid && dayValid)
        {
            System.out.print("Date is valid; it is ");
            if (!leapYear) System.out.print("not ");
            System.out.println("a leap year.");
        } else {
            System.out.println("Date is invalid.");
        }

    }

}