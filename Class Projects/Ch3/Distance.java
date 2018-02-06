/**
 * Distance.java
 * Creates a Distance object holding the coordinates of two points 
 * and their distance. 
 * @author (your name) 
 */
public class Distance
{
    // instance variables - coordinates of two points
    private double x1, y1,x2, y2;
    private double distance;

    //Constructor for objects of class Distance
    public Distance(double cx1, double cx2, double cy1, double cy2)
    {
        // initialize instance variables
        x1 = cx1;
        y1 = cy1;        
        x2 = cx2;
        y2 = cy2;
    }

  // Complete the method for computing the distance
    public void computeDistance()
    {
       //fill in assignment statement for computation of distance
        distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        //WHY DOES JAVA NOT HAVE BUILT-IN EXPONENTS?!?!?!?!
        //THAT'S QUITE POSSIBLY THE DUMBEST THING I'VE EVER HEARD!
    }
    
  //create a toString method generating a string stating the distance between the points
    public String toString()
    {
        return "The distance between (" + x1 + "," + y1 + ") and (" +
            x2 + "," + y2 + ") is " + distance;
    }
}