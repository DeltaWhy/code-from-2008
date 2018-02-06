
/**
 * Write a description of class Car here.
 * 
 * @author Michael Limiero
 * @version -3
 */
public class Car
{
    private int wheels;
    private int doors;
    private String color;
    
    public Car (int wheels, int doors, String color)
    {
        this.wheels = wheels;
        this.doors = doors;
        this.color = color;
    }
    public Car (int doors, String color)
    {
        this.wheels = 4;
        this.doors = doors;
        this.color = color;
    }
    public void paintJob(String color)
    {
        this.color = color; //why don't we just make these public
                            //and avoid all the setters and getters?
                            //That's how Python does it...
    }
    public void rust()
    {
        this.color = "rusty";
        System.out.println("You should have taken better care of this car.\n" +
            "It just rusted and now you have to repaint it.");
    }
    public String honk()
    {
        return "Meep-meep!";
    }
    public String toString()
    {
        return "This car has " + wheels + " wheels and " + doors + " doors. " +
            "The color is " + color + ".";
    }
}
