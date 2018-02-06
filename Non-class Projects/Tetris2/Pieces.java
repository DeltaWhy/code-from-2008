public class Pieces
{
    public static boolean[][] PIECE_J = {{true,false,false},{true,true,true}};
    public static boolean[][] PIECE_L = {{false,false,true},{true,true,true}};
    public static boolean[][] PIECE_I = {{true,true,true,true}};
    public static boolean[][] PIECE_O = {{true,true},{true,true}};
    public static boolean[][] PIECE_T = {{false,true,false},{true,true,true}};
    public static boolean[][] PIECE_S = {{false,true,true},{true,true,false}};
    public static boolean[][] PIECE_Z = {{true,true,false},{false,true,true}};
    public static boolean[][][] PIECES = {null,PIECE_J,PIECE_L,PIECE_I,PIECE_S,PIECE_O,PIECE_T,PIECE_Z};
}
