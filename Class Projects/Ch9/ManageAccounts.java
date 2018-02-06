//Michael Limiero
// ****************************************************************
//   ManageAccounts.java
//
//   Use Account class to create and manage Sally and Joe's 
//   bank accounts       
// ****************************************************************

public class ManageAccounts
{
    public static void main(String[] args)
    {
    Account acct1, acct2;
    
    acct1 = new Account(1000, "Sally", 1111);
    acct2 = new Account(500, "Joe", 1112);
    
    acct2.deposit(100);
    System.out.println("Joe's new balance: " + acct2.getBalance());
    
    acct1.withdraw(50);
    System.out.println("Sally's new balance: " + acct1.getBalance());
    
    acct1.chargeFee();
    acct2.chargeFee();
    
    acct2.changeName("Joseph");
    
    acct1.printSummary();
    acct2.printSummary();
    }
}
