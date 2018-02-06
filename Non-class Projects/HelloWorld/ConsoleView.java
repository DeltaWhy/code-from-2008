
/**
 * Write a description of class ConsoleView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConsoleView extends View
{
    public EventManager em;
    public ConsoleView(EventManager em)
    {
        this.em = em;
    }
    public void fire(MVCEvent ev)
    {
        if (ev instanceof HelloWorldEvent)
        {
            HelloWorldEvent hev = (HelloWorldEvent) ev;
            System.out.println(hev.data);
        }
    }
}