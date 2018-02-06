
/**
 * Write a description of class TestFish here.
 * 
 * @author Michael Limiero
 * @version 5.5
 */
public class TestFish
{
    public static void main(String[] args)
    {
        Fish fritz = new Fish("orange","goldfish","Fritz",FishTank.WATER_FRESH);
        System.out.println(fritz + "\n");
        FishTank tank1 = new FishTank(25.0,FishTank.WATER_SALT,0,74.0);
        System.out.println(tank1 + "\n");
        Fish rover = new Fish("gray","tuna","Rover",FishTank.WATER_SALT,tank1);
        System.out.println(rover + "\n");
        System.out.println("Turn up the heat in the tank!");
        tank1.setTemp(120);
        rover.liveDays(1);
        System.out.println(tank1);
        System.out.println(rover);
        System.out.println("Okay, back to normal.");
        tank1.setTemp(74);
        System.out.println("\n20 days pass...");
        fritz.liveDays(20);
        System.out.println("Put Fritz in the tank...");
        fritz.setTank(tank1);
        System.out.println("Except it was salt-water and he's a goldfish.");
        System.out.println(fritz);
        System.out.println("\nFin. (pun really intended)");
    }
}
