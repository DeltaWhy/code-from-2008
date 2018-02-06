//Michael Limiero
// ****************************************************************
// DogTest.java
//
// A simple test class that creates a Dog and makes it speak.
//          
// ****************************************************************

public class DogTest
{
    public static void main(String[] args)
    {
	Dog yorky = new Yorkshire("Yorky");    //polymorphic variable...
    System.out.println(yorky.getName() + " says " + yorky.speak());
    Dog lab = new Labrador("Dexter", "red");    //as in Dexter's Lab...
    System.out.println(lab.getName() + " says " + lab.speak());
    System.out.println("Average breed weight:");
    System.out.println(yorky.getName() + ": " + yorky.avgBreedWeight());
    System.out.println(lab.getName() + ": " + lab.avgBreedWeight());
    }
}
