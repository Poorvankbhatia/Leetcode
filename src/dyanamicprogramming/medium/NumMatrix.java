/*

Given a 2D matrix matrix, find the sum of the elements
inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).


Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 07/12/16.
 */
public class NumMatrix {

    int[][] sumMatrix = null;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int rows=matrix.length;
        if(rows==0) {
            return;
        }
        int cols = matrix[0].length;
        sumMatrix = new int[rows][cols];

        sumMatrix[0][0] = matrix[0][0];

        for (int i=1;i<rows;i++) {
            sumMatrix[i][0] = matrix[i][0] + sumMatrix[i-1][0];
        }

        for (int j=1;j<cols;j++) {
            sumMatrix[0][j] = matrix[0][j] + sumMatrix[0][j-1];
        }

        /*for (int i=1;i<rows;i++) {
            for (int j=1;j<cols;j++) {
                sumMatrix[i][j] = matrix[i][j] + sumMatrix[i-1][j] + sumMatrix[i][j-1] - sumMatrix[i-1][j-1];
            }
        }*/

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        if(sumMatrix==null || (row1>=matrix.length || col1>=matrix[0].length)) {
            return 0;
        }


        if(row1==0 && col1==0) {
            return sumMatrix[row2][col2];
        }

        int sum = 0;

        if(row1-1>=0 && col1-1>=0) {
            sum = sumMatrix[row2][col2] - sumMatrix[row2][col1-1] - sumMatrix[row1-1][col2] + sumMatrix[row1-1][col1-1];
        } else if(row1-1<0) {
            sum = sumMatrix[row2][col2] - sumMatrix[row2][col1-1];
        } else if(col1-1<0) {
            sum = sumMatrix[row2][col2] - sumMatrix[row1-1][col2];
        } else {
            sum = sumMatrix[row2][col2];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{7 ,7 ,0},
                                  {-4,-7,7},
                                  {-4,0 ,-2},
                                  {-8,5 ,6}};

        NumMatrix nm = new NumMatrix(arr);
        System.out.print(nm.sumRegion(0,2,2,2));
    }

}
