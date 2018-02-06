
/**
 * Write a description of class EventManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventManager
{
    // instance variables - replace the example below with your own
    private Controller controller;
    private View view;

    public EventManager()
    {
    }
    
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    public void setView(View view)
    {
        this.view = view;
    }
    public void fire(MVCEvent event)
    {
        if (controller != null) controller.fire(event);
        if (view != null) view.fire(event);
    }
}
