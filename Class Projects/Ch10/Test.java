public class Test //how original!
{
    public static void main(String[] args)
    {
        String name1 = new String("The quick brown fox");
        String name2 = name1;
        String s1 = name1.substring(1);
        System.out.println(s1);
        System.out.println(name1.length());
        System.out.println(name1.indexOf('b'));
        System.out.println(name1.charAt(5));
    }
}