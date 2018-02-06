import java.util.LinkedList;
/**
 * Write a description of class EventManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventManager
{
    private MVCObject[] objs = new MVCObject[32];
    private int numobjs;
    private boolean debug=false;
    private LinkedList<Event> evtQueue;
    private boolean working=false;
    //public boolean stayAlive = false;
    private AppletView appletView;
    public EventManager()
    {
        this.numobjs = 0;
        this.debug=true;
        this.evtQueue = new LinkedList();
    }
    public void addObject(MVCObject obj)
    {
        objs[numobjs] = obj;
        numobjs++;
        obj.added(this);
        if (obj instanceof AppletView) this.appletView = (AppletView)obj;
    }
    public void removeObject(MVCObject obj)
    {
        for (int i = 0; i < numobjs; i++)
        {
            if (objs[i] == obj) 
            {
                objs[i].removed(this);
                objs[i] = null;
            }
        }
    }
    public void waitForClick()
    {
        //hang this thread while we wait for click in AppletView.
        if (appletView == null) return;
        appletView.waiting=true;
        while (appletView.waiting);
    }
    public void notify(Event e)
    {
        if (!working)
        {
            notifyNow(e);
            return;
        }
        evtQueue.addLast(e);
    }
    public void notifyNow(Event e)
    {
        working = true;
        if (e instanceof DebugEvent)
            for (int i = 0; i < numobjs; i++)
                if (objs[i] != null)
                    System.out.println(objs[i].getClass().getName());
        if (debug)
            System.out.println(e.getClass().getName());
        if (e instanceof ModeChangedEvent)
            if (((ModeChangedEvent)e).mode == 0)
                System.out.println();
        /*if (e instanceof WinEvent)
            System.out.println("WinEvent");*/
        for (int i = 0; i < numobjs; i++)
        {
            if (objs[i] != null)
            {
                objs[i].notify(e);
            }
        }
        if (evtQueue.size() != 0)
        {
            notifyNow(evtQueue.removeFirst());
        }
        working = false;
        if (e instanceof WaitRequest)
            waitForClick();
            //this should work because AppletView has its own freaking thread.
    }
}
