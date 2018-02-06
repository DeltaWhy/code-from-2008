
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Arrays;
public class Test
{
    public static boolean test()
    {
        String str = "Katana-Stego";
        byte[] b1 = str.getBytes();
        byte[] b2 = "Katana-Stego".getBytes();
        return Arrays.equals(b1,b2);
    }
}
