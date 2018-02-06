//Michael Limiero
/*
   This class implements a vendor that sells one kind of items.
   A vendor carries out sales transactions.
*/

public class Vendor
{
  // Fields:
  private final int PRICE;
  private int stock;
  private int deposit;
  private int change;
  private static double totalSales = 0;
  //  Constructor
  //  Parameters:
  //    int price of a single item in cents
  //    int number of items to place in stock
  public Vendor (int price, int num)
  {
    this.PRICE = price;
    this.stock = num;
    this.deposit = 0;
    this.change = 0;
  }

  //  Sets the quantity of items in stock.
  //  Parameters:
  //    int number of items to place in stock
  //  Return:
  //    None
  public void setStock (int num)
  {
      stock = num;
  }

  //  Returns the number of items currently in stock.
  //  Parameters:
  //    None
  //  Return:
  //    int number of items currently in stock
  public int getStock ()
  {
    return stock;
  }

  //  Adds a specified amount (in cents) to the deposited amount.
  //  Parameters:
  //    int number of cents to add to the deposit
  //  Return:
  //    None
  public void addMoney (int amt)
  {
      deposit += amt;
  }

  //  Returns the currently deposited amount (in cents).
  //  Parameters:
  //    None
  //  Return:
  //    int number of cents in the current deposit
  public int getDeposit ()
  {
    return deposit;
  }

  //  Implements a sale.  If there are items in stock and
  //  the deposited amount is greater than or equal to
  //  the single item price, then adjusts the stock
  //  and calculates and sets change and returns true;
  //  otherwise refunds the whole deposit (moves it into change)
  //  and returns false.
  //  Parameters:
  //    None
  //  Return:
  //    boolean successful sale (true) or failure (false)
  public boolean makeSale ()
  {
      if (stock > 0 && deposit >= PRICE)
      {
          stock--;
          change = deposit - PRICE;
          deposit = 0;
          totalSales += (PRICE / 100.0);
          return true;
      } else {
          change = deposit;
          deposit = 0;
          return false;
      }
  }

  //  Returns and zeroes out the amount of change (from the last
  //  sale or refund).
  //  Parameters:
  //    None
  //  Return:
  //    int number of cents in the current change
  public int getChange ()
  {
    int cents = change;
    change = 0;
    return cents;
  }
  public static double getTotalSales()
  {
      double temp = totalSales;
      totalSales = 0;
      return temp;
  }
}
