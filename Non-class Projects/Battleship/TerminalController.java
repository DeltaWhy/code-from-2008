//BUG: Doesn't work properly with bad ships
import java.util.Scanner;
import java.awt.Point;
public class TerminalController implements MVCObject
{
    private EventManager em;
    private Scanner kb;
    private int numShips = 0;
    public TerminalController()
    {
        System.out.println("Battleship");
        this.kb = new Scanner(System.in);
        this.numShips = 0;
    }
    private void placeShip(int player)
    {
        Point ps=new Point(-1,-1),pe=new Point(-1,-1);
        for (boolean error=true;error==true;)
        {
            error = false;
            System.out.print("Start point (ex. E3): ");
            ps = getPoint();
            System.out.print("End point (ex. E5): ");
            pe = getPoint();
            if (ps.x != pe.x && ps.y != pe.y) error = true;
            boolean horizontal=false;
            if (ps.y == pe.y) horizontal = true;
            int length=0;
            if (horizontal) length = pe.x-ps.x+1;
            if (!horizontal) length = pe.y-ps.y+1;
            if (length < 0)
            {
                Point pp = ps;
                ps = pe;
                pe = pp;
                if (horizontal) length = pe.x-ps.x+1;
                if (!horizontal) length = pe.y-ps.y+1;
            }
            if (length < 2 || length > 5) error=true;
            if (error) System.out.println("Error: invalid ship");
        }
        em.notify(new PlaceShipEvent(player,ps,pe));
        numShips++; //maybe shouldn't be here??
        if (numShips<5) { em.notify(new PlaceShipRequest(player)); }
        if (numShips==5) { em.notify(new WaitRequest()); em.notify(new TurnChangeRequest()); }
    }
    private Point getPoint()
    {
        String ss = kb.next();
        int x,y;
        char cy = ss.substring(0,1).toUpperCase().toCharArray()[0];
        y = (int)cy - 65;
        x = Integer.parseInt(ss.substring(1,2))-1;
        if (x == 0 && ss.length() > 2 && ss.substring(2,3).equals("0")) x = 9;
        if (x < 0) x = 0;
        if (x > 9) x = 9;
        if (y < 0) y = 0;
        if (y > 9) y = 9;
        return new Point(x,y);
    }
    //standard MVC stuff
    public void notify(Event e)
    {
        /*if (e instanceof GameStartedEvent)
            System.out.println("Game Started.");*/
        if (e instanceof StatusChangedEvent)
            System.out.println(((StatusChangedEvent)e).status);
        if (e instanceof TurnChangedEvent)
        {
            for (int i = 0; i < 50; i++) System.out.println();
            numShips = 0;
        }
        if (e instanceof PlaceShipRequest)
            placeShip(((PlaceShipRequest)e).player);
        if (e instanceof PlaceShipEvent)
        {
        }
        if (e instanceof ErrorEvent)
        {
            System.out.println(((ErrorEvent)e).description);
            /*if (((ErrorEvent)e).description.equals("Invalid ship"))
                numShips--;*/
        }
        if (e instanceof ModeChangedEvent)
            if (((ModeChangedEvent)e).mode == Game.MODE_BETWEEN)
            {
                em.notify(new WaitRequest());
                em.notify(new ReadyEvent());
            }
        if (e instanceof WaitRequest) kb.next();
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
