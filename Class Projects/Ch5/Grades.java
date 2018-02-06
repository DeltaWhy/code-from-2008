/**
 * Prints student names and grades, as well as
 * a funky header-type thing.
 * 
 * @author Michael Limiero
 * @version 3.1415926535897932384626433
 */
public class Grades
{
    public static void main(String[] args)
    {
        String header1 = "";
        for (int i = 0; i < 19; i++)
            header1 += "/";
        for (int i = 0; i < 19; i++)
            header1 += "\\";
        String header2 = "==";
        for (int i = 0; i < 10; i++)
            header2 += " ";
        header2 += "Student Points";
        for (int i = 0; i < 10; i++)
            header2 += " ";
        header2 += "==";
        String header3 = "";
        for (int i = 0; i < 19; i++)
            header3 += "\\";
        for (int i = 0; i < 19; i++)
            header3 += "/";
        
        String[] names = {"Joe","William","Mary Sue","Trogdor","Sauron"};
        int[] labs = {43,50,39,-23,1000};
        double[] bonus = {7,8,10,2.3746,1000};
        
        System.out.println(header1);
        System.out.println(header2);
        System.out.println(header3);
        System.out.println();
        System.out.println("Name\t\tLab\tBonus\tTotal");
        System.out.println("----\t\t---\t-----\t-----");
        for (int i = 0; i < names.length; i++)
        {
            String s = names[i] + "\t";
            if (i != 2) s += "\t";
            s += labs[i] + "\t";
            s += bonus[i] + "\t";
            s += (labs[i]+bonus[i]);
            System.out.println(s);
        }
    }
}
