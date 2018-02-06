//Michael Limiero
//
//Student.java
//
// Define a student class that stores name, score on test 1,
// and score on test 2. Methods prompt for and read in grades,
// compute the average, and print student's name.

import java.util.Scanner;
public class Student
{
    //declare instance data
    private String name;
    private double test1=-1, test2=-1;
    private Scanner kb;
    
    //constructor
    public Student(String name)
    {
        this.name = name;
        this.kb = new Scanner(System.in);
    }
    
    //inputGrades: prompt for and read in student's grades for test1 and test2.
    //Use name in prompts, e.g., "Enter Joe's score for test1".
    public void inputGrades()
    {
        do
        {
            System.out.print("Enter " + name + "'s percent score for Test 1: ");
            test1 = kb.nextDouble();
            if (test1 < 0 || test1 > 100) System.out.println("Invalid grade, " +
                "please try again.");
        } while (test1 < 0 || test1 > 100);
        do
        {
            System.out.print("Enter " + name + "'s percent score for Test 2: ");
            test2 = kb.nextDouble();
            if (test2 < 0 || test2 > 100) System.out.println("Invalid grade, " +
                "please try again.");
        } while (test2 < 0 || test2 > 100);
    }
    
    //getAverage: compute and return the student's test average
    public double getAverage()
    {
        if (test1 < 0 || test2 < 0)
        {
            System.out.println("Warning: one or more grades have not been entered.");
            return -1;
        }
        return (test1 + test2) / 2;
    }
    
    //getName: returns the student's name
    public String getName()
    {
        return name;
    }
    
    //toString: returns a string containing the student name and test scores
    //and average
    public String toString()
    {
        return "Name: " + name + "\tTest1: " + test1 + "\tTest2: " +
            test2 + "\tAverage: " + getAverage();
    }
}