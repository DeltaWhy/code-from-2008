//Michael Limiero
//*******************************************************
// Account.java
//
// A bank account class with methods to deposit to, withdraw from,
// change the name on, charge a fee to, and print a summary of the account.
//*******************************************************
//Comments removed to save paper
public class Account
{
  private double balance;
  private String name;
  private long acctNum;

  public Account(double initBal, String owner, long number)
  {
    balance = initBal;
    name = owner;
    acctNum = number;
  }

  public void withdraw(double amount)
  {
    if (balance >= amount)
       balance -= amount;
    else
       System.out.println("Insufficient funds");
  }

  public void deposit(double amount)
  {
    balance += amount;
  }

  public double getBalance()
  {
    return balance;
  }

  public void printSummary()
  {
    System.out.println("Name: " + name);
    System.out.println("Acct. #: " + acctNum);
    System.out.println("Balance: " + balance);
  }

  public double chargeFee()
  {
      if (balance < 1000) balance -= 10;
      return balance;
  }

  public void changeName(String newName)     
  {
      name = newName;
  }

}
