//Michael Limiero
//
// Grades.java
//
// Use Student class to get test grades for two students and compute averages

public class Grades
{
    public static void main(String[] args)
    {
        //create student1, "Mary"
        Student student1 = new Student("Mary");
        //create student2, "Mike"
        Student student2 = new Student("Mike");
        
        //input grades for Mary
        student1.inputGrades();
        System.out.println();
        //input grades for Mike
        student2.inputGrades();
        System.out.println();
        
        //print information for Mary
        System.out.println(student1);
        System.out.println();
        //print information for Mike
        System.out.println(student2);
    }
}