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

    public int minFallingPathSum(int[][] A) {
        if(A==null || A.length==0) {
            return 0;
        }
        for (int i=1;i<A.length;i++) {
            for (int j=0;j<A.length;j++) {
                int leftMin = Integer.MAX_VALUE;
                for (int k=0;k<j;k++) {
                    leftMin = Math.min(A[i-1][k],leftMin);
                }
                int rightMin = Integer.MAX_VALUE;
                for (int k=j+1;k<A.length;k++) {
                    rightMin = Math.min(A[i-1][k],rightMin);
                }
                A[i][j]+=Math.min(leftMin,rightMin);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int a : A[A.length-1]) {
            ans = Math.min(a,ans);
        }
        return ans;
    }

}

/*

For every element in every column starting from second last row,
count minimum on the left & minimum on the right.

Same as Paint House 2.

 */
