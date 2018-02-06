
/**
 * Hello World using the Model-View-Controller
 * architecture for most optimum extens-o-bility!
 * 'Cuz extens-o-bility is t3h 1337X0RZ!
 * 
 * @author Michael Limiero
 * @version 2.718281828459
 */
public class MVCHelloWorld
{
    public static void main(String[] args)
    {
        EventManager em = new EventManager();
        HelloWorldController c = new HelloWorldController(em);
        ConsoleView v = new ConsoleView(em);
        em.setController(c);
        em.setView(v);
        c.helloWorld();
    }
}
