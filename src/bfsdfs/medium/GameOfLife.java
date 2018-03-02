/*

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts
with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

 */
package bfsdfs.medium;

/**
 * Created by poorvank on 27/11/16.
 */
public class GameOfLife {

    private int[] xMove = new int[]{0,1,-1,1,-1,1,0,-1};
    private int[] yMove = new int[]{1,0,-1,1,1,-1,-1,0};

    public void gameOfLife(int[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        int[][] anotherBoard = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            System.arraycopy(board[i], 0, anotherBoard[i], 0, cols);
        }


        /*for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                int liveNeighbours = countLiveNeighbours(i,j,rows,cols,anotherBoard);
                if(anotherBoard[i][j]==1) {
                    if(liveNeighbours<2) {
                        board[i][j] = 0;
                    } else if(liveNeighbours>=2 && liveNeighbours<=3) {
                        board[i][j] = 1;
                    } else if(liveNeighbours>3){
                        board[i][j] = 0;
                    }
                } else {
                    if(liveNeighbours==3){
                        board[i][j] = 1;
                    }
                }
            }
        }*/

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int lives = countLiveNeighbours(i,j,rows,cols,board);;

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }

    }

    private int countLiveNeighbours(int x,int y,int rows,int cols,int[][] board) {

        int count = 0;

        for (int k=0;k<8;k++) {
            int nextX = x+xMove[k];
            int nextY = y+yMove[k];

            if(isValid(nextX,nextY,rows,cols) && board[nextX][nextY]==1) {
                count++;
            }

        }

        return count;

    }


    private boolean isValid(int x,int y,int rows,int cols) {
        return x>=0 && y>=0 && x<rows && y<cols;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,1}};
        new GameOfLife().gameOfLife(arr);
    }

}


/*

Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.

To solve it in place, we use 2 bits to store 2 states:

[2nd bit, 1st bit] = [next state, current state]

- 00  dead (next) <- dead (current)
- 01  dead (next) <- live (current)
- 10  live (next) <- dead (current)
- 11  live (next) <- live (current)
In the beginning, every cell is either 00 or 01.
Notice that 1st state is independent of 2nd state.
Imagine all cells are instantly changing from the 1st to the 2nd state, at the same time.
Let’s count # of neighbors from 1st state and set 2nd state bit.
Since every 2nd state is by default dead, no need to consider transition 01 -> 00.
In the end, delete every cell’s 1st state by doing >> 1.
For each cell’s 1st bit, check the 8 pixels around itself, and set the cell’s 2nd bit.

Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
Transition 00 -> 10: when board == 0 and lives == 3.
To get the current state, simply do

board[i][j] & 1
To get the next state, simply do

board[i][j] >> 1


In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
cause problems when the active area encroaches the border of the array. How would you address these problems?

https://discuss.leetcode.com/topic/26236/infinite-board-solution


In principle, the Life field is infinite, but computers have finite memory. This leads to problems when the active area encroaches
on the border of the array. Programmers have used several strategies to address these problems. The simplest strategy is simply to assume
that every cell outside the array is dead. This is easy to program, but leads to inaccurate results when the active area crosses the boundary.
 A more sophisticated trick is to consider the left and right edges of the field to be
stitched together, and the top and bottom edges also, yielding a toroidal array. The result is that active areas that move across a
field edge
 reappear at the opposite edge. Inaccuracy can still result if the pattern grows too large, but at least there are no pathological
 edge effects.
 Techniques of dynamic storage allocation may also be used, creating ever-larger arrays to hold growing patterns.

Alternatively, the programmer may abandon the notion of representing the Life field with a 2-dimensional array, and use a different data
 structure, like a vector of coordinate pairs representing live cells. This approach allows the pattern to move about the field unhindered,
 as long as the population does not exceed the size of the live-coordinate array. The drawback is that counting live neighbours becomes a
  hash-table lookup or search operation, slowing down simulation speed. With more sophisticated data structures this problem can also be
  largely solved.


 */
