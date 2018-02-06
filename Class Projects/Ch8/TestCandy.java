
/**
 * Write a description of class TestCandy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCandy
{
    public static void main(String[] args)
    {
        CandyBars snickers = new CandyBars("Snickers");
        CandyBars payday = new CandyBars("PayDay");
        
        snickers.inputSize();
        payday.inputSize();
        
        snickers.inputCalories();
        payday.inputCalories();
        
        System.out.println(snickers);
        System.out.println(payday);
    }
}