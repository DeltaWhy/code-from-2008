
/**
 * Hide files inside other files.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.Arrays;
public class Stego
{
    public static void insertFile(String hostName, String guestName) throws IOException
    {
        if (hostName.equals(guestName)) return; //to avoid accidentally deleting stuff
        File host = new File(hostName);
        FileOutputStream fos = new FileOutputStream(host, true); //append mode
        File guest = new File(guestName);
        FileInputStream fis = new FileInputStream(guest);
        
        //header: 255,"Katana-Stego",<header-length>,<version>,<reserved>,<name>
        fos.write(255);
        fos.write("Katana-Stego".getBytes());
        fos.write(guestName.length()+2);
        fos.write(0); //version
        fos.write(0); //for future use
        fos.write(guestName.getBytes());
        
        byte[] a = new byte[(int)guest.length()];
        fis.read(a);
        fos.write(a);
        
        fis.close();
        fos.close();
        
        guest.delete();
    }
    public static void extractFile(String hostName) throws IOException
    {
        File host = new File(hostName);
        FileInputStream fis = new FileInputStream(host);
        
        //header: 255,"Katana-Stego",<header-length>,<version>,<reserved>,<name>
        
        long headerPosition = findHeader(fis);
        if (headerPosition < 0) return; //find the header or die!
        
        //we are at the end of "Katana-Stego"
        int length = fis.read() - 2; //length of filename
        int version = fis.read(); //currently useless
        int discard = fis.read(); //for future use - flags of some sort...
        if (discard != 0)
        {
            System.out.println("Version error");
            fis.close();
            return;
        }
        
        byte[] bName = new byte[length];
        fis.read(bName);
        String guestName = new String(bName);
        File guest = new File(guestName);
        FileOutputStream fos = new FileOutputStream(guest);
        
        //Copy file
        byte[] guestData = new byte[fis.available()];
        fis.read(guestData);
        fos.write(guestData);
        fos.close();
        
        //Remove from end of original: close host, copy to temp, then copy
        fis.close();
        File temp = new File(hostName + ".qqq");
        copyFile(host,temp);
        fis = new FileInputStream(temp);
        fos = new FileOutputStream(host);
        long blocks = headerPosition / 1024;
        int remainder = (int)(headerPosition % 1024);
        byte[] buf = new byte[1024];
        for (int i = 0; i < blocks; i++)
        {
            fis.read(buf);
            fos.write(buf);
        }
        for (int i = 0; i < remainder; i++)
            fos.write(fis.read()); //one byte at a time
        //now we are before the 255 of the header
        //we can quit
        fos.close();
        fis.close();
        temp.delete();
        return;
       
        /*int next = 0;
        while (next != -1)
        {
            next = fis.read();
            if (next != 255)
            {
                fos.write(next);
            } else {
                String headerString = "Katana-Stego";
                byte[] read = new byte[headerString.length()];
                fis.read(read);
                if (Arrays.equals(read,headerString.getBytes()))
                {
                    fos.close();
                    fis.close();
                    temp.delete();
                    return;
                } else {
                    fos.write(255);
                    fos.write(read);
                }
            }
        }
        fos.close();
        fis.close();
        temp.delete();*/
    }
    private static long findHeader(FileInputStream fis) throws IOException
    {
                //search for header
        boolean stop = false;
        long position = -1;
        while (!stop)
        {
            int next = 0;
            while (next != -1 && next != 255)
                next = fis.read();
            if (next == -1) 
            {
                fis.close();
                return -1; //didn't find the header
            }
            position = fis.getChannel().position();
            String headerString = "Katana-Stego";
            byte[] read = new byte[headerString.length()];
            fis.read(read);
            if (Arrays.equals(read,headerString.getBytes()))
            {
                stop = true;
            } else fis.getChannel().position(position); //go back
        }
        return position - 1; //before the 255 byte
    }
    private static void copyFile(File source, File dest) throws IOException
    {
        if(!dest.exists())
            dest.createNewFile();
        InputStream in = null;
        OutputStream out = null;
        in = new FileInputStream(source);
        out = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len;
        while((len = in.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
