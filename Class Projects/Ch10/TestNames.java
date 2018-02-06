//Michael Limiero
import java.util.Scanner;
public class TestNames
{
public static void main(String[] args)
{
    Name n1, n2;
    String first, middle, last;
    Scanner kb = new Scanner(System.in);
    
    System.out.print("First: ");
    first = kb.next();
    System.out.print("Middle: ");
    middle = kb.next();
    System.out.print("Last: ");
    last = kb.next();
    n1 = new Name(first,middle,last);
    
    System.out.print("First: ");
    first = kb.next();
    System.out.print("Middle: ");
    middle = kb.next();
    System.out.print("Last: ");
    last = kb.next();
    n2 = new Name(first,middle,last);
    
    System.out.println(n1.firstMiddleLast());
    System.out.println(n2.firstMiddleLast());
    System.out.println(n1.lastFirstMiddle());
    System.out.println(n2.lastFirstMiddle());
    System.out.println(n1.initials());
    System.out.println(n2.initials());
    System.out.println(n1.length());
    System.out.println(n2.length());
    
    System.out.print("Are names equal? ");
    System.out.print(n1.equals(n2));
}
}