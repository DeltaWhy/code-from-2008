public class StatusChangeRequest extends Event
{
    public String status;
    public StatusChangeRequest(String status)
    {
        this.status = status;
    }
}
