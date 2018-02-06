//Michael Limiero
public class RightTriangle extends Triangle
{
    public RightTriangle(double a)
    {
        super(a);
    }
    public double getPerimeter()
    {
        return (2 + Math.sqrt(2.0)) * side;
    }
    public double getArea()
    {
        return side * side / 2;
    }
}