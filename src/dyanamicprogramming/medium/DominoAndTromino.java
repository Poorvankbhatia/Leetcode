/*

We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.

(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally
adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)

Example:
Input: 3
Output: 5
Explanation:
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY
Note:

N  will be in range [1, 1000].

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 21/09/18.
 */
public class DominoAndTromino {

    public int numTilings(int N) {

        int M = 1000000007;
        int[] dp = new int[1000 + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        if (N <= 3) {
            return dp[N];
        }
        for (int i = 4; i <= N; i++) {
            dp[i] = 2 * dp[i - 1] % M + dp[i - 3] % M;
            dp[i] %= M;
        }
        return dp[N];

    }

}

/*

dp[n]=dp[n-1]+dp[n-2]+ 2*(dp[n-3]+...+d[0])
=dp[n-1]+dp[n-2]+dp[n-3]+dp[n-3]+2*(dp[n-4]+...+d[0])
=dp[n-1]+dp[n-3]+(dp[n-2]+dp[n-3]+2*(dp[n-4]+...+d[0]))
=dp[n-1]+dp[n-3]+dp[n-1]
=2*dp[n-1]+dp[n-3]



 */