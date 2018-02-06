import java.util.Scanner;
import java.util.ArrayList;
public class Test
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        
        int[] numbers;
        numbers = new int[5];
        String[] words = new String[3];
        double[] taxes = {2345.09, 345.67, 678.98, 89.95};
        
        System.out.println("the length of the numbers array is: " + numbers.length);
        
        numbers[0] = 86;
        numbers[1] = 125;
        numbers[2] = 247;
        numbers[3] = 12987;
        numbers[4] = 13;
        
        for (int i = 0; i < words.length; i++)
        {
            System.out.print("Enter a word: ");
            words[i] = kb.nextLine();
        }
        
        for (int i = 0; i < words.length; i++)
            System.out.println(words[i]);
        
        ArrayList<String> names = new ArrayList<String>();
        System.out.println("names is size " + names.size());
        names.add("Steve");
        System.out.println(names);
        names.add(0, "Paul");
        System.out.println("names is size " + names.size());
        System.out.println("names is empty? - " + names.isEmpty());
        names.set(1, "Steven");
        System.out.println(names);
    }
}
