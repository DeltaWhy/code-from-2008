import java.util.Scanner;
public class Quiz
{
    public static void main(String[] args)
    {
        int numQuestions;
        String[] key;
        int numCorrect;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("GradeBot v0.2\n");
        System.out.print("How many questions are on this test? ");
        numQuestions = kb.nextInt();
        key = new String[numQuestions];
        
        System.out.println("Enter the answer key on a single line:");
        for (int i = 0; i < numQuestions; i++)
            key[i] = kb.next().trim().toLowerCase();
        
        do
        {
            System.out.println("\nEnter the student's answers on a single line:");
            numCorrect = 0;
            for (String s : key)
                if (s.equals(kb.next().trim().toLowerCase())) numCorrect++;
            
            System.out.println("\nNumber correct: " + numCorrect + " of " + numQuestions);
            System.out.println("Percentage: " + (((double)numCorrect) / numQuestions * 100) + "%");
            System.out.print("\nGrade another test (y/n)? ");
        } while (kb.next().toLowerCase().trim().equals("y"));
    }
}