//Michael Limiero
//Project 21
// **********************************************************
//   Temps.java
//
//   This program reads in a sequence of hourly temperature 
//   readings (beginning with midnight) and prints the maximum
//   temperature (along with the hour, on a 24-hour clock, it 
//   occurred) and the minimum temperature (along with the hour
//   it occurred).
// **********************************************************

import java.util.Scanner;

public class Temps
{
    public static void main (String[] args)
    {
	final int HOURS_PER_DAY = 24; //5 yard penalty - unneccesary use of constants
	//maybe this is an "enterprisey" app... even so we should at least have an array or seven...

	Scanner scan = new Scanner(System.in);
	int temp;   // a temperature reading //no, ya think??
	
	int maxTemp = Integer.MIN_VALUE; //very small number
	int minTemp = Integer.MAX_VALUE; //take a guess...
	int timeMax = -1;
	int timeMin = -1;
	
	// print program heading
	System.out.println ();
	System.out.println ("Temperature Readings for 24 Hour Period");
	System.out.println ();

	for (int hour = 0; hour < HOURS_PER_DAY; hour++)
	{
	    System.out.print ("Enter the temperature reading at " + hour +
				" hours: ");
	    temp = scan.nextInt();
	    if (temp > maxTemp)
	    {
	        maxTemp = temp;
	        timeMax = hour;
	    }
	    if (temp < minTemp)
	    {
	        minTemp = temp;
	        timeMin = hour;
	    }
	}

	// Print the results
	System.out.println("\nThe maximum temperature was " + maxTemp + "°");
	System.out.println("It occured at " + timeMax + "00 hours.");
    System.out.println("\nThe minimum temperature was " + minTemp + "°");
	System.out.println("It occured at " + timeMin + "00 hours.");

    }
}