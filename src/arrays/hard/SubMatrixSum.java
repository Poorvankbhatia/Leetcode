/*
Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.


Note:

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
 */
package arrays.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubMatrixSum {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[] temp = new int[m];
        int ans=0;
        for (int left = 0;left<n;left++) {
            Arrays.fill(temp,0);
            for (int right=left;right<n;right++) {
                for (int i=0;i<m;i++) {
                    temp[i]+=matrix[i][right];
                }
                ans+= subCount(temp,target);
            }
        }
        return ans;
    }

    private int subCount(int[] input,int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int anInput : input) {
            sum += anInput;
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            // for cases like [0,0,0,0,0,0,0,0,0]
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1,-1},
                {-1,1}
        };
        System.out.println(new SubMatrixSum().numSubmatrixSumTarget(mat, 0));
    }

}

/*

Counting all sub-arrays with a given sum k for 1-Dimensional array can be used to reduce the time complexity.
We fix the left and right columns one by one and count sub-arrays for every left and right column pair.
The sum of elements in every row from left to right and store these sums in a temporary array say temp[].
So temp[i] indicates sum of elements from left to right in row i.
Count sub-arrays in temp[] having sum k.
Sum up all the counts for each temp[] with different left and right column pairs.

 */