
/**
 * Adds the odd numbers from 1 to n
 * I couldn't think of anything witty to say.
 * 
 * @author Michael Limiero
 * @version a_version_number_or_a_date
 */
public class Ex3
{
    public static int addOdds(int n)
    {
        int sum = 0;
        for (int i = 1; i <= n; i+=2)
        {
            sum += i;
        }
        return sum;
    }
    public static void main()
    {
        int n;
        java.util.Scanner kb = new java.util.Scanner(System.in);
        System.out.print("n=? ");
        n = kb.nextInt();
        System.out.println(addOdds(n));
    }
}
