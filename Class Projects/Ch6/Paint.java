//***************************************************************
//File: Paint.java
//
//Purpose: Determine how much paint is needed to paint the walls 
//of a room given its length, width, and height
//***************************************************************
//Michael Limiero
import java.util.Scanner;

public class Paint
{
    public static void main(String[] args)
    {
        final int COVERAGE = 350;  //paint covers 350 sq ft/gal
        final int DOOR_SIZE = 20; //and if your doors happen to be a different
                                    //size, too bad!
        final int WINDOW_SIZE = 15; //and if your windows aren't all identical,
                                    //take your business elsewhere!
        //declare integers length, width, and height;
        int length, width, height;
        int doors, windows;
        //declare double totalSqFt;
        double totalSqFt; //why is this a double when the dimensions are ints?
        //declare double paintNeeded;
        double paintNeeded; //why can't I make up my own names??
        
        Scanner kb = new Scanner(System.in);
        //Prompt for and read in the length of the room
        System.out.print("Enter the length of the room in feet: ");
        length = kb.nextInt();
        
        //Prompt for and read in the width of the room
        System.out.print("Enter the width of the room in feet: ");
        width = kb.nextInt();

        //Prompt for and read in the height of the room
        System.out.print("Enter the height of the room in feet: ");
        height = kb.nextInt();
        
        //Are there doors or windows?
        System.out.print("Enter the number of " + DOOR_SIZE + " sq. ft doors: ");
        doors = kb.nextInt();
        System.out.print("Enter the number of " + WINDOW_SIZE + " sq. ft windows: ");
        windows = kb.nextInt();

        //Compute the total square feet to be painted--think
        //about the dimensions of each wall
        totalSqFt = 2*(length*height) + 2*(width*height);
            //basic geometry...
        totalSqFt -= doors * DOOR_SIZE; //subtract the doors and windows
        totalSqFt -= windows * WINDOW_SIZE; //which won't be painted
            
        //Compute the amount of paint needed
        paintNeeded = totalSqFt / COVERAGE;
        
        //Print the length, width, and height of the room and the
        //number of gallons of paint needed.
        System.out.println();
        System.out.println("For a room with length " + length + " ft, width " +
            width + " ft, height " + height + " ft, " + doors + " doors, and " +
            windows + "\nwindows, the area to be painted is " + totalSqFt +
            " square feet, and you will need \n" + 
            paintNeeded + " gallons of paint.");
            //of course, it would probably be easier to use a calculator,
            // not to mention waaaay cheaper.
    }
}