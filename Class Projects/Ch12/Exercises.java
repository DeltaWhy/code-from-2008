//Michael Limiero
import java.util.ArrayList;
public class Exercises
{
    public static ArrayList<String> reverse(ArrayList<String> a)
    {
        ArrayList<String> b = new ArrayList<String>(a.size());
        for (String s : a)
            b.add(0,s);
        return b;
    }
    public static void removeSmallest(ArrayList<Integer> a)
    {
        Integer smallest = Integer.MAX_VALUE;
        for (Integer i : a)
            if (i.compareTo(smallest) < 0)
                smallest = i;
        a.remove(smallest);
    }
    public static void main(String[] args)
    {
        ArrayList<String> test = new ArrayList<String>();
        test.add("some"); test.add("words"); test.add("for"); test.add("testing");
        System.out.println(test);
        System.out.println(reverse(test));
        System.out.println();
        ArrayList<Integer> test2 = new ArrayList<Integer>();
        test2.add(123); test2.add(456); test2.add(78); test2.add(910);
        System.out.println(test2);
        removeSmallest(test2);
        System.out.println(test2);
    }
}