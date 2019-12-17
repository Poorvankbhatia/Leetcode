/*

Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one
element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.

Return the minimum sum of a falling path with non-zero shifts.



Example 1:

Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation:
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.


Constraints:

1 <= arr.length == arr[i].length <= 200
-99 <= arr[i][j] <= 99

 */
package dyanamicprogramming.hard;

public class MinimumFallingPathSum2 {

    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        for (int row=n-2;row>=0;row--) {
            for (int col=0;col<n;col++) {
                int min = Integer.MAX_VALUE;
                // Values to the left.
                for(int k=0;k<col;k++) {
                    min = Math.min(arr[row+1][k],min);
                }
                // values to the right.
                for (int k=col+1;k<n;k++) {
                    min = Math.min(arr[row+1][k],min);
                }
                arr[row][col]+= min;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            result = Math.min(result, arr[0][i]);
        }
        return result;
    }

}

/*

For every element in every column starting from second last row,
count minimum on the left & minimum on the right.

Same as Paint House 2.

 */
