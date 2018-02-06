// ****************************************************************
//   Guess.java
//
//   Play a game where the user guesses a number from 1 to 10
//          
// ****************************************************************
import java.util.Scanner;

public class Guess
{
    public static void main(String[] args)
    {
        int numToGuess;       //Number the user tries to guess
        int guess;            //The user's guess
        int numGuesses = 1;
        int tooHigh = 0, tooLow = 0;

    Scanner scan = new Scanner(System.in);

    //randomly generate the  number to guess
    numToGuess = (int)(Math.random()*10)+1;
    if (args.length == 1 && args[0].equals("cheat")) numToGuess = 5;
        //cheat if I tell it to!! Yay backdoors!
   
    //print message asking user to enter a guess
    System.out.print("Enter a guess, user (1 to 10): ");

    //read in guess
    guess = scan.nextInt();
                
    while (guess != numToGuess)  //keep going as long as the guess is wrong
        {
        //print message saying guess is wrong
        if (guess > 10 || guess < 1)
            System.out.println("1 to 10, moron!"); //not user-friendly...
        else if (guess > numToGuess)
        {
            System.out.println("Too high. You don't win.");
            tooHigh++;
        } else {
            System.out.println("Too low. You don't win.");
            tooLow++;
        }
        //get another guess from the user
        System.out.print("Guess again: ");
        guess = scan.nextInt();
        numGuesses++;
    }

    //print message saying guess is right
    System.out.println("Fine, you win. And it only took you " + 
        numGuesses + " tries.");
    System.out.println(tooHigh + " guesses were too high, and " +
        tooLow + " guesses were too low (as if you cared).");
    }
}
