/*

Given an 2D board, count how many different battleships are in it.
The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns)
or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

 */

package bfsdfs.medium;

/**
 * Created by poorvank on 16/11/16.
 */
public class BattleShip {

    public int countBattleships(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        int count = 0;

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(board[i][j]=='X') {
                   if((i==0 && j==0) || (i!=0 && j!=0 && board[i-1][j]=='.' && board[i][j-1]=='.') || (i==0 && board[i][j-1]=='.') ||
                           (j==0 && board[i-1][j]=='.') ) {
                       count++;
                   }
                }
            }
        }

        return count;

    }


}



/*

Done in one pass whithout modifying the board & O(1) extra memory

Just find cells which are the staring of battle ships i.e they don't have any element which is X above them or below.

 */