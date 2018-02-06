//Michael Limiero
import java.util.Scanner;
import java.text.NumberFormat;
public class Shopping
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        String itemName = "";
        double price = 0.0;
        int quantity = 0;
        ShoppingCart cart = new ShoppingCart();
        
        System.out.println("SimShopping\n");
        while (true) //loop exits with a break statement
        {
            System.out.print("Enter the item name or 'quit': ");
            itemName = kb.nextLine();
            if (itemName.equalsIgnoreCase("quit")) break;
            System.out.print("Enter the price: ");
            price = kb.nextDouble();
            System.out.print("Enter the quantity to purchase: ");
            quantity = kb.nextInt();
            
            cart.addToCart(itemName, price, quantity);
            System.out.println();
            System.out.println(cart);
            
            String discard = kb.nextLine(); //no idea why I need this
        }
        
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        System.out.println("\nPlease pay " + fmt.format(cart.getTotalPrice()));
    }
}