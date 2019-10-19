/*

You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.

Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.



Example 1:

Input: prob = [0.4], target = 1
Output: 0.40000
Example 2:

Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
Output: 0.03125


Constraints:

1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
Answers will be accepted as correct if they are within 10^-5 of the correct answer.

 */
package dyanamicprogramming.medium;

public class TossStrangeCoins {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];
        dp[0] = 1.0;
        for (int i = 0; i < prob.length; ++i)
            for (int k = Math.min(i + 1, target); k >= 0; --k)
                dp[k] = (k > 0 ? dp[k - 1] : 0) * prob[i] + dp[k] * (1 - prob[i]);
        return dp[target];
    }
}

/*

dp[c][k] is the prob of tossing c first coins and get k faced up.
dp[c][k] = dp[c - 1][k] * (1 - p) + dp[c - 1][k - 1] * p)
where p is the prob for c-th coin.


 */