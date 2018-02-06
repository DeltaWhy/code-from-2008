
/**
 * Write a description of class TestCar here.
 * 
 * @author Michael Limiero
 * @version 2.718281828459...
 */
public class TestCar
{
    public static void main (String[] args)
    {
        Car car1 = new Car(4,2,"marrón");
        Hummer car2 = new Hummer (4,4,"gunmetal",208);
        //it comes in two colors: black and gunmetal
        
        System.out.println(car1);
        System.out.println(car2);
        
        car2.paintJob("black");
        System.out.println(car2);
        System.out.println(car1.honk());
        System.out.println(car2.honk());
    }
}