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


        for (int i=0;i<rows;i++) {
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


Since the board has ints but only the 1-bit is used, I use the 2-bit to store the new state.
At the end, replace the old state with the new state by shifting all values one bit to the right.

void gameOfLife(vector<vector<int>>& board) {
    int m = board.size(), n = m ? board[0].size() : 0;
    for (int i=0; i<m; ++i) {
        for (int j=0; j<n; ++j) {
            int count = 0;
            for (int I=max(i-1, 0); I<min(i+2, m); ++I)
                for (int J=max(j-1, 0); J<min(j+2, n); ++J)
                    count += board[I][J] & 1;
            if (count == 3 || count - board[i][j] == 3)
                board[i][j] |= 2;
        }
    }
    for (int i=0; i<m; ++i)
        for (int j=0; j<n; ++j)
            board[i][j] >>= 1;
}
Note that the above count counts the live ones among a cell's neighbors and the cell itself.
Starting with int count = -board[i][j] counts only the live neighbors and allows the neat

if ((count | board[i][j]) == 3)


In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
cause problems when the active area encroaches the border of the array. How would you address these problems?



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
