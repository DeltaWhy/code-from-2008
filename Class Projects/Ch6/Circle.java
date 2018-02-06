//**********************************************************
//  Circle.java
//
//  Print the area of a circle with two different radii
//**********************************************************
// Michael Limiero                                 ^^
//                                                 ||
//                                             made-up word

//For extra fun, type in 2147483647 as the input!!
import java.util.Scanner;
public class Circle
{
    public static void main(String[] args)
    {
     //final double PI = 3.14159;
     final double PI = Math.PI; //in case California defines pi as 3
                                // it's not hard coded so I don't have to fix it
                                // OK not really, but it's more accurate.
     
     //int radius = 10; //speaking of hard coding...
     //After step 3:
     int radius = -1;
     System.out.print("Enter an integer for the radius: ");
     Scanner kb = new Scanner(System.in);
     boolean success = false;
     while (!success)
     {
        try
        {
             radius = kb.nextInt();
             success = true;
        } catch (Exception e) {
             int message = (int)(4*Math.random());
             if (message == 0)
                System.out.println("No, you idiot, an integer!! INTEGER!!!!!");
             else if (message == 1)
                System.out.println("Try again, dimwit.");
             else if (message == 2)
                System.out.println("What part of integer do you not get??");
             else if (message == 3)
                System.out.println("What is this nonsense? I said integer!");
             String trash = kb.next();
             //probably not user-friendly... Oh well.
        }
     }
     
     System.out.println(); //blank lines, more readable
     double area1 = PI * radius * radius;
     double circumference1 = PI * radius * 2;

     System.out.println("The area of a circle with radius " + radius +
                        " is " + area1);
     System.out.println("The circumference of a circle with radius " + radius +
                        " is " + circumference1);

     System.out.println("\nNow double the radius\n"); //so the user doesn't
                                                  // wonder where the other
                                                  // number came from
                                                  
     //radius = 20; //again with the magic numbers - bad form
     radius *= 2; //oh good, I'm supposed to fix it.
     
     double area2 = PI * radius * radius;
     double circumference2 = PI * radius * 2;

     System.out.println("The area of a circle with radius " + radius +
                        " is " + area2);
     System.out.println("The circumference of a circle with radius " + radius +
                        " is " + circumference2);

     System.out.println();
     //and what's up with this indentation?? my tab key doesn't work here!
     double areaDelta = area2 / area1; //mathematical tradition...
     double circumferenceDelta = circumference2 / circumference1;
        //double variableNameWhichIsVeryLong;
     System.out.println("The second area is " + areaDelta + 
        " times the first.");
     System.out.println("The second circumference is " + circumferenceDelta + 
        " times the first.");
        
     if ((circumferenceDelta != 2.0) || (areaDelta != 4.0))
        System.out.println("\nCAUTION: The laws of mathematics have ceased " +
            "to apply to the universe! \n\tPlease panic and run around " +
            "in circles!");
            //that would be pretty bad. Unless it's just floating-point error.
            // or bit overflow. (I made that term up).
    }
}