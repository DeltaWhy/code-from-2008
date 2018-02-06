//Michael Limiero
public class TestRectangle
{
    public static void main(String[] args)
    {
        Rectangle r1 = new Rectangle(20,5);
        Rectangle r2 = new Rectangle(5);
        Rectangle r3 = new Rectangle();
        System.out.println("r1 - " + r1);
        System.out.println("r2 - " + r2);
        System.out.println("r3 - " + r3);
        System.out.println("calling isSquare()");
        System.out.println("r1 - " + r1.isSquare());
        System.out.println("r2 - " + r2.isSquare());
        System.out.println("r3 - " + r3.isSquare());
        System.out.println("calling r1.quadratize()");
        r1.quadratize();
        System.out.println("r1 - " + r1);
    }
}
