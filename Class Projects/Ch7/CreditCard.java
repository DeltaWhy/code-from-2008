//Michael Limiero
import java.util.Scanner;
import java.text.NumberFormat;
public class CreditCard
{
    public static void main(String[] args)
    {
        double prevBalance = 0;
        double newCharges = 0;
        double interest = 0;
        double newBalance = 0;
        double minPayment = 0;
        Scanner kb = new Scanner(System.in);
        
        System.out.print("Input the previous balance (leave out $): ");
        prevBalance = kb.nextDouble();
        System.out.print("Input the new charges: ");
        newCharges = kb.nextDouble();
        System.out.println("\n"); //two newlines
        
        if (prevBalance > 0) interest = 0.02 * (prevBalance+newCharges);
        newBalance = prevBalance + newCharges + interest;
        if (newBalance < 50)
            minPayment = newBalance;
        else if (newBalance > 300)
            minPayment = 0.2 * newBalance;
        else //save some complicated expressions by putting this last
            minPayment = 50;
            
        NumberFormat money = NumberFormat.getCurrencyInstance();
        System.out.println("CS CARD International Statement");
        System.out.println("===============================");
        System.out.println("Previous Balance:\t" + money.format(prevBalance));
        System.out.println("Additional Charges:\t" + money.format(newCharges));
        System.out.println("Interest:\t\t" + money.format(interest));
        System.out.println("");
        System.out.println("New Balance:\t\t" + money.format(newBalance));
        System.out.println("");
        System.out.println("Minimum Payment:\t" + money.format(minPayment));
    }
}