
/**
 * Write a description of class Hummer here.
 * 
 * @author Michael Limiero
 * @version -2+3i
 */
public class Hummer extends Car
{
    private static final int MPG = 8; //only slightly exaggerated
    private double interiorSize;
    private int roadClearance;
    
    public Hummer (int wheels, int doors, String color, int roadClearance)
    {
        super(wheels,doors,color);
        this.roadClearance = roadClearance;
    }
    public void setInteriorSize(double interiorSize)
    {
        this.interiorSize = interiorSize; //too long of a name
    }
    public String honk()
    {
        return "@#€*%¡¿?áü!"; //¡international keyboard es el mejor!
    }
    public String toString()
    {
        return super.toString() + "\nRoad clearance: " + roadClearance + ".\n"+
            "You got a problem with that?";
    }
}
