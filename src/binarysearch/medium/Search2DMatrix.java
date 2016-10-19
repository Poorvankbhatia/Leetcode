/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

 */
package binarysearch.medium;

/**
 * Created by poorvank on 11/10/16.
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {

        int row = matrix.length;
        int col = matrix[0].length;

        int i=0,j=col-1;

        while (i< row && j<col && i>=0 && j>=0) {

            int val = matrix[i][j];

            if(target==val) {
                return true;
            } else if(target<val) {
                j--;
            } else {
                i++;
            }

        }

        return false;

    }



}
