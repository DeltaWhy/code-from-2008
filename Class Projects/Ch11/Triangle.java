//Michael Limiero
public abstract class Triangle
{
  protected double side;

  public Triangle(double a)
  {
    side = a;
  }

  public abstract double getPerimeter();
  
  public abstract double getArea();
  
  public double getRatio()
  {
    return getArea()/getPerimeter();
  }
  
  public static void main(String[] args)
  {
    Triangle equilateralTr = new EquilateralTriangle(12);
    Triangle rightTr = new RightTriangle(12);
    System.out.println("Equilateral " + equilateralTr.getRatio());
    System.out.println("Right isosceles " + rightTr.getRatio());
  }
}  
