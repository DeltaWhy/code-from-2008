
/**
 * Write a description of class CreatedEvent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreatedEvent extends Event
{
    public MVCObject object;
    public CreatedEvent(MVCObject object)
    {
        this.object = object;
    }
}
