/*

Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 08/03/18.
 */
public class StockWithFees {

    public int maxProfit(int[] prices, int fee) {

        if(prices==null || prices.length==0) {
            return 0;
        }

        int n = prices.length;

        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] hold = new int[n];
        int[] skip = new int[n];

        // the moment we buy a stock, our balance should decrease
        buy[0] = -prices[0];
        // assume if we have stock in the first day, we are still in deficit
        hold[0] = -prices[0];

        for (int i=1;i<n;i++) {

            // We can only buy on today if we sold stock
            // or skipped with empty portfolio yesterday
            buy[i] = Math.max(sell[i-1],skip[i-1]) - prices[i];

            // Can only hold if we bought or already holding stock yesterday
            hold[i] = Math.max(hold[i-1],buy[i-1]);

            // Can skip only if we skipped, or sold stock yesterday
            skip[i] = Math.max(skip[i-1],sell[i-1]);

            // Can sell only if we bought, or held stock yesterday
            sell[i] = Math.max(buy[i-1],hold[i-1])+prices[i]-fee;

        }

        int max = Math.max(Math.max(hold[n-1],skip[n-1]),Math.max(buy[n-1],sell[n-1]));
        return Math.max(max,0);

    }

}

/*

This problem is just like the other stock problems.
At given day, we can do 1 out of 4 things:

1.buy stock
2.hold stock
3.do nothing with empty portfolio
4.sell stock
We have 4 arrays with the length of # of the days, recording the max profit at given day if we do given operation.

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108892/Java-DP-solution-O(n)-to-O(1)-space



 */