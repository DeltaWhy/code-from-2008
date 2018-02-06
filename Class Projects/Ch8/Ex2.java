
/**
 * Simulating Kevin since 1881.
 * 
 * @author Michael Limiero
 * @version 1881
 */
public class Ex2
{
    public static void main()
    {
        double know = 0.0;
        double didntKnow = 1.0;
        double rate = 0.1;
        double needToKnow = 0.95;
        int months = 0;
        int years = 0;
        for (; know < needToKnow; months++)
        {
            know += didntKnow * rate;
            didntKnow = 1.0 - know;
        }
        years = months / 12;
        months = months % 12;
        System.out.println("Kevin is a bit \"slow.\" It will take him");
        System.out.println(years + " years and " + months + " months before" +
            " he can pass the exam.");
    }
}
