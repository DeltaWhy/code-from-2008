public class StatusChangedEvent extends Event
{
    public String status;
    public StatusChangedEvent(String status)
    {
        this.status = status;
    }
}
