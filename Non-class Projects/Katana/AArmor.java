import java.io.*;
public class AArmor
{
    public static byte[] deArmor(String a)
    {
        byte[] o = new byte[a.length() / 2];
        for(int i = 0; i < a.length(); i+=2)
        {
            o[i/2] = (byte)(Integer.parseInt(a.substring(i,i+2),16));
        }
        return o;
    }
    public static String aArmor(byte[] d)
    {
        String a = "";
        for (int i = 0; i < d.length; i++)
        {
            String hex = Integer.toHexString(((int)d[i])&255);
            if (hex.length()<2) hex = "0" + hex;
            a += hex;
        }
        return a;
    }
    public static String aArmorFile(String filename) throws IOException
    {
        File ip = new File(filename);
        FileInputStream fis = new FileInputStream(ip);
        byte[] a = new byte[(int)ip.length()];
        fis.read(a);
        fis.close();
        return aArmor(a);
    }
    public static void deArmorFile(String filename, String message) throws IOException
    {
        File op = new File(filename);
        FileOutputStream fos = new FileOutputStream(op);
        byte[] a = deArmor(message.trim());
        fos.write(a);
        fos.close();
    }
    /*public static boolean testAArmor(String a)
    {
        return a.equalsIgnoreCase(aArmor(deArmor(a)));
        //byte[] m = {(byte)0x00,(byte)0x01,(byte)0x10,(byte)0xFF,(byte)0xCC};
        //return "000110FFCC".equalsIgnoreCase(aArmor(m));
    }*/
}
