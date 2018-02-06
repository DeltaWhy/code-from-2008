import java.awt.Point;
public class ShipGrid implements MVCObject
{
    public static final int SHIP_PATROL = 1;
    public static final int SHIP_CRUISER = 2;
    public static final int SHIP_SUBMARINE = 3;
    public static final int SHIP_BATTLESHIP = 4;
    public static final int SHIP_CARRIER = 5;
    public static final String[] SHIPS = {"","Patrol Boat","Cruiser","Submarine","Battleship","Carrier"};

    private EventManager em;
    public int number;
    public int[][] data = new int[10][10];
    private boolean has[] = new boolean[6];
    public ShipGrid(int number)
    {
        this.number = number;
    }
    private void placeShip(Point s, Point e)
    {
        //TODO: rewrite so this fails properly
        boolean horizontal;
        if (s.x == e.x) horizontal = false;
        else horizontal = true;
        boolean error = false;
        int length;
        int ship;
        if (horizontal)
        {
            length = e.x-s.x+1;
            ship = length;
            if (ship == 2) ship = 1;
            if (ship == 3 && has[3]) ship = 2;
            if (has[ship]) error=true;
            if (!error)
            {
                for (int i = 0; !error && i < length; i++)
                {
                    if (data[s.x+i][s.y] != 0) error=true;
                    if (!error) data[s.x+i][s.y] = ship;
                }
                //add error-fixing code
            }
            if (!error) has[ship] = true;
        } else {
            length = e.y-s.y+1;
            ship = length;
            if (ship == 2) ship = 1;
            if (ship == 3 && has[3]) ship = 2;
            if (has[ship]) error=true;
            if (!error)
            {
                for (int i = 0; !error && i < length; i++)
                {
                    if (data[s.x][s.y+i] != 0) error=true;
                    if (!error) data[s.x][s.y+i] = ship;
                }
            }
            if (!error) has[ship] = true;
        }
        if (error) { em.notify(new ErrorEvent("Invalid ship")); return; }
        if (ship > 0) em.notify(new StatusChangeRequest(SHIPS[ship] + " placed."));
    }
    public boolean hit(Point p)
    {
        boolean ans=false;
        if (data[p.x][p.y] != 0) ans=true;
        return ans;
    }
    //standard MVC stuff
    public void notify(Event e)
    {
        if (e instanceof PlaceShipEvent)
            if (((PlaceShipEvent)e).player == number)
            {
                Point s = ((PlaceShipEvent)e).start;
                Point en = ((PlaceShipEvent)e).end;
                placeShip(s,en);
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
