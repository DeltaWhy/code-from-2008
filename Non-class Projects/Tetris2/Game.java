import java.util.Timer;
public class Game
{
    public int score, lines, level;
    public int nextPiece;
    public int currPiece;
    public int lock = 0;
    public boolean started = false;
    private Timer timer;
    private TetrisTask task;
    private Grid grid;
    public Game()
    {
        this.score = 0;
        this.lines = 0;
        this.level = 0;
        this.currPiece = (int)(Math.random()*7)+1;
        this.nextPiece = (int)(Math.random()*7)+1;
        this.timer = new Timer();
    }
    public void start()
    {
        timer.schedule(task,600-50*level,600-50*level);
        started = true;
    }
    public void setTask(TetrisTask t)
    {
        task = t;
    }
    public void tick()
    {
        while (lock > 0) {}
        downArrow();
    }
    public void newPiece()
    {
        currPiece = nextPiece;
        grid.newPiece(currPiece);
        nextPiece = (int)(Math.random()*7)+1;
    }
    public void test()
    {
        if (!leftArrow()) downArrow();
    }
    public boolean leftArrow()
    {
        lock++;
        boolean ans = grid.shiftPiece(-1);
        lock--;
        return ans;
    }
    public boolean rightArrow()
    {
        lock++;
        boolean ans = grid.shiftPiece(1);
        lock--;
        return ans;
    }
    public boolean upArrow()
    {
        lock++;
        boolean ans = grid.rotatePiece();
        lock--;
        return ans;
    }
    public boolean downArrow()
    {
        if (!started) start();
        lock++;
        //TEMP
        boolean ans = grid.dropPiece();
        if (!ans) 
        {
            checkForLines();
            newPiece(); //TEMP - doesn't belong here //or does it??
        }
        lock--;
        return ans;
    }
    private void checkForLines()
    {
        int ans = grid.checkForLines(); //handle the completed lines
        //ans is number of completed lines
        //do some score-related stuff
        lines += ans;
        score += ans * ans * 50;
    }
    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }
    public Grid getGrid()
    {
        return this.grid;
    }
}
