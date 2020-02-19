/*
Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.

Return the number of negative numbers in grid.



Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0
Example 3:

Input: grid = [[1,-1],[-1,-1]]
Output: 3
Example 4:

Input: grid = [[-1]]
Output: 1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100
 */
package binarysearch.easy;

public class CountNegativeNumbersMatrix {

    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int i=0,j=n-1;

        int count=0;
        while (i<m && j>=0) {
            if(grid[i][j]<0) {
                count+=(m-i);
                j--;
            } else {
                i++;
            }
        }
        return count;

    }

}

/*

Binary Search sol:

public int countNegatives(int[][] grid) {
    int res = 0;
    for(int[] row : grid){
        res += bs(row);
    }
    return res;
}

int bs(int[] row){
    int l = 0, r = row.length;
    while(l < r){
        int m = l + (r - l)/2;
        if(row[m] < 0)
            r = m;
        else
            l = m + 1;
    }
    return row.length - l;
}

See Search 2D Matrix.

 */