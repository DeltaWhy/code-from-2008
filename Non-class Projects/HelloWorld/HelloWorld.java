
/**
 * This is a completely pointless program to print junk to the screen.
 * 
 * @author Michael Limiero
 * @version 3.14159265358979323842624338
 */
public class HelloWorld
{
    public static void main(String[] args)
    {
        char[] theString = "Gdkkn+ Vnqkc ".toCharArray();
        theString[6]--;
        for (int i=0; i < theString.length; i++)
        {
            theString[i]++;
        }
        System.out.println(theString);
    }
}
