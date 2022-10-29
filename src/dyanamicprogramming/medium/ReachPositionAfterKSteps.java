/*
You are given two positive integers startPos and endPos. Initially, you are standing at position startPos
on an infinite number line. With one step, you can move either one position to the left, or one position to the right.

Given a positive integer k, return the number of different ways to reach the position endPos starting from startPos,
such that you perform exactly k steps. Since the answer may be very large, return it modulo 109 + 7.

Two ways are considered different if the order of the steps made is not exactly the same.

Note that the number line includes negative integers.



Example 1:

Input: startPos = 1, endPos = 2, k = 3
Output: 3
Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
- 1 -> 2 -> 3 -> 2.
- 1 -> 2 -> 1 -> 2.
- 1 -> 0 -> 1 -> 2.
It can be proven that no other way is possible, so we return 3.
Example 2:

Input: startPos = 2, endPos = 5, k = 10
Output: 0
Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.


Constraints:

1 <= startPos, endPos, k <= 1000
 */
package dyanamicprogramming.medium;

import java.util.Arrays;

public class ReachPositionAfterKSteps {

    long[][] dp;
    int mod = (int) Math.pow(10,9)+7;

    public int numberOfWays(int startPos, int endPos, int k) {
        dp = new long[3000][k+1];
        for (long[] d : dp) {
            Arrays.fill(d,-1L);
        }
        return (int) fill(startPos,endPos,k);
    }

    private long fill(int startPos, int endPos, int k) {
        if(k==0) {
            if(startPos==endPos) {
                return 1;
            } else {
                return 0;
            }
        }
        // Tackle -ve index, -1000 stored at 0;
        if(dp[startPos+1000][k]!=-1) {
            return dp[startPos+1000][k];
        }
        long right = (fill(startPos+1,endPos,k-1));
        long left = (fill(startPos-1,endPos,k-1));
        dp[startPos+1000][k] = ((left)+(right))%mod;
        return (dp[startPos+1000][k]);
    }

    public static void main(String[] args) {
        System.out.println(new ReachPositionAfterKSteps().numberOfWays(989,1000,99));
    }

}

/*

Another:
Instead of positions, we can track the distance between them.

The distance is non-negative, so it's easier to memoise.

For k number of steps and distance d, the number of ways is dfs(k - 1, d + 1) + dfs(k - 1, abs(d - 1)).

Note that for d == 0, the nuber of ways is dfs(k - 1, 1) + dfs(k - 1, 1), since abs(0 - 1) == 1.
We can reach 0 from positions 1 and -1, and the number of ways for negative positions mirrors positive positions.



 */
