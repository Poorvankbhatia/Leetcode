/*

You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 17/12/16.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {

        if(amount==0) {
            return 0;
        }

        if(coins.length==0) {
            return -1;
        }

        int[] dp = new int[amount+1];
        dp[0] = 0;

        for (int i=1;i<=amount;i++) {
            dp[i] = amount+1;
        }

        for (int amountReq = 1;amountReq<=amount;amountReq++) {
            for (int coin : coins) {
                if (coin <= amountReq) {
                    dp[amountReq] = Math.min(dp[amountReq], dp[amountReq - coin] + 1);
                }
            }
        }

        return dp[amount]>amount?-1:dp[amount];


    }

    public static void main(String[] args) {
        int[] coins = new int[]{1};
        System.out.println(new CoinChange().coinChange(coins,1));
    }

}

/*

Time complexity : O(S*n)O(S∗n). On each step the algorithm finds the next F(i) in n iterations,
where 1<=i<=S Therefore in total the iterations are S*nS∗n.
Space complexity : O(S) We use extra space for the memoization table.

DO READ: https://leetcode.com/articles/coin-change/
 */