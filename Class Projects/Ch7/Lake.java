/**
 * Another program that doesn't do anything a piece of paper couldn't.
 * 
 * @author Michael Limiero
 * @version Q
 */
public class Lake
{
    public static void main(String[] args)
    {
        double temp;
        String ans;
        java.util.Scanner kb = new java.util.Scanner(System.in);
        
        System.out.print("What's the temperature? ");
        temp = kb.nextDouble();
        if (temp >= 80) ans = "go swimming";
        else if (temp >= 60) ans = "play tennis";
        else if (temp >= 40) ans = "play golf";
        else ans = "go skiing";
        
        if (temp > 95 || temp < 20) ans = "visit our shops";
        
        ans = "You should " + ans + "!";
        System.out.println(ans);
    }
}
