
/**
 * Strong crypto - the algorithm is simple enough to fit on a napkin.
 * 
 * @author Michael Limiero
 * @version (a version number or a date)
 */
import java.io.*;
public class CipherSaber
{
    public static void encryptFile(String filename) throws IOException
    {
        encryptFile(filename,false);
    }
    public static void encryptFile2(String filename) throws IOException
    {
        encryptFile(filename,true);
    }
    public static void encryptFile(String filename, boolean promptN) throws IOException
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);
        int n = 1;
        
        System.out.print("Key: ");
        String keyStr = kb.next();
        if (promptN)
        {
            System.out.print("N: ");
            n = kb.nextInt();
        }
        
        encryptFile2(filename, keyStr, n);
    }
    public static void encryptFile(String filename, String keyStr) throws IOException
    {
        encryptFile2(filename, keyStr, 1);
    }
    public static void encryptFile2(String filename, String keyStr, int n) throws IOException
    {
        File infile = new File(filename);
        FileInputStream fis = new FileInputStream(infile);
        byte[] pt = new byte[(int)infile.length()];
        fis.read(pt);
        fis.close();

        byte[] key = keyStr.getBytes();
        byte[] iv = new byte[10];
        byte[] rand = new byte[10];
        for (int i=0; i < 10; i++)
            rand[i] = (byte)(Math.random()*256);
        iv = RC4.cipher(rand,iv); //this gives better randomness and therefore more security
        
        byte[] k = new byte[key.length + 10];
        System.arraycopy(key,0,k,0,key.length);
        System.arraycopy(iv,0,k,key.length,10);
        
        String newFilename;
        if (n > 1) { newFilename = filename + ".cs2"; }
        else { newFilename = filename + ".cs1"; }
        File outfile = new File(newFilename);
        outfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outfile,false);
        fos.write(iv);
        fos.write(RC4.cipher(k,pt,n));
        fos.close();
    }
    public static String encryptMessage2(String message, String keyStr, int n)
    {
        byte[] pt = message.getBytes();
        byte[] ct = new byte[pt.length];
        byte[] o = new byte[pt.length + 10];

        byte[] key = keyStr.getBytes();
        byte[] iv = new byte[10];
        byte[] rand = new byte[10];
        for (int i=0; i < 10; i++)
            rand[i] = (byte)(Math.random()*256);
        iv = RC4.cipher(rand,iv); //this gives better randomness and therefore more security
        
        byte[] k = new byte[key.length + 10];
        System.arraycopy(key,0,k,0,key.length);
        System.arraycopy(iv,0,k,key.length,10);
        
        ct = RC4.cipher(k,pt,n);
        System.arraycopy(iv,0,o,0,10);
        System.arraycopy(ct,0,o,10,ct.length);
        return AArmor.aArmor(o);
    }
        
    public static void decryptFile(String filename) throws IOException
    {
        decryptFile(filename, false);
    }
    public static void decryptFile2(String filename) throws IOException
    {
        decryptFile(filename, true);
    }
    public static void decryptFile(String filename, boolean promptN) throws IOException
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);
        int n = 1;
        
        System.out.print("Key: ");
        String keyStr = kb.next();
        if (promptN)
        {
            System.out.print("N: ");
            n = kb.nextInt();
        }
        
        decryptFile2(filename, keyStr, n);
    }
    public static void decryptFile(String filename, String keyStr) throws IOException
    {
        decryptFile2(filename, keyStr, 1);
    }
    public static void decryptFile2(String filename, String keyStr, int n) throws IOException
    {
        File infile = new File(filename);
        FileInputStream fis = new FileInputStream(infile);
        byte[] pt = new byte[(int)infile.length()];
        byte[] pt2 = new byte[pt.length-10];
        fis.read(pt);
        fis.close();

        byte[] key = keyStr.getBytes();
        byte[] iv = new byte[10];
        System.arraycopy(pt,0,iv,0,10);
        System.arraycopy(pt,10,pt2,0,pt.length-10);
       
        byte[] k = new byte[key.length + 10];
        System.arraycopy(key,0,k,0,key.length);
        System.arraycopy(iv,0,k,key.length,10);
        
        File outfile = new File(filename.substring(0,filename.length()-4));
        outfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outfile,false);
        fos.write(RC4.cipher(k,pt2,n));
        fos.close();
    }
    public static String decryptMessage2(String message, String keyStr, int n)
    {
        byte[] pt = AArmor.deArmor(message);
        byte[] pt2 = new byte[pt.length-10];

        byte[] key = keyStr.getBytes();
        byte[] iv = new byte[10];
        System.arraycopy(pt,0,iv,0,10);
        System.arraycopy(pt,10,pt2,0,pt.length-10);
       
        byte[] k = new byte[key.length + 10];
        System.arraycopy(key,0,k,0,key.length);
        System.arraycopy(iv,0,k,key.length,10);
        
        return new String(RC4.cipher(k,pt2,n));
    }
    /*public static boolean rc4Test()
    {
        String[] keys = { "24g3","24g3","5ybdt" };
        String[] ciphertexts = { "24z0","24z2","5ybu8" };
        String[] plaintexts = { "nhnW","nhnU","XJrkp" };

        for (int i = 0; i < keys.length; i++)
        {
            byte[] ct = ciphertexts[i].getBytes();
            byte[] pt = new byte[ciphertexts[i].length()];
            byte[] key = keys[i].getBytes();
            
            pt = RC4.cipher(key,ct);
            
            String k = new String(key);
            String ciph = new String(ct);
            String ans = new String(pt);
            System.out.println("RC4(" + k + ", " + ciph + ") == " + ans);
            if (!ans.equals(plaintexts[i])) return false;
        }
        return true;
    }
    public static void csTest()
    {
        String message = "ba9ab4cffb7700e618e382e8fcc5ab9813b1abc436ba7d5cdea1a31fb72fb5763c44cfc2ac77afee19ad";
        //String message = "6f6d0babf3aa6719031530edb677ca74e0089dd0e7b8854356bb1448e37cdbefe7f3a84f4f5fb3fd";
        byte[] m1 = AArmor.deArmor(message);
        byte[] iv = new byte[10];
        byte[] m = new byte[m1.length - 10];
        
        byte[] o = new byte[m.length];
        System.arraycopy(m1,0,iv,0,10);
        System.arraycopy(m1,10,m,0,m.length);
        
        byte[] key = "asdfg".getBytes();
        byte[] k = new byte[key.length + 10];
        System.arraycopy(key,0,k,0,key.length);
        System.arraycopy(iv,0,k,key.length,10);
        
        int n = 10;
        
        o = RC4.cipher(k,m,n);
        
        String ans = new String(o);
        System.out.println(ans);
    }*/

}
