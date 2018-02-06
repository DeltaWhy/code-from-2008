// *******************************************************************
//   Coin.java            Author: Lewis and Loftus
//
//   Represents a coin with two sides that can be flipped.
// *******************************************************************

public class BiasedCoin
{
    public final int HEADS = 0;
    public final int TAILS = 1;

    private int face;
    private double bias;

    // ---------------------------------------------
    //   Sets up the coin by flipping it initially.
    // ---------------------------------------------
    public BiasedCoin()
    {
        bias = 0.5;
        flip();
    }
    
    public BiasedCoin(double bias)
    {
        this.bias = bias;
        flip();
    }

    // -----------------------------------------------
    //   Flips the coin by randomly choosing a face.
    // -----------------------------------------------
    public void flip()
    {
        if (Math.random() < bias) face = HEADS;
        else face = TAILS;
    }

    // -----------------------------------------------------
    //   Returns the current face of the coin as an integer.
    // -----------------------------------------------------
    public int getFace()
    {
        return face;
    }

    // ----------------------------------------------------
    //   Returns the current face of the coin as a string.
    // ----------------------------------------------------
    public String toString()
    {
        String faceName;

        if(face == HEADS)
            faceName = "Heads";
        else
            faceName = "Tails";

        return faceName;
    }
}
