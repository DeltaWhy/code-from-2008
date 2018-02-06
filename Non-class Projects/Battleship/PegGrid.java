import java.awt.Point;
/**
 * Write a description of class PegGrid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PegGrid implements MVCObject
{
    public static final int PEG_RED = 1;
    public static final int PEG_WHITE = 2;
    private EventManager em;
    public int number;
    public int[][] data = new int[10][10];
    public int hits;
    public PegGrid(int number)
    {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                data[i][j] = 0;
        this.number = number;
        this.hits = 0;
    }
    public boolean place(boolean hit, Point p)
    {
        //returns true if the player wins
        if (hit) 
        {
            data[p.x][p.y] = PEG_RED;
            hits++;
            if (hits == (2+3+3+4+5)) return true;
        } else {
            data[p.x][p.y] = PEG_WHITE;
        }
        return false;
    }
    public void notify(Event e)
    {
        
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
