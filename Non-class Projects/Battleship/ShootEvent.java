import java.awt.Point;
public class ShootEvent extends Event
{
    public int player; //source player, not destination
    public Point point;
    public ShootEvent(int player, Point point)
    {
        this.player = player;
        this.point = point;
    }
}
