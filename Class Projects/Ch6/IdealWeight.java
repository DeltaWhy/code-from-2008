/**
 * Tells people whether they are too fat or not.
 * 
 * @author Michael Limiero
 * @version Q.5
 */
import java.util.Scanner;
public class IdealWeight
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in); //love the Scanner, wish I had
                                                // learned it like a year ago
        int feet, inches, realWeight;
        String gender;
        boolean male = false;
        System.out.println("Please enter your height in feet and inches");
        System.out.println("Round to the nearest inch");
        System.out.print("Feet: ");
        feet = kb.nextInt();
        System.out.print("Inches: ");
        inches = kb.nextInt();
        System.out.print("Enter your weight to the nearest pound: ");
        realWeight = kb.nextInt();
        System.out.print("Enter m for male or f for female: ");
        gender = kb.next(); //probably won't work
        System.out.println();
        
        if (gender.substring(0,1).equalsIgnoreCase("m")) male = true;
        
        inches = feet*12 + inches;
        inches -= 5*12; //height over 5 feet
        int femaleWeight, maleWeight;
        femaleWeight = 100 + 5*inches;
        maleWeight = 106 + 6*inches;
        
        System.out.println("For a female, your ideal weight is " + 
            femaleWeight + " pounds.");
        System.out.println("Your okay range is from " + 
            (femaleWeight - 0.15*femaleWeight) + " to " +
            (1.15*femaleWeight) + " pounds.");
        System.out.println();
        
        System.out.println("For a male, your ideal weight is " + 
            maleWeight + " pounds.");
        System.out.println("Your okay range is from " + 
            (maleWeight - 0.15*maleWeight) + " to " +
            (1.15*maleWeight) + " pounds.");
        System.out.println();
        
        int iWeight=0; //coming soon from Apple
        //the compiler complains if I don't set it to 0
        // even though it actually does get set. Probably I should have
        // used "else".
        if (male) iWeight = maleWeight;
        if (!male) iWeight = femaleWeight; //yes I could use "else"
                                            //but I'm lazy.
        
        String criticism = "";
        if (realWeight > iWeight*1.15) criticism = "You're fat!! Fatty!";
        if (realWeight < iWeight*0.85) criticism = "You're skinny!! Eat a burger!";
        if (realWeight == iWeight) criticism = "You're perfect! You probably lied to me!";
        if (inches < 0) criticism += "\nYou're short! Buy taller shoes!";
        
        if (!criticism.equals("")) System.out.println(criticism);
    }
}
