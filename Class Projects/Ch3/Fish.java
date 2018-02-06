
/**
 * Write a description of class Fish here.
 * 
 * @author Michael Limiero
 * @version 5.5
 */
public class Fish
{
    private String color;
    private String species;
    private String name;
    private double age;
    private boolean alive;
    private boolean waterType;
    private FishTank tank; //had to add a few methods to FishTank
    
    public Fish(String color, String species, String name, boolean waterType)
    {
        this.color = color;
        this.species = species;
        this.name = name;
        this.age = 0;
        this.alive = true;
        this.waterType = waterType;
        this.tank = null;
    }
    public Fish(String color, String species, String name, boolean waterType, FishTank tank)
    {
        this.color = color;
        this.species = species;
        this.name = name;
        this.age = 0;
        this.alive = true;
        this.waterType = waterType;
        this.tank = tank;
        checkIfAlive();
    }
    public boolean isAlive()
    {
        return alive;
    }
    public void kill()
    {
        alive = false;
        checkIfAlive();
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setTank(FishTank tank)
    {
        this.tank = tank;
        checkIfAlive();
    }
    public FishTank getTank()
    {
        return tank;
    }
    public String getColor()
    {
        return color;
        //no set method because you shouldn't paint a fish
    }
    public void liveDays(int days)
    {
        age += days/365.0;
        checkIfAlive();
    }
    private void checkIfAlive()
    {
        if (tank != null)
        {
            if (tank.getTempF() < 65 || tank.getTempF() > 90) alive = false;
            if (tank.getWater() != waterType) alive = false;
        }
        if (age >= 2) alive = false;
        if (Math.random() < 0.1) alive = false; //die randomly
                                                //just like my real fish
        if (!alive) System.out.println(name + " died!");
    }
    public String toString()
    {
        String s = name + " - Species: " + species + ", Color: " + color;
        s += ".\nLives in ";
        if (waterType == FishTank.WATER_SALT)
        {
            s += "salt";
        } else {
            s += "fresh";
        }
        s += " water. Age: " + age + " years.\n";
        if (alive)
        {
            s += "This fish is alive and healthy.";
        } else {
            s += "This fish has shuffled off the mortal coil. \nHe " +
                "wouldn't voom if you put a thousand volts through 'im!";
        }
        s += "\n<=><";
        return s;
    }
}
