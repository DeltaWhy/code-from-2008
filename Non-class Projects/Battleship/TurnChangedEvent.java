public class TurnChangedEvent extends Event
{
    public int player;
    public TurnChangedEvent(int player)
    {
        this.player = player;
    }
}
