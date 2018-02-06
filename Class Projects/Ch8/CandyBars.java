
/**
 * Write a description of class CandyBars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class CandyBars
{
    // instance variables - replace the example below with your own
    private String name;
    private double size;
    private int calories;
    static Scanner kb = new Scanner(System.in);

    /**
     * Constructor for objects of class CandyBars
     */
    public CandyBars(String name)
    {
        // initialise instance variables
        this.name = name; //that's how it works in real code...
    }

    public String getName()
    {
        return name;
    }
    public void inputSize()
    {
        System.out.println("What is the size of the " + name + "® bar???");
        do {
            size = kb.nextDouble();
            if (size < 1 || size > 6) System.out.print("You can't have that " +
                "size. Try again: ");
            } while (size < 1 || size > 6);
    }
    public void inputCalories()
    {
        System.out.println("How many calories are in the " + name + "® bar???");
        calories = kb.nextInt();
    }
    public double getCalPerOz()
    {
        return calories/size;
    }
    public String toString()
    {
        return "Name: " + name + ", Size: " + size + " oz., Calories: " + 
           calories + ", Cal/Oz.: " + getCalPerOz();
    }
}
