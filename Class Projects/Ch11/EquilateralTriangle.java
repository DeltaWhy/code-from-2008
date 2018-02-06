//Michael Limiero
public class EquilateralTriangle extends Triangle
{
    public EquilateralTriangle(double a)
    {
        super(a);
    }
    public double getArea()
    {
        return Math.sqrt(3) / 4 * side * side;
    }
    public double getPerimeter()
    {
        return 3 * side;
    }
}