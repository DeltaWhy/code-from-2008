/**
 * Write a description of class FishTank here.
 * 
 * @author Michael Limiero
 * @version <=><
 */
public class FishTank
{
    private int numFish;
    private double volume; //in gallons
    private boolean waterType; //why use 40 bits when 1 will work?
    private double temperature;
    private boolean unitSystem; //just for fun
                                //only for temperature since I don't know
                                //gallons->liters off the top of my head.
    
    //define some constants for stuff
    public static final boolean WATER_SALT = true;
    public static final boolean WATER_FRESH = false;
    public static final boolean SYSTEM_ENGLISH = true;
    public static final boolean SYSTEM_METRIC = false;
    
    private Util u;
    
    public FishTank(double volume, boolean waterType, int numFish, 
        double temperature, boolean unitSystem)
    {
        this.volume = volume; //why don't we learn it this way?
        this.waterType = waterType;
        this.numFish = numFish;
        this.temperature = temperature;
        this.unitSystem = unitSystem;
        u = new Util(unitSystem);
    }
    public FishTank(double volume, boolean waterType, int numFish, 
        double temperature)
    {
        //there should be a shortcut, but I can't do
        // this = new FishTank(...);
        this.volume = volume;
        this.waterType = waterType;
        this.numFish = numFish;
        this.temperature = temperature;
        this.unitSystem = SYSTEM_ENGLISH;
        u = new Util(this.unitSystem);
    }
    public FishTank(double volume, boolean waterType)
    {
        this.volume = volume;
        this.waterType = waterType;
        this.numFish = 0;
        this.temperature = 0;
        this.unitSystem = SYSTEM_ENGLISH;
        u = new Util(this.unitSystem);
    }
    public FishTank(double volume, String waterType)
    {
        this.volume = volume;
        if (waterType.equalsIgnoreCase("salt")) 
        {
            this.waterType = WATER_SALT;
        } else {
            this.waterType = WATER_FRESH; //less chance of killing the
                                    //fish when this is default.
        }
        this.numFish = 0;
        this.temperature = 0;
        this.unitSystem = SYSTEM_ENGLISH;
        u = new Util(this.unitSystem);
    }
    public int addFish()
    {
        return addFish(1);
    }
    public int addFish(int newFish) //because return values are bomb-awesome
    {                               //but mostly because I'm bored.
        numFish += newFish;
        System.out.println("" + newFish + " fish were added to the tank.\n" +
            "Now you have " + numFish + " total."); //totally unneccesary.
        return numFish;
    }
    
    public void ichthycide()
    {
        setTemp(u.c(100)); //boiling
        System.out.println("One order of fish sticks coming up...");
        numFish = 0;
    }
    public void setTemp (double newTemp)
    {
        temperature = newTemp;
        System.out.print("Temperature is now " + temperature + " degrees ");
        if (unitSystem == SYSTEM_ENGLISH)
        {
            System.out.println("Farenheit");
        } else {
            System.out.println("Celsius");
        }
    }
    public void setWater (boolean waterType)
    {
        if (waterType != this.waterType && numFish > 0)
        {
            System.out.println("You killed your fish!");
            numFish = 0;
        }
        this.waterType = waterType;
    }
    public void setWater (String waterType)
    {
        if (waterType.equalsIgnoreCase("salt")) 
        {
            setWater(WATER_SALT);
        } else {
            setWater(WATER_FRESH); //less chance of killing the
                                    //fish when this is default.
        }
    }
    public boolean getWater()
    {
        return waterType;
    }
    public double getTemp()
    {
        return temperature;
    }
    public double getTempF()
    {
        return u.f(temperature);
    }
    public double getTempC()
    {
        return u.c(temperature);
    }
    public void setUnits(boolean unitSystem)
    {
        if (unitSystem == this.unitSystem) return;
        this.unitSystem = unitSystem;
        u.unitSystem = unitSystem;
        if (unitSystem == SYSTEM_ENGLISH)
        {
            temperature = Util.cToF(temperature);
        } else {
            temperature = Util.fToC(temperature);
        }
    }
    public boolean getUnits()
    {
        return unitSystem;
    }
    public String toString()
    {
        String s = "";
        s += "(Fishtank) Volume: " + volume + " gallons, Contains ";
        if (waterType == WATER_SALT)
        {
            s += "salt";
        } else {
            s += "fresh";
        }
        s+= " water.\n Temperature: " + temperature +
            " degrees ";
        if (unitSystem == SYSTEM_ENGLISH)
        {
            s += "Farenheit";
        } else {
            s += "Celsius";
        }
        s += "\n";
        for (int i = 0; i < numFish; i++)
            s += "<=><  ";
            //it's like a GUI only not at all like a GUI...
        return s;
    }
    public void print()
    {
        System.out.println(this.toString());
    }
}