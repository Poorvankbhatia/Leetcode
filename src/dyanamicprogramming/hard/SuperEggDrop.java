/*

You are given K eggs, and you have access to a building with N floors from 1 to N.

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?



Example 1:

Input: K = 1, N = 2
Output: 2
Explanation:
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4


Note:

1 <= K <= 100
1 <= N <= 10000


 */
package dyanamicprogramming.hard;

public class SuperEggDrop {

    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        return helper(K, N, memo);
    }
    private int helper(int K, int N, int[][] memo) {
        if (N <= 1) {
            return N;
        }
        if (K == 1) {
            return N;
        }
        if (memo[K][N] > 0) {
            return memo[K][N];
        }

        int low = 1, high = N, result = N;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int left = helper(K - 1, mid - 1, memo);
            int right = helper(K, N - mid, memo);
            result = Math.min(result, Math.max(left, right) + 1);
            if (left == right) {
                break;
            } else if (left < right) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        memo[K][N] = result;
        return result;
    }

}

/*

We can easily come up with an O(KN^2) DP solution, where dp[k][n] = min(1 + max(dp[k - 1][i - 1], dp[k][n - i])) i = 1...n
In this implementation, we use recursion to simulate each move.
But it runs TLE.

class Solution {
    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        return helper(K, N, memo);
    }
    private int helper(int K, int N, int[][] memo) {
        if (N <= 1) {
            return N;
        }
        if (K == 1) {
            return N;
        }
        if (memo[K][N] > 0) {
            return memo[K][N];
        }
        int min = N;
        for (int i = 1; i <= N; i++) {
            int left = helper(K - 1, i - 1, memo);
            int right = helper(K, N - i, memo);
            min = Math.min(min, Math.max(left, right) + 1);
        }
        memo[K][N] = min;
        return min;
    }
}

Notice that for the same K when N goes up, dp[K][N] goes up.
Then for int left = helper(K - 1, i - 1, memo); int right = helper(K, N - i, memo); when i goes up, left goes up and right goes down.
We can use Binary Search here to get the minimum Math.max(left, right) + 1, when left and right are as close as possible.
We come to this O(KNlogN) solution:


It's natural to attempt dynamic programming, as we encounter similar subproblems. Our state is (K, N):
 K eggs and N floors left. When we drop an egg from floor X, it either survives and we have state (K, N-X),
  or it breaks and we have state (K-1, X-1).


Now for the key insight: Because dp(K,N) is a function that is increasing on N, the first term T1 = dp(K-1, X-1)
in our max expression is an increasing function on X, and the second term T2 = dp(K, N-X)
is a decreasing function on X. This means that we do not need to check every X to find the minimum -- instead, we can binary search for the best X.

Continuing our discussion, if T1<T2 the X value chosen is too small;
if T1>T2 then X is too big. However, this argument is not quite correct: when there are only two possible values of X, we need to check both.

Using the above fact, we can use a binary search to find the correct value of X more efficiently than checking all N of them.

See : https://leetcode.com/problems/super-egg-drop/solution/

 */