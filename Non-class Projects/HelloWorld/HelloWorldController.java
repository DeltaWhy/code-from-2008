
/**
 * Write a description of class HelloWorldController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelloWorldController extends Controller
{
    public EventManager em;
    public HelloWorldController(EventManager em)
    {
        this.em = em;
    }
    public void helloWorld()
    {
        MVCEvent ev = new HelloWorldEvent("Hello World already! Good grief...");
        em.fire(ev);
    }
    public void fire(MVCEvent ev)
    {
        return;
    }
}
