/*
 * Michael Limiero
 *
 * The population of Mexico in 2005 was 106.2 million. 
 * Write a program that calculates and prints out the year 
 * in which the population of Mexico will reach 120 million, 
 * assuming a constant growth rate of 1.7% per year. Use a while loop.
 */
public class Population
{
    public static void main()
    {
        double pop = 106200000; //because I'm not sure how big int is and
                                //we haven't learned long
        double rate = 1.017;
        int year = 0;
        while (pop < 120000000)
        {
            pop *= rate;
            year++;
        }
        year += 2005;
        System.out.println ("Mexico's population will reach 120 million in " + 
            year + ".");
    }
}