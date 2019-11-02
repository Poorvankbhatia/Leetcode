/*

Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j]
where i <= j, and remove that subarray from the given array. Note that after removing a subarray,
the elements on the left and on the right of that subarray move to fill the gap left by the removal.

Return the minimum number of moves needed to remove all numbers from the array.



Example 1:

Input: arr = [1,2]
Output: 2
Example 2:

Input: arr = [1,3,4,1,5]
Output: 3
Explanation: Remove [4] then remove [1,3,1] then remove [5].


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 20

 */
package dyanamicprogramming.hard;

public class MinimumMoves {

    public int minimumMoves(int[] arr) {
        if(arr==null || arr.length==0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][N + 1];

        // loop for sub-array length:
        for (int len = 1; len <= N; len++) {
            // loop with two variables i and j, denoting start & end of sub arrays.
            for (int i = 0, j = len - 1; j < N; i++, j++) {
                if (len == 1) {
                    dp[i][j] = 1;
                } else {
                    // delete the ith char individually
                    // and assign result for
                    // subproblem (i+1,j)
                    dp[i][j] = 1 + dp[i + 1][j];
                    // if current and next char are same,
                    // choose min from current and
                    // subproblem (i+2, j)
                    if (arr[i] == arr[i+1]) {
                        dp[i][j] = Math.min(1 + dp[i + 2][j],
                                dp[i][j]);
                    }

                    /* loop over all right indexes and suppose Xth char is same as ith
                      character then choose minimum from current and two substring after
                      ignoring ith and Xth char
                     */
                    for (int X = i + 2; X <= j; X++)
                        if (arr[i]== arr[X]) {
                            dp[i][j] = Math.min(dp[i + 1][X - 1] + dp[X + 1][j], dp[i][j]);
                        }
                }
            }
        }

        return dp[0][N - 1];
    }


}

/*

O(n^3) solution:

Explanation:
dp[i][j] -> the number of steps it takes to delete the sub array arr[i, j].

Each character will be deleted alone or as part of a subarray:
First case we will delete the array index itself & call sub-problem (i+1, j).
Second case we will iterate over all occurrence of the current array index in right side,
if K is the index of one such occurrence then the problem will reduce to two subproblems (i+1, K – 1) and (K+1, j).
We can reach to this subproblem (i+1, K-1) because we can just delete the same array index and call for mid subarray.
Also consider the case when first two characters are same in that case we can directly reduce to the subproblem (i+2, j)

dp relation as follows:
dp[i][j] = Math.min(1 + dp[i+1][j],
          dp[i+1][K-1] + dp[K+1][j],  where s[i] == s[K]
          1 + dp[i+2][j])

 */