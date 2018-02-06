
/**
 * Doesn't really do anything, but pretends to.
 * 
 * @author Michael Limiero
 * @version 0.00000000001
 */
public class Variable
{
    static int num1, num2;
    static double num3;
    static String label = "NOT text";
    
    public static void main(String[] args)
    {
        num1 = 23;
        num2 = num1 + 100;
        num3 = (num1 + num2) / 2.0;
        
        System.out.println("The value of num1 is: " + num1);
        System.out.println("The value of num2 is: " + num2);
        System.out.println("The average is: " + num3);
        System.out.println("The label object contains: " + label + "!");
    }
}
