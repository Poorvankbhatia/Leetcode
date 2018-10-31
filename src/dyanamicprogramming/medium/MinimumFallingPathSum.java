/*

Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.
The next row's choice must be in a column that is different from the previous row's column by at most one.



Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation:
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.



Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100

 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 31/10/18.
 */
public class MinimumFallingPathSum {

    public int minFallingPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int j = 0; j < n; j++) {
            sumMap.put(((m-1)*(n)+j), A[m - 1][j]);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {

                int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
                if (j - 1 >= 0) {
                    left = sumMap.get(((i+1)*(n))+(j-1));
                }
                if (j + 1 < n) {
                    right = sumMap.get(((i+1)*(n))+(j+1));
                }
                int mid = sumMap.get(((i+1)*(n))+(j));
                sumMap.put(((i)*(n))+(j), Math.min(Math.min(left, right), mid)+A[i][j]);

            }
        }
        int res=Integer.MAX_VALUE;

        for (int j=0;j<n;j++) {
            res = Math.min(sumMap.get(j),res);
        }

        return res;

    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {-80,-13,22},
                {83,94,-5},
                {73,-48,61}
        };
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(a));
    }


}
/*

Time O(n ^ 2), space O(1) & O(n)

 */
