import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
public class Runner
{
    public static void main(String[] args)
    {
        ActorWorld a = new ActorWorld();
        a.addOccupantClass("info.gridworld.actor.Actor");
        a.addOccupantClass("info.gridworld.actor.Bug");
        a.addOccupantClass("info.gridworld.actor.Flower");
        a.addOccupantClass("info.gridworld.actor.Rock");
        a.addOccupantClass("info.gridworld.actor.Critter");
        a.show();
    }
}
