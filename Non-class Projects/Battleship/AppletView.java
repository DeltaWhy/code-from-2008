import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Actually a misnomer, since this is also a controller.
 * (Well, it will be when it's done.)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AppletView extends JApplet implements MVCObject, MouseListener
{
    public static final int WIDTH = 480;
    public static final int HEIGHT = 640;
    public static final int GRIDSIZE = 460;
    public boolean waiting=false;
    private EventManager em;
    private Game game;
    public void init()
    {
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        addMouseListener(this);
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        paintGrid(g);
        paintStatus(g);
        if(game!=null && game.mode == Game.MODE_SETUP) paintShips(g,game.turn);
        if(game!=null && game.mode == Game.MODE_SHOOT) paintPegs(g);
    }
    private void paintGrid(Graphics g)
    {
        g.setColor(Color.BLACK);
        if (game.mode == Game.MODE_BETWEEN) g.setColor(Color.LIGHT_GRAY);
        int xO = (WIDTH - GRIDSIZE)/2;
        int yO = (HEIGHT - GRIDSIZE)/2;
        g.drawRect(xO,yO,GRIDSIZE,GRIDSIZE);
        g.drawRect(xO-1,yO-1,GRIDSIZE+2,GRIDSIZE+2);
        int blockSize = GRIDSIZE/10;
        for (int x = blockSize; x < GRIDSIZE; x+=blockSize)
        {
            g.drawLine(xO+x,yO,xO+x,yO+GRIDSIZE);
        }
        for (int y = blockSize; y < GRIDSIZE; y+=blockSize)
        {
            g.drawLine(xO,yO+y,xO+GRIDSIZE,yO+y);
        }
    }
    private void paintPeg(Graphics g, boolean hit, Point point)
    {
        int xO = (WIDTH - GRIDSIZE)/2;
        int yO = (HEIGHT - GRIDSIZE)/2;
        int blockSize = GRIDSIZE/10;
        int gapSize = 4;
        if (hit) { g.setColor(Color.RED); } else { g.setColor(Color.WHITE); }
        g.fillOval(xO + blockSize*point.x + gapSize, yO + blockSize*point.y + gapSize, blockSize-gapSize*2, blockSize-gapSize*2);
        g.setColor(Color.BLACK);
        g.drawOval(xO + blockSize*point.x + gapSize, yO + blockSize*point.y + gapSize, blockSize-gapSize*2, blockSize-gapSize*2);
    }
    private void paintPegs(Graphics g)
    {
        if (game == null) return;
        PegGrid p = game.players[game.turn].pegGrid;
        for (int x = 0; x < 10; x++)
            for (int y = 0; y < 10; y++)
            {
                if (p.data[x][y] == PegGrid.PEG_WHITE)
                    paintPeg(g, false, new Point(x,y));
                if (p.data[x][y] == PegGrid.PEG_RED)
                    paintPeg(g, true, new Point(x,y));
            }
    }
        
    private void paintStatus(Graphics g)
    {
        if (game == null) return;
        String str = game.status;
        g.setColor(Color.BLACK);
        int xO = 15;
        int yO = (HEIGHT - GRIDSIZE)/2;
        yO += GRIDSIZE;
        yO += (HEIGHT - yO)/2;
        yO -= 5;
        g.drawString(str,xO,yO);
    }
    private void paintShip(Graphics g, int player, Point start, Point end)
    {
        int xO = (WIDTH - GRIDSIZE)/2;
        int yO = (HEIGHT - GRIDSIZE)/2;
        int blockSize = GRIDSIZE/10;
        int gapSize = 4;
        if (player == 0) { g.setColor(Color.RED); } else { g.setColor(Color.BLUE); }
        g.fillOval(xO+blockSize*start.x+gapSize,yO+blockSize*start.y+gapSize,blockSize-2*gapSize,blockSize-2*gapSize);
        g.fillOval(xO+blockSize*end.x+gapSize,yO+blockSize*end.y+gapSize,blockSize-2*gapSize,blockSize-2*gapSize);
        boolean horizontal = false;
        if (start.y == end.y) horizontal = true;
        if (horizontal)
        {
            g.fillRect(xO+blockSize*start.x+gapSize+(blockSize-2*gapSize)/2,yO+blockSize*start.y+gapSize,blockSize*(end.x-start.x),blockSize-2*gapSize);
        } else {
            g.fillRect(xO+blockSize*start.x+gapSize,yO+blockSize*start.y+gapSize+(blockSize-2*gapSize)/2,blockSize-2*gapSize,blockSize*(end.y-start.y));            
        }
    }
    private void paintShips(Graphics g, int player)
    {
        ShipGrid s = game.players[player].shipGrid;
        Point ps = new Point(-1,-1);
        Point pe = new Point(-1,-1);
        int working=0;
        for (int y = 0; y < 10; y++)
            for (int x = 0; x < 10; x++)
            {
                if (s.data[x][y] != working)
                {
                    if (working == 0) 
                    {
                        if (x<9 && s.data[x+1][y] == s.data[x][y])
                        {
                            working = s.data[x][y];
                            ps = new Point(x,y);
                        }
                    } else {
                        if (x>0) pe = new Point(x-1,y);
                        if (x==0) pe = new Point(9,y-1);
                        paintShip(g,player,ps,pe);
                        working = 0;
                        if (s.data[x][y] != 0)
                        {
                            if (x<9 && s.data[x+1][y] == s.data[x][y])
                            {
                                working = 0;
                                working = s.data[x][y];
                                ps = new Point(x,y);
                            }   
                        }
                    }
                }
            }       
        working=0;
        for (int x = 0; x < 10; x++)
            for (int y = 0; y < 10; y++)
            {
                if (s.data[x][y] != working)
                {
                    if (working == 0) 
                    {
                        if (y<9 && s.data[x][y+1] == s.data[x][y])
                        {
                            working = s.data[x][y];
                            ps = new Point(x,y);
                        }
                    } else {
                        if (y>0) pe = new Point(x,y-1);
                        if (y==0) pe = new Point(x-1,9);
                        paintShip(g,player,ps,pe);
                        if (s.data[x][y] == 0)
                        {
                            working = 0;
                        } else {
                            working = 0;
                            if (y<9 && s.data[x][y+1] == s.data[x][y])
                            {
                                working = s.data[x][y];
                                ps = new Point(x,y);
                            }   
                        }
                    }
                }
            }       

    }
    public void update(Graphics g)
    {
        paint(g);
    }
    //Mouse stuff follows
    public void mouseClicked(MouseEvent e)
    {
        if (waiting) { waiting = false; return; }
        //this is somewhat magic
        // it should work because AppletView gets its own
        //  freaking thread which is causing me much pain.
        //Update: it doesn't, and AppletView only sometimes has a thread????????
        // This is not fun anymore.
        
        //System.out.println("mouseClicked()");
        int xO = (WIDTH - GRIDSIZE)/2;
        int yO = (HEIGHT - GRIDSIZE)/2;
        int blockSize = GRIDSIZE/10;
        if (game.mode == Game.MODE_SETUP)
        {
            //not implemented
            return;
        } else if (game.mode == Game.MODE_BETWEEN) {
            em.notify(new ReadyEvent());
        } else if (game.mode == Game.MODE_SHOOT) {
            if (e.getButton()!=MouseEvent.BUTTON1) return;
            int x = e.getX() - xO;
            int y = e.getY() - yO;
            if (x < 0 || y < 0) return;
            if (x >= GRIDSIZE || y >= GRIDSIZE) return;
            x /= blockSize;
            y /= blockSize;
            //System.out.println("Shooting");
            em.notify(new ShootEvent(game.turn, new Point(x,y)));
        }
    }
    public void mouseEntered(MouseEvent e) {} //doesn't matter
    public void mouseExited(MouseEvent e) {} //doesn't matter
    public void mousePressed(MouseEvent e) {} //doesn't matter
    public void mouseReleased(MouseEvent e) {} //doesn't matter
    //MVC stuff follows
    public void notify(Event e)
    {
        if (e instanceof CreatedEvent)
            if (((CreatedEvent)e).object instanceof Game)
                game = (Game)(((CreatedEvent)e).object);
        if (e instanceof PegPlacedEvent)
        {
            repaint();
            //em.waitForClick();
        }
        repaint();
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
