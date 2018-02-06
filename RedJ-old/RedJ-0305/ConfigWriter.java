//Michael Limiero
import java.io.*;
import java.nio.*;
import java.util.Hashtable;
import java.util.Enumeration;
public class ConfigWriter
{
    private File configFile;
    private FileWriter fileWriter;
    public ConfigWriter(File configFile) throws IOException
    {
        this.configFile = configFile;
        this.fileWriter = new FileWriter(configFile);
    }
    public ConfigWriter(String fileName) throws IOException
    {
        this.configFile = new File(fileName);
        this.fileWriter = new FileWriter(configFile);
    }
    public void write(Hashtable ht) throws IOException
    {
        Enumeration e = ht.keys();
        while (e.hasMoreElements())
        {
            String key = (String)e.nextElement();
            String value = (String)ht.get(key);
            key = key.replace("\"","").replace("=","");
            value = value.replace("\"","").replace("=","");
            String pair = key + "=" + value + System.getProperty("line.separator");
            fileWriter.write(pair);
        }
    }
    public void close() throws IOException
    {
        fileWriter.close();
    }
}