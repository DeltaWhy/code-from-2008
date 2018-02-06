
/**
 * Write a description of class HelloWorldEvent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelloWorldEvent extends MVCEvent
{
    // instance variables - replace the example below with your own
    public static String type="HelloWorldEvent";
    public String data;
    /**
     * Constructor for objects of class HelloWorldEvent
     */
    public HelloWorldEvent(String data)
    {
        this.type = "HelloWorldEvent";
        this.data = data;
    }
}
