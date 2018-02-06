import java.awt.Point;
public class PegPlacedEvent extends Event
{
    public int player;
    public boolean hit;
    public Point point;
    public PegPlacedEvent(int player, boolean hit, Point point)
    {
        this.player = player;
        this.hit = hit;
        this.point = point;
    }
}
