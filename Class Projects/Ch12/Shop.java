//Michael Limiero
// ***************************************************************
//   Shop.java
//
//   Uses the Item class to create items and add them to a shopping
//   cart stored in an ArrayList.
// ***************************************************************
// Not to be confused with Shopping.java or ShoppingCart.java.

import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat;

public class Shop
{
    public static void main (String[] args)
    {
	ArrayList<Item> cart = new ArrayList<Item>();
	Scanner kb = new Scanner(System.in);
	//Item item;
	String itemName;
	double itemPrice;
	int quantity;
    double totalPrice = 0;

	String keepShopping = "y";

	do 
	    {
		System.out.print ("Enter the name of the item: "); 
		itemName = kb.nextLine();

		System.out.print ("Enter the unit price: ");
		itemPrice = kb.nextDouble();

		System.out.print ("Enter the quantity: ");
		quantity = kb.nextInt();

		// *** create a new item and add it to the cart
        cart.add(new Item(itemName, itemPrice, quantity));

		// *** print the contents of the cart object using a loop
        totalPrice = 0;
        for (Item i : cart)
        {
            System.out.println(i);
            totalPrice += i.getPrice() * i.getQuantity();
        }
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        System.out.println("Total price: " + fmt.format(totalPrice) + "\n");

		kb.nextLine(); //consume remaining enter from previous nextInt()
		System.out.print ("Continue shopping (y/n)? ");
		keepShopping = kb.nextLine();
	    }
	while (keepShopping.equals("y"));

    }
}