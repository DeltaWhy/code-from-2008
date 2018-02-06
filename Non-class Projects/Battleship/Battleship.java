//BUG: I don't understand this nonsense.
// I thought AppletView got its own thread but now it apparently
//  doesn't but I need it to in order to wait...
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class Battleship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battleship
{
    public static void main(String[] args)
    {
        EventManager em = new EventManager();
        AppletView a = new AppletView();
        em.addObject(a);
        MVCObject o;
        o = new TerminalController();
        em.addObject(o);
        o = new Game();
        em.addObject(o);
        em.notify(new CreatedEvent(o));
        for (int i = 0; i <= 1; i++)
        {
            o = new Player(i);
            em.addObject(o);
            em.notify(new CreatedEvent(o));
            o = new ShipGrid(i);
            em.addObject(o);
            em.notify(new CreatedEvent(o));
            o = new PegGrid(i);
            em.addObject(o);
            em.notify(new CreatedEvent(o));
        }
        //em.notify(new DebugEvent());
        FrameView window = new FrameView(a);
        window.setBounds(300,300,480,640);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        em.notify(new StartGameEvent());
    }
}
