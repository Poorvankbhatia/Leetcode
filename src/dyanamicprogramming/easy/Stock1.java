/*

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

 */
package dyanamicprogramming.easy;

/**
 * Created by poorvank on 11/09/16.
 */
public class Stock1 {

    public int maxProfit(int[] prices) {

        if(prices.length==0 || prices.length==1) {
            return 0;
        }

        int n = prices.length;
        int[] max = new int[n];
        max[n-1] = -1;
        int value = prices[n-1];
        for (int i=n-2;i>=0;i--) {
            max[i] = value;
            if(prices[i]>value) {
                value = prices[i];
            }
        }

        int maxProfit = 0;
        for (int i=0;i<n-1;i++) {
            if(maxProfit<max[i]-prices[i]) {
                maxProfit = max[i]-prices[i];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] a = new int[]{7, 6, 4, 3, 1};
        System.out.println(new Stock1().maxProfit(a));
    }

}
