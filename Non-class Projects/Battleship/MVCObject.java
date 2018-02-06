
/**
 * Write a description of interface MVCObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface MVCObject
{
    void notify(Event e);
    void added(EventManager e);
    void removed(EventManager e);
}
