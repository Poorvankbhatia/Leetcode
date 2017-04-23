/*

Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3

 */

package bfsdfs.medium;

/**
 * Created by poorvank.b on 23/04/17.
 */
public class LongestConsecutiveOne {

    private class Cell {
        int horizontalSum;
        int verticalSum;
        int diagonalSum;
        int antiDiagonalSum;
        int max;

        public Cell(int horizontalSum, int verticalSum, int diagonalSum, int antiDiagonalSum,int max) {
            this.horizontalSum = horizontalSum;
            this.verticalSum = verticalSum;
            this.diagonalSum = diagonalSum;
            this.antiDiagonalSum = antiDiagonalSum;
            this.max = max;
        }
    }

    public int longestLine(int[][] M) {

        int rows = M.length;
        if(rows==0) {
            return 0;
        }

        int cols = M[0].length;

        Cell[][] oneCount = new Cell[rows][cols];

        int max = 0;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(M[i][j]==1) {
                    countOnesUtil(M,i,j,rows,cols,oneCount);
                    max = Math.max(max,oneCount[i][j].max);
                }
            }
        }

        return max;

    }

    private void countOnesUtil(int[][] matrix,int currentRow,int currentCol,int row,int col,Cell[][] oneCount) {

        int horizontalSum = 1;
        int verticalSum = 1;
        int diagonalSum = 1;
        int antiDiagonalSum = 1;
        if (isValid(currentRow, currentCol-1, row, col,matrix)) {
            horizontalSum += oneCount[currentRow][currentCol-1].horizontalSum;
        }
        if (isValid(currentRow-1, currentCol-1, row, col,matrix)) {
            diagonalSum += oneCount[currentRow-1][currentCol-1].diagonalSum;
        }
        if (isValid(currentRow-1, currentCol, row, col,matrix)) {
            verticalSum += oneCount[currentRow-1][currentCol].verticalSum;
        }
        if (isValid(currentRow-1, currentCol+1, row, col,matrix)) {
            antiDiagonalSum += oneCount[currentRow-1][currentCol+1].antiDiagonalSum;
        }

        int max = Math.max(Math.max(diagonalSum,antiDiagonalSum),Math.max(horizontalSum,verticalSum));
        Cell newCell = new Cell(horizontalSum,verticalSum,diagonalSum,antiDiagonalSum,max);

        oneCount[currentRow][currentCol] = newCell;

    }

    private boolean isValid(int x,int y,int row,int col, int[][] matrix) {
        return x>=0 && y>=0 && x<row && y<col && matrix[x][y]==1;
    }

    public static void main(String[] args) {
        int[][] M = {{0,1,1,0},
                     {0,1,1,0},
                     {0,0,0,1}};

        System.out.println(new LongestConsecutiveOne().longestLine(M));

    }

}
