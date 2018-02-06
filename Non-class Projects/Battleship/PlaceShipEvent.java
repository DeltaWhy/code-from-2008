import java.awt.Point;
public class PlaceShipEvent extends Event
{
    public int player;
    public Point start;
    public Point end;
    public PlaceShipEvent(int player, Point start, Point end)
    {
        this.player = player;
        this.start = start;
        this.end = end;
    }
}
