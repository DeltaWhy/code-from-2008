public class PlaceShipRequest extends Event
{
    public int player;
    //public MVCObject parent;
    public PlaceShipRequest(int player)
    {
        this.player = player;
//        this.parent = parent;
    }
}
