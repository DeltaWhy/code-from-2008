// *************************************************************
//   TestDistance.java
//
//   Test the Distance program
// *************************************************************
import java.util.Scanner;
public class TestDistance
{
    public static void main (String[] args)
    {
	double x1, y1, x2, y2; // coordinates of two points
	Scanner scan = new Scanner(System.in);

	// Read in the two points 
	System.out.print ("Enter the coordinates of the first point " +
               "(put a space between them): ");
	x1 = scan.nextDouble();
	y1 = scan.nextDouble();

	System.out.print ("Enter the coordinates of the second point: ");
	x2 = scan.nextDouble();
	y2 = scan.nextDouble();

	//Create a Distance object
	Distance d = new Distance(x1, x2, y1, y2);

	// Call the method to calculate the distance
	d.computeDistance(); //that should be automatic...

	// Print out the answer
	System.out.println(d);
	
    }
}