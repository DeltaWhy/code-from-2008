// ****************************************************************
//   Rock.java
//
//   Play Rock, Paper, Scissors with the user
//          
// ****************************************************************
//Michael Limiero
import java.util.Scanner;

public class Rock
{
    public static void main(String[] args)
    {
    String personPlay;    //User's play -- "R", "P", or "S"
    String computerPlay;  //Computer's play -- "R", "P", or "S"
    int computerInt;      //Randomly generated number used to determine
                          //computer's play
    Scanner kb = new Scanner(System.in);
    String response = "";
    
    while (!response.equals("no") && !response.equals("n"))
    {
        do
        {
            //Get player's play -- note that this is stored as a string
            System.out.println("Enter your play: R, P, or S");
            personPlay = kb.next().toUpperCase();
            //Make player's play uppercase for ease of comparison
        } while (!(personPlay.equals("R") || personPlay.equals("P") || 
            personPlay.equals("S")));
        
        
        //Generate computer's play (0,1,2)
        computerInt = (int)(3*Math.random()); //looks like 2 but should be 3
        
        //Translate computer's randomly generated play to string
        switch (computerInt){
            case 0:
                computerPlay = "R";
                break;
            case 1:
                computerPlay = "P";
                break;
            default: //should only be 2
                computerPlay = "S";
        }
        
        //Print computer's play
        System.out.println("Computer play is " + computerPlay);
        
        //See who won.  Use nested ifs instead of &&.
        if (personPlay.equals(computerPlay))  
            System.out.println("It's a tie!");
        else if (personPlay.equals("R"))
            if (computerPlay.equals("S"))
                System.out.println("Rock crushes scissors.  You win!!");
            else
                System.out.println("Paper suffocates rock.  You lose!!");
        else if (personPlay.equals("P"))
            if (computerPlay.equals("R"))
                System.out.println("Paper suffocates rock.  You win!!");
            else
                System.out.println("Scissors cut paper.  You lose!!");
        else //"S"
            if (computerPlay.equals("R"))
                System.out.println("Rock crushes scissors.  You lose!!");
            else
                System.out.println("Scissors cut paper.  You win!!");

        System.out.print("Play again? ");
        response = kb.next().toLowerCase();
    }
    }
}
