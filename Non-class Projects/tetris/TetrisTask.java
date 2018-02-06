import java.util.TimerTask;
public class TetrisTask extends TimerTask
{
    private TetrisApplet a;
    private Game g;
    public TetrisTask(TetrisApplet a, Game g)
    {
        super();
        this.a = a;
        this.g = g;
    }
    public void run()
    {
        g.tick();
        a.repaint();
    }
}