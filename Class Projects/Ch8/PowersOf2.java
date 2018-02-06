//Michael Limiero
// ****************************************************************
//   PowersOf2.java
//
//   Print out as many powers of 2 as the user requests
//          
// ****************************************************************
import java.util.Scanner;

public class PowersOf2{
    public static void main(String[] args){

        int n;        //How many powers of 2 to compute
        //let's not use 50 characters for a name when 1 works fine...
        int nextPowerOf2 = 1;    //Current power of  2
        int exponent;            //Exponent for current power of 2 -- this
                             //also serves as a counter for the loop
        Scanner scan = new Scanner(System.in);

    System.out.println("How many powers of 2 would you like printed?");
        n = scan.nextInt();
                
        //print a message saying how many powers of 2 will be printed
        System.out.println("Here are the first " + n + " powers of 2:");

    //initialize exponent -- the first thing printed is 2 to the what?
    exponent = 0;

    while (exponent < n)
    {
        //print out current power of 2
        System.out.println("2^" + exponent + " = " + nextPowerOf2);
    
        //find next power of 2 -- how do you get this from the last one?
        nextPowerOf2 *= 2;

        //increment exponent
        exponent++;
    }
    }
}
