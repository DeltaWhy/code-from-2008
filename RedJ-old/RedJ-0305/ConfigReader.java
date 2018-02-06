//Michael Limiero
import java.io.*;
import java.nio.*;
import java.util.Hashtable;
public class ConfigReader
{
    private File configFile;
    private FileReader fileReader;
    public ConfigReader(File configFile) throws IOException
    {
        this.configFile = configFile;
        this.fileReader = new FileReader(configFile);
    }
    public ConfigReader(String fileName) throws IOException
    {
        this.configFile = new File(fileName);
        this.fileReader = new FileReader(configFile);
    }
    public Hashtable read() throws IOException
    {
        String s = "";
        char[] buf = new char[256];
        while (fileReader.read(buf) > 0)
        s += new String(buf);
        s = s.trim();
        String[] lines = s.split("\n");
        Hashtable ht = new Hashtable(lines.length);
        for (int i = 0; i < lines.length; i++)
        {
            lines[i] = lines[i].trim();
            if (lines[i].indexOf("=") < 0) continue;
            String[] pair = lines[i].split("=");
            if (pair[0] != null) ht.put(pair[0].toUpperCase(), pair[1]);
        }
        return ht;
    }
    public void close() throws IOException
    {
        fileReader.close();
    }
}