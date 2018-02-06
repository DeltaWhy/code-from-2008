public abstract class Poem
{
    public abstract int numLines();
    public abstract int getSyllables(int k);
    public void printRhythm()
    {
        for (int i = 1; i <= numLines(); i++)
        {
            System.out.print("ta");
            for (int j = 2; j <= getSyllables(i); j++)
                System.out.print("-ta");
            System.out.println();
        }
    }
}