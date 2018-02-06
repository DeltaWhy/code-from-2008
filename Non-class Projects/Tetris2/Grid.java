public class Grid
{
    public int[][] data;
    public int pieceY; //bottom row of the piece
    private int currentPiece;
    boolean[][] piece;
    public Grid(int width, int height)
    {
        data = new int[height][width];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                data[y][x] = 0;
        pieceY = -1;
        currentPiece = 0;
    }
    public boolean newPiece(int piece)
    {
        if (currentPiece == 0) currentPiece = piece;
        for (int y = 0; y < data.length; y++)
            for (int x = 0; x < data[0].length; x++)
                if (data[y][x] == TetrisApplet.PIECE_CURRENT) data[y][x] = currentPiece;
        currentPiece = piece;
        //if it returns false, you lose!
        boolean[][] p = Pieces.PIECES[piece];
        int w = p[0].length;
        int h = p.length;
        int xo = (data[0].length - w)/2;
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                if (data[y][xo+x] != 0) return false;
        //good, the space is clear.
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                if (p[y][x]) data[y][xo+x] = TetrisApplet.PIECE_CURRENT;
        pieceY = h-1; //bottom row of the piece
        this.piece = p;
        return true;
    }
    public boolean dropGrid()
    {
        return dropGrid(1);
    }
    public boolean dropGrid(int rows)
    {
        return dropGrid(data.length-1,rows);
    }
    public boolean dropGrid(int start, int rows)
    {
        for (int y = start; y>=rows; y--)
            for (int x = 0; x < data[0].length; x++)
                data[y][x] = data[y-rows][x];
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < data[0].length; x++)
                data[y][x] = 0;
        if (pieceY > 0) pieceY += rows;
        return true;
    }
    public boolean dropPiece()
    {
        for (int y = pieceY+1; y>=0; y--)
        {
            for (int x = 0; x < data[0].length; x++)
            {
                if (y >= data.length) return false; 
                if (data[y][x] == TetrisApplet.PIECE_CURRENT)
                {
                    if (data[y+1][x] != 0 && 
                        data[y+1][x] != TetrisApplet.PIECE_CURRENT)
                            return false;
                }
            }
        }
        for (int y = pieceY+1; y>=0; y--)
        {
            for (int x = 0; x < data[0].length; x++)
            {
                if (data[y][x] == TetrisApplet.PIECE_CURRENT)
                {
                    data[y+1][x] = data[y][x];
                    data[y][x] = 0;
                }
            }
        }
        pieceY+=1;
        return true;
    }
    public boolean shiftPiece(int cols)
    {
        //cols should only be -1 or 1, but let's make it work for anything...
        for (int y = pieceY; y > pieceY-4 && y >= 0; y--)
            for (int x = 0; x < data[0].length; x++)
                if (data[y][x] == TetrisApplet.PIECE_CURRENT)
                {
                    if (x+cols >= data[0].length || x+cols < 0) return false;
                    if (data[y][x+cols] != 0 && data[y][x+cols] != 
                        TetrisApplet.PIECE_CURRENT)
                            return false;
                }
        if (cols < 0)
        {
            for (int y = pieceY; y > pieceY-4 && y >= 0; y--)
                for (int x = 0; x < data[0].length; x++)
                    if (data[y][x] == TetrisApplet.PIECE_CURRENT)
                    {
                        data[y][x+cols] = TetrisApplet.PIECE_CURRENT;
                        data[y][x] = 0;
                    }
        } else {
            for (int y = pieceY; y > pieceY-4 && y >= 0; y--)
                for (int x = data[0].length - 1; x >= 0; x--)
                    if (data[y][x] == TetrisApplet.PIECE_CURRENT)
                    {
                        data[y][x+cols] = TetrisApplet.PIECE_CURRENT;
                        data[y][x] = 0;
                    }
        }
        return true;
    }
    public boolean rotatePiece()
    {
        //WARNING: The following method is long and painful!
        // This was probably the hardest part of the game to create...
        // There is probably a less ugly way, but this was the best
        //  I could do... Here we go!
        
        if (currentPiece == TetrisApplet.PIECE_O) return false;
        //saves some time
        boolean[][] p;
        p = piece;
        if (piece == null) p = Pieces.PIECES[currentPiece]; //ugh
        int w = p[0].length;
        int h = p.length;

        boolean[][] n = new boolean[w][h]; //sideways
        for (int y = 0; y < p.length; y++)
            for (int x = 0; x < p[0].length; x++)
            {
                n[x][h-(y+1)] = p[y][x];
            }
        //so now n is the flipped piece's array...

        int top = pieceY-(h-1); //top of current piece
        int left = 9999;
        //loop through the area of the piece to find the leftmost block
        for (int y = top; y <= pieceY; y++) //from top to bottom
            for (int x = 0; x < data[0].length; x++)
                if (data[y][x] == TetrisApplet.PIECE_CURRENT)
                    if (x < left) left = x;
        if (left == 9999) return false; //should never ever happen
        int newTop = -1, newLeft = -1;
        
        //find the new corner - depends on the piece
        if (w == 3 && h == 2)
        {
            newTop = top;
            newLeft = left;
        } else if (w == 4 && h == 1) {
            newTop = top - 1;
            if (top == 0) newTop = 0; //hackish but whatever...
            newLeft = left+1; //if this is bad there are more serious issues
                                //so don't bother error-handling
        } else if (w == 2 && h == 3) {
            newTop = top;
            newLeft = left;
        } else if (w == 1 && h == 4) {
            newTop = top + 1;
            newLeft = left - 1;
        } //there are actually only 4 sizes ignoring the square piece
        //this will probably be wrong the first few times...
        int newH = w, newW = h;
        
        //check if there are pieces in the way
        if (newTop + newH - 1 >= data.length) return false;
        if (newLeft + newW - 1 >= data[0].length) return false;
        for (int y = newTop; y < newTop + newH; y++)
            for (int x = newLeft; x < newLeft + newW; x++)
                if (n[y-newTop][x-newLeft] &&
                    data[y][x] != TetrisApplet.PIECE_CURRENT &&
                    data[y][x] != 0)
                        return false;
                        
        //wow, we made it this far!
        //delete the old piece
        for (int y = top; y < top + h; y++)
            for (int x = left; x < left + w; x++)
                if (data[y][x] == TetrisApplet.PIECE_CURRENT) data[y][x] = 0;
                
        //now put in the new one!
        for (int y = newTop; y < newTop + newH; y++)
            for (int x = newLeft; x < newLeft + newW; x++)
                if (n[y-newTop][x-newLeft]) data[y][x] = TetrisApplet.PIECE_CURRENT;
        pieceY = newTop + (newH-1);
        piece = n;
        return true; //PHEW!!
    }
    public int checkForLines()
    {
        int ans = 0;
        for (int y = data.length-1; y >= 0; y--)
        {
            boolean line = true;
            for (int x = 0; line && x < data[0].length; x++)
                if (data[y][x] == 0) line = false;
            if (line) 
            {
                dropGrid(y,1);
                ans++;
                y++; //bad form, but not sure how else to do this
            }
        }
        return ans;
    }
                   
}
