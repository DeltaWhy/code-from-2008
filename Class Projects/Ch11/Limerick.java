public class Limerick extends Poem
{
    //5 lines: 9 9 6 6 9
    public int numLines()
    {
        return 5;
    }
    public int getSyllables(int k)
    {
        if (k == 1 || k == 2 || k == 5) return 9;
        if (k == 3 || k == 4) return 6;
        return -1; //error
    }
}