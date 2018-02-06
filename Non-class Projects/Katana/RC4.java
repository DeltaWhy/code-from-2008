public class RC4
{
    public static byte[] cipher(byte[] key, byte[] pt)
    {
        return cipher(key,pt,1);
    }
    public static byte[] cipher(byte[] key, byte[] pt, int n)
    {
        //Setup
        byte[] state = new byte[256];
        byte[] ct = new byte[pt.length];
        //Initialize state array
        for (int i = 0; i < 256; i++) state[i] = (byte)i;
        
        //Key setup
        int j = 0;
        for (int k = 1; k <= n; k++) //for CS-2
        {
            for (int i = 0; i < 256; i++)
            {
                int a = btoi(state[i]);
                int b = btoi(key[i%key.length]);
                j = (j + a + b) & 255;
                byte temp = state[i];
                state[i] = state[j];
                state[j] = temp;
            }
        }
        
        //Ciphering
        int i = 0; j = 0;
        for (int k = 0; k < pt.length; k++)
        {
            i = (i + 1) & 255;
            int a = btoi(state[i]);
            j = (j + a) & 255;
            byte temp = state[i];
            state[i] = state[j];
            state[j] = temp;
            a = btoi(state[i]);
            int b = btoi(state[j]);
            ct[k] = (byte)(state[ (a + b) & 255 ] ^ pt[k]);
        }
        
        return ct;
    }
    public static int btoi(byte b)
    {
        return ((int)b) & 255;
    }
}
