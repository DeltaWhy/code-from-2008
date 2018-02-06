
/**
 * Write a description of class BandBooster here.
 * 
 * @author Michael Limiero
 * @version 0.000001
 */
public class BandBooster
{
    private String name;
    private int boxesSold;
    
    public BandBooster (String name)
    {
        this.name = name;
        this.boxesSold = 0;
    }
    public String getName()
    {
        return name;
    }
    public void updateSales(int sales)
    {
        boxesSold += sales;
    }
    public String toString()
    {
        return name + ":\t" + boxesSold + " boxes";
    }
}
