//Michael Limiero
//Created with RedJ v0.00001 alpha
public class Rectangle
{
    private int width, height;
    public Rectangle(int width, int height)
    {
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }
    public Rectangle(int side)
    {
        side = Math.abs(side);
        this.width = side;
        this.height = side;
    }
    public Rectangle()
    {
        this.width = 1;
        this.height = 1;
    }
    public boolean isSquare()
    {
        return (width == height);
    }
    public void quadratize()
    {
        int area = width * height;
        double side = Math.sqrt(area);
        int roundedSide = (int)(side + 0.5);
        width = roundedSide;
        height = roundedSide;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public String toString()
    {
        return "Width: " + width + "\t\tHeight: " + height;
    }
}
