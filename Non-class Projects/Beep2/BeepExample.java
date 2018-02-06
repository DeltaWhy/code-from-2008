import com.sun.jna.Library;
import com.sun.jna.Native;
 
/** Simple example of Windows native library declaration and usage. */
public class BeepExample {
    public static int[] freqs = {262, 277, 294, 311, 330, 
        349, 370, 392, 415, 440, 466, 494, 523};
   public interface Kernel32 extends Library {
       // FREQUENCY is expressed in hertz and ranges from 37 to 32767
       // DURATION is expressed in milliseconds
       public boolean Beep(int FREQUENCY, int DURATION);
       public void Sleep(int DURATION);
   }
 
   public static void playString(String song)
   {
    Kernel32 lib = (Kernel32) Native.loadLibrary("kernel32", 
           Kernel32.class);
       String[] notes = song.split(" ");
       for (String s:notes)
       {
           String note = "R";
           String value = "4";
           if (s.length() == 2)
           {
               note = s.substring(0,1);
               value = s.substring(1,2);
            } else if (s.length() == 3) {
                note = s.substring(0,2);
                value = s.substring(2,3);
            }
            String[] names = {"C","C#","D","D#","E","F","F#","G","G#","A",
                "A#","B","C."};
            for (int i = 0; i < names.length; i++)
            {
                if (note.equalsIgnoreCase(names[i]))
                    lib.Beep(freqs[i],2000/Integer.valueOf(value));
            }
            if (note.equalsIgnoreCase("R"))
                lib.Sleep(2000/Integer.valueOf(value));
        }
    }
    public static void twinkle()
    {
        playString("C4 C4 G4 G4 A4 A4 G4 R4 " +
        "F4 F4 E4 E4 D4 D4 C4 R4 " +
        "G4 G4 F4 F4 E4 E4 D4 R4 " +
        "G4 G4 F4 F4 E4 E4 D4 R4 " +
        "C4 C4 G4 G4 A4 A4 G4 R4 " +
        "F4 F4 E4 E4 D4 D4 C4 R4 ");
    }
    public static void mario()
    {
        playString("G8 G8 R8 G8 E4 G8 R8 C.2 C2");
    }
   public static void main(String[] args)
   {
       mario();
   }
}
