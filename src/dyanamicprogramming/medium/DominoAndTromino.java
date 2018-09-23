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