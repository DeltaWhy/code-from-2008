
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player implements MVCObject
{
    private EventManager em;
    public int number;
    public PegGrid pegGrid;
    public ShipGrid shipGrid;
    public Player(int number)
    {
        this.number = number;
    }
    public void notify(Event e)
    {
        if (e instanceof CreatedEvent)
        {
            MVCObject f = ((CreatedEvent)e).object;
            if (f instanceof PegGrid && ((PegGrid)f).number == number)
                this.pegGrid = (PegGrid)f;
            if (f instanceof ShipGrid && ((ShipGrid)f).number == number)
                this.shipGrid = (ShipGrid)f;
        } else if (e instanceof DebugEvent) {
            System.out.println("Player " + number);
            if (pegGrid != null) System.out.println("  has pegGrid");
            if (shipGrid != null) System.out.println("  has shipGrid");
        } else if (e instanceof PlacePegEvent) {
            PlacePegEvent f = (PlacePegEvent)e;
            if (f.player == number)
            {
                boolean won = pegGrid.place(f.hit,f.point);
                em.notify(new PegPlacedEvent(f.player,f.hit,f.point));
                if (won) em.notify(new WinEvent(number));
            }
        }
    }
    public void added(EventManager e)
    {
        em = e;
    }
    public void removed(EventManager e)
    {
        if (em == e) em = null;
    }
}
