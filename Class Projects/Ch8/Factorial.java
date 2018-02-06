//Michael Limiero
//Calculate the factorial of a number in the least efficient way...
import java.util.Scanner;
public class Factorial
{
    public static void main()
    {
        Scanner kb = new Scanner(System.in);
        int n=-1;
        int ans = 1;
        while (n < 0)
        {
            System.out.print("Enter a non-negative integer: ");
            n = kb.nextInt();
        }
        for (int i = 2; i <= n; i++)
            ans *= i;
        System.out.println(n + "! = " + ans);
    }
}