/*

A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an N x N grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).



Example 1:

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation:
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.
Note:

1 <= grid.length = grid[0].length <= 10
0 <= grid[i][j] <= 15

 */
package arrays.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 27/05/18.
 */
public class MagicSquaresInGrid {

    public int numMagicSquaresInside(int[][] grid) {

        if(grid==null || grid.length<2 || grid[0].length<3) {
            return 0;
        }

        int m = grid.length;
        int n  = grid[0].length;

        int count = 0;

        for (int i=1;i<m-1;i++) {
            for(int j=1;j<n-1;j++) {
                if(equalDiagonals(i,j,grid) && containsAllElements(i,j,grid) && equalRowsAndCols(i,j,grid) && equalCols(i,j,grid)) {
                    System.out.println(i+" "+j);
                    count++;
                }
            }
        }

        return count;

    }

    private boolean containsAllElements(int i,int j,int[][] grid) {
        Set<Integer>  set = new HashSet<>();

        for (int m=i-1;m<=i+1;m++) {
            for (int n=j-1;n<=j+1;n++) {
                if(grid[m][n]<=9 && grid[m][n]>=1) {
                    set.add(grid[m][n]);
                }
            }
        }

        return set.size()==9;

    }

    private boolean equalDiagonals(int i, int j, int[][] grid) {

        int sum = grid[i][j];

        int leftDiagonal = sum+grid[i-1][j-1]+grid[i+1][j+1];
        int rightDiagonal = sum+grid[i-1][j+1]+grid[i+1][j-1];

        return leftDiagonal==rightDiagonal;

    }

    private boolean equalRowsAndCols(int i, int j, int[][] grid) {

        int[] s = new int[3];

        int k=0;
        for (int m=i-1;m<=i+1;m++) {
            int sum=0;
            for (int n=j-1;n<=j+1;n++) {
                sum+=grid[m][n];
            }
            s[k]=sum;
            k++;
        }

        return s[0]==s[1] && s[1]==s[2];

    }

    private boolean equalCols(int i, int j, int[][] grid) {

        int[] s = new int[3];

        int k=0;
        for (int n=j-1;n<=j+1;n++) {
            int sum=0;
            for (int m=i-1;m<=i+1;m++) {
                sum+=grid[m][n];
            }
            s[k]=sum;
            k++;
        }

        return s[0]==s[1] && s[1]==s[2];

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {5,4,7,8,5,4,6,8,2,9},
                {5,3,6,8,1,5,1,1,8,5},
                {8,9,6,8,4,7,3,4,9,1},
                {9,3,8,9,2,8,3,8,7,1},
                {1,1,1,7,3,3,9,4,3,8},
                {1,5,5,7,1,6,7,9,5,1},
                {2,3,3,8,8,1,1,2,7,6},
                {7,8,5,4,7,9,4,6,5,3},
                {8,5,2,7,1,3,7,2,8,9},
                {4,9,4,3,9,4,5,4,7,1}
        };
        System.out.println(new MagicSquaresInGrid().numMagicSquaresInside(arr));
    }

}
