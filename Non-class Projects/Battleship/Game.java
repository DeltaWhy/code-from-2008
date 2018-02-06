
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game implements MVCObject
{
    private EventManager em;
    public Player[] players = new Player[2];
    public int mode;
    public int turn;
    public static final int MODE_BETWEEN=0;
    public static final int MODE_SETUP=1;
    public static final int MODE_SHOOT=2;
    public boolean gameOver;
    public String status;
    public Game()
    {
        this.mode = MODE_BETWEEN;
        this.turn = 0;
        this.status = "Status Bar"; //TEMP
        this.gameOver = false;
    }
    private void shoot(ShootEvent e)
    {
        int notTurn = 0;
        if (turn == 0) notTurn = 1;
        boolean hit = players[notTurn].shipGrid.hit(e.point);
        em.notify(new PlacePegEvent(turn,hit,e.point));
    }
    private void changeTurn(Event e)
    {
        if (turn == 0) turn = 1; else turn = 0;
        em.notify(new TurnChangedEvent(turn));
        if (mode == MODE_SETUP && turn == 0)
        {
            mode = MODE_SHOOT;
            em.notify(new ModeChangedEvent(mode));
            status = "Player " + (turn+1) + " - shoot";
            em.notify(new StatusChangedEvent(status));
            em.notify(new WaitRequest());
        } else if (mode == MODE_SHOOT) {
            mode = MODE_BETWEEN;
            em.notify(new ModeChangedEvent(mode));
            status = "Waiting for player " + (turn+1);
            em.notify(new StatusChangedEvent(status));
        }
    }
    public void notify(Event e)
    {
        if (gameOver) return;
        if (e instanceof CreatedEvent)
        {
            MVCObject o = ((CreatedEvent)e).object;
            if (o instanceof Player)
            {
                Player p = (Player)o;
                players[p.number] = p;
            }
        } else if (e instanceof DebugEvent) {
            System.out.println("Game");
            if (players[0] != null) System.out.println("  has player 0");
            if (players[1] != null) System.out.println("  has player 1");
        } else if (e instanceof StartGameEvent) {
            mode = MODE_SETUP;
            em.notify(new GameStartedEvent());
            status = "Player 1 - place ships";
            em.notify(new StatusChangedEvent(status));
            em.notify(new PlaceShipRequest(turn));
        } else if (e instanceof TurnChangedEvent) {
            if (mode == MODE_SETUP)
            {
                status = "Player " + (turn+1) + " - place ships";
                em.notify(new StatusChangedEvent(status));
                em.notify(new PlaceShipRequest(turn));
            }
        } else if (e instanceof ErrorEvent) {
            String d = ((ErrorEvent)e).description;
            if (d.equals("Invalid ship")) em.notify(new PlaceShipRequest(turn));
        } else if (e instanceof TurnChangeRequest) {
            changeTurn(e);
        } else if (e instanceof ReadyEvent && mode == MODE_BETWEEN) {
            mode = MODE_SHOOT;
            em.notify(new ModeChangedEvent(mode));
            status = "Player " + (turn+1) + " - shoot";
            em.notify(new StatusChangedEvent(status));
        } else if (e instanceof StatusChangeRequest) {
            status = ((StatusChangeRequest)e).status;
            em.notify(new StatusChangedEvent(status));
        } else if (e instanceof ShootEvent) {
            shoot((ShootEvent)e);
        } else if (e instanceof PegPlacedEvent) {
            //em.notify(new TurnChangeRequest());
            changeTurn(e);
        } else if (e instanceof WinEvent) {
            gameOver = true;
            //em.stayAlive = false;
            //mode = MODE_BETWEEN;
            //em.notify(new ModeChangedEvent(mode));
            status = "Player " + (((WinEvent)e).player+1) + " wins!";
            em.notify(new StatusChangedEvent(status));
        }
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
