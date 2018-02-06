// ****************************************************************
//   LoveCS.java
//
//   Use a while loop to print many messages declaring your 
//   passion for computer science
// ****************************************************************
//Michael Limiero

import java.util.Scanner;
public class LoveCS
{
    public static void main(String[] args)
    {
    Scanner kb = new Scanner(System.in);
    int n;
    int count = 1;
    int sum = 0;
    System.out.print("How many times do you want to print the message? ");
    n = kb.nextInt();

    while (count <= n){
        System.out.println(count + " I love Computer Science!!");
        sum += count;
        count++;
    }
    System.out.print("Printed this message " + n + " times.\t");
    System.out.println("The sum of the numbers from 1 to " + n + " is " + 
        sum + ".");
    }
}