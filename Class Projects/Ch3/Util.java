public class Util
{
    public boolean unitSystem = FishTank.SYSTEM_ENGLISH;
    
    public Util(boolean unitSystem)
    {
        this.unitSystem = unitSystem;
    }
    
    //Java probably already had these but I don't want to look for them.
    public static double fToC(double f)
    {
        return (f-32)*(5.0/9.0);
    }
    public static double cToF(double c)
    {
        return c*(9.0/5.0) + 32;
    }
    public double f(double f)
    {
        if (unitSystem == FishTank.SYSTEM_ENGLISH)
        {
            return f;
        } else {
            return fToC(f);
        }
    }
    public double c(double c)
    {
        if (unitSystem == FishTank.SYSTEM_METRIC)
        {
            return c;
        } else {
            return cToF(c);
        }
    }
}