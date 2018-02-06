
/**
 * Write a description of class TestTank here.
 * 
 * @author Michael Limiero
 * @version <=><
 */
public class TestTank
{
    public static void main(String[] args)
    {
        FishTank tank1 = new FishTank(25.0,"fresh");
        FishTank tank2 = new FishTank(87.5, FishTank.WATER_SALT, 7, 72.3);

        System.out.println("Tank 1");
        System.out.println(tank1);
        System.out.println("Tank 2");
        System.out.println(tank2);
        
        System.out.println("\nTank 1");
        System.out.println("Changing temp and adding fish...");
        tank1.setUnits(FishTank.SYSTEM_METRIC);
        tank1.setTemp(Util.fToC(81.2)); //converts from Farenheit
        tank1.addFish();
        tank1.addFish();
        tank1.addFish(2);
        
        System.out.println("\n" + tank1 + "\n");
        System.out.println("The temperature for tank 1 is: " + 
                tank1.getTemp() + ".\n The temperature for tank 2 is " +
                "still: " + tank2.getTemp() + ". Shocking, isn't it?\n");
        System.out.println("Tank 2");
        System.out.println("Calling ichthycide()...");
        tank2.ichthycide(); //best name EVER!
        System.out.println(tank2);
    }
}
