/*

On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.
These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.

The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south),
then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by
moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.


Return the number of pawns the rook can capture in one move.

Input: [[".",".",".",".",".",".",".","."],
       [".",".",".","p",".",".",".","."],
       [".",".",".","R",".",".",".","p"],
       [".",".",".",".",".",".",".","."],
       [".",".",".",".",".",".",".","."],
       [".",".",".","p",".",".",".","."],
       [".",".",".",".",".",".",".","."],
       [".",".",".",".",".",".",".","."]]
Output: 3
Explanation:
In this example the rook is able to capture all the pawns.


Input: [[".",".",".",".",".",".",".","."],
        [".","p","p","p","p","p",".","."],
        [".","p","p","B","p","p",".","."],
        [".","p","B","R","B","p",".","."],
        [".","p","p","B","p","p",".","."],
        [".","p","p","p","p","p",".","."],
        [".",".",".",".",".",".",".","."],
        [".",".",".",".",".",".",".","."]]
Output: 0
Explanation:
Bishops are blocking the rook to capture any pawn.

Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],
["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],
[".",".",".",".",".",".",".","."]]
Output: 3
Explanation:
The rook can capture the pawns at positions b5, d6 and f5.


Note:

board.length == board[i].length == 8
board[i][j] is either 'R', '.', 'B', or 'p'
There is exactly one cell with board[i][j] == 'R'

 */
package bfsdfs.easy;

public class CapturesForRook {

    public int numRookCaptures(char[][] board) {
        if(board==null || board.length==0) {
            return 0;
        }
        int m = board.length;
        int n = board[0].length;
        int x=0,y=0;
        boolean flag=false;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j]=='R') {
                    x=i;
                    y=j;
                    flag=true;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }

        int result=0;
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] d : dir) {
            int nextX=d[0]+x;
            int nextY=d[1]+y;
            while(isSafe(nextX,nextY,m,n) && board[nextX][nextY]=='.') {
                nextX+=d[0];
                nextY+=d[1];
            }
            if(isSafe(nextX,nextY,m,n) && board[nextX][nextY]=='p') {
                result++;
            }

        }
        return result;
    }

    private boolean isSafe(int x,int y,int m,int n) {
        return x>=0 && y>=0 && x<m && y<n;
    }

}
