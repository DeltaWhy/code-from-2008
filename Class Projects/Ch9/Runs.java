//VERSION 1.1 - Fixed a subtle bug that occurs when the longest run
// lasts until the last flip.
// ********************************************************************
// Runs.java
//
// Finds the length of the longest run of heads in 100 flips of a coin.
// ********************************************************************
//Michael Limiero

public class Runs
{
    public static void main (String[] args)
    {
    final int FLIPS = 100; // number of coin flips

    int currentRun = 0; // length of the current run of HEADS
    int maxRun = 0;     // length of the maximum run so far

    // Create a coin object
    Coin theCoin = new Coin();


    // Flip the coin FLIPS times
    for (int i = 0; i < FLIPS; i++)
    {
        // Flip the coin & print the result
        theCoin.flip();
        System.out.println(theCoin);

        // Update the run information
        if (theCoin.getFace() == theCoin.HEADS)
        {
            currentRun++;
        } else {
            if (currentRun > maxRun) maxRun = currentRun;
            currentRun = 0;
        }
    }
    //EDIT - bugfix
    if (currentRun > maxRun) maxRun = currentRun;
    // Print the results
    System.out.println("The longest run of heads was " + maxRun + ".");
    }
}
