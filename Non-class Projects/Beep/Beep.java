public class Beep
{
/** @dll.import("KERNEL32") */
static native boolean Beep( int freq, int dur );
public static void main(String[] args)
{
Beep(500,1000);
}
}