public class DieTest
{
    public static void main(String[] args)
    {
        Die die = new Die();
        for (int j = 0; j < 10; j++)
        {
            for (int i = 0; i < 5; i++) //run this 50 times...
            {
                die.roll();
                System.out.print(die.getNumDots() + "\t");
            }
            System.out.println();
        }
    }
}
