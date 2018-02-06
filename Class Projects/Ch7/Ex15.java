
/**
 * Exercise 15 a and b
 * 
 * @author Michael Limiero 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.text.NumberFormat;
public class Ex15
{
    public static double getOrderTotal(int bp, int nb)
    {
        double total;
        if (bp + nb >= 12) total = 14 * (bp+nb);
        else if (bp + nb >= 3) total = 15.95 * (bp+nb);
        else if (bp == 1 && nb == 1) total = 37.95;
        else total = 18.95*bp + 21.95*nb;
        return total;
    }
    public static void main(String[] args)
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);
        System.out.print("How many copies of \"Be Prepared\"? ");
        int bp = kb.nextInt();
        System.out.print("How many copies of \"Next Best\"? ");
        int nb = kb.nextInt();
        double total = getOrderTotal(bp,nb);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        System.out.println("Your total is " + money.format(total));
    }
}