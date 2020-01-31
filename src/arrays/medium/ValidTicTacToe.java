/*
A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Note:

board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.
 */
package arrays.medium;

public class ValidTicTacToe {

    public boolean validTicTacToe(String[] board) {
        int n = board.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int diagonal = 0;
        int antiDiagonal =0;
        int turn=0;

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if(board[i].charAt(j)=='X') {
                    turn++;
                    rows[i]++;
                    cols[j]++;
                    if(i==j) {
                        diagonal++;
                    }
                    if(i+j==n-1) {
                        antiDiagonal++;
                    }
                } else if(board[i].charAt(j)=='O') {
                    turn--;
                    rows[i]--;
                    cols[j]--;
                    if(i==j) {
                        diagonal--;
                    }
                    if(i+j==n-1) {
                        antiDiagonal--;
                    }
                }
            }
        }
        boolean xWin=false;
        for (int i=0;i<n;i++) {
            if(rows[i]==n || cols[i]==n || diagonal==n || antiDiagonal==n) {
                xWin = true;
                break;
            }
        }
        boolean oWin=false;
        for (int i=0;i<n;i++) {
            if(rows[i]==-n || cols[i]==-n || diagonal==-n || antiDiagonal==-n) {
                oWin = true;
                break;
            }
        }
        if(turn<0 || turn>1 || (xWin && turn==0) || (oWin && turn==1)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] board = new String[]{
                "XXO",
                "XOX",
                "OXO"
        };
        System.out.println(new ValidTicTacToe().validTicTacToe(board));
    }

}
