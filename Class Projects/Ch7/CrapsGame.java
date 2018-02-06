// Implements the game of Craps logic

public class CrapsGame
{
  private int point = 0;

  /**
   *  Calculates the result of the next dice roll in the Craps game.
   *  The parameter total is the sum of dots on two dice.
   *  point is set to the saved total, if the game continues,
   *  or 0, if the game has ended.
   *  Returns 1 if player won, -1 if player lost,
   *  0 if player continues rolling.
   */
  public int processRoll(int total)
  {
    //if the total is 7 or 11, win
    //if it is 2, 3, or 12, lose
    //else, roll again
    //  and remember point
    
    //if there is point,
    //  roll point to win
    //  7 to lose
    
    if (point == 0)
    {
        if (total == 7 || total == 11) return 1;
        else if (total == 2 || total == 3 || total == 12) return -1;
        else
        {
            point = total;
            return 0;
        }
    } else {
        if (total == point)
        {
            point = 0;
            return 1;
        } else if (total == 7) {
            point = 0;
            return -1;
        } else return 0;
    }
  }

  /**
   *  Returns the saved point
   */
  public int getPoint()
  {
    return point;
  }
}
