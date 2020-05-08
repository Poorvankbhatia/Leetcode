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

Example 3:
coins = [1,3,4], amount = 6
return {3,3} and not {4,1,1}.

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

F(S) - minimum number of coins needed to make change for amount S using coin denominations [c0…c​n−1]

 F(3)=min{F(3−c1),F(3−c2),F(3−c3)}+1
       =min{F(3−1),F(3−2),F(3−3)}+1
       =min{F(2),F(1),F(0)}+1
       =min{1,1,0}+1=1

Time complexity : O(S*n). On each step the algorithm finds the next F(i) in n iterations,
where 1<=i<=S Therefore in total the iterations are S*n
Space complexity : O(S) We use extra space for the memoization table.

DO READ: https://leetcode.com/articles/coin-change/
 */