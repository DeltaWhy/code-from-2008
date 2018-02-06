//Michael Limiero
//Ex. 9
import java.util.Scanner;
public class Scrabble
{
    public static int computeScore(String word)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] scores = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,3,3,10,1,1,1,1,4,4,8,4,10};
        int score = 0;
        
        for (int i = 0; i < word.length(); i++)
        {
            String letter = word.substring(i,i+1);
            int pos = alphabet.indexOf(letter);
            if (pos == -1) continue; //ignore spaces, apostrophes, etc.
            score += scores[pos];
        }
        
        return score;
    }
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter a word to score: ");
        String word = kb.next();
        int score = computeScore(word);
        System.out.println("Your score is: " + score + "\n");
    }
}