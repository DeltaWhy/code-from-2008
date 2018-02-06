//Michael Limiero
//Project 20
// ***************************************************************
//   Election.java
//
//   This file contains a program that tallies the results of
//   an election.  It reads in the number of votes for each of
//   two candidates in each of several precincts.  It determines
//   the total number of votes received by each candidate, the
//   percent of votes received by each candidate, the number of 
//   precincts each candidate carries, and the 
//   maximum winning margin in a precinct.
// **************************************************************

import java.util.Scanner;

public class Election
{
    public static void main (String[] args)
    {
	Scanner scan = new Scanner(System.in);
	int votesForPolly;  // number of votes for Polly in each precinct
	int votesForErnest; // number of votes for Ernest in each precinct
	int totalPolly=0;     // running total of votes for Polly
	int totalErnest=0;    // running total of votes for Ernest
	//initialize here because it takes 2 characters instead of many... smaller code...
	//   TI83 programming has ruined my brain.
	int precinctsPolly=0; //keeping with the too-long variable names
	int precinctsErnest=0;
	int precinctsTie=0;
	String response;      // answer (“y” or “n”) to the "more precincts" question
	

	System.out.println ();
	System.out.println ("Election Day Vote Counting Program");
	System.out.println ();

	// Loop to "process" the votes in each precinct
	do {
	    System.out.print("Votes for Polly: ");
	    votesForPolly = scan.nextInt();
	    System.out.print("Votes for Ernest: ");
	    votesForErnest = scan.nextInt();
	    totalPolly += votesForPolly;
	    totalErnest += votesForErnest;
	    if (votesForPolly > votesForErnest) precinctsPolly++;
	    else if (votesForPolly < votesForErnest) precinctsErnest++;
	    else precinctsTie++;
	    	    
	    System.out.print("Continue? (y or n) ");
	    response = scan.next().toLowerCase().trim();
	} while (response.equals("y"));

	// Print out the results
	System.out.println();
	System.out.println("Polly: " + totalPolly + " total votes");
	System.out.print( ((double)(totalPolly*100)) / (totalPolly+totalErnest) );
	System.out.println("% of the vote");
	System.out.println("Ernest: " + totalErnest + " total votes");
	System.out.print( ((double)(totalErnest*100)) / (totalPolly+totalErnest) );
	System.out.print("% of the vote");
	System.out.println();
	System.out.println("Polly: " + precinctsPolly + " precincts");
	System.out.println("Ernest: " + precinctsErnest + " precincts");
	System.out.println("Tie: " + precinctsTie + " precincts");
    }
}
