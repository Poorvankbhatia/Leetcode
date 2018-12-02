/*

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at
the same time (ie, you must sell the stock before you buy again).

 */
package greedy.easy;

/**
 * Created by poorvank.b on 07/11/17.
 */
public class Stock2 {

    public int maxProfit(int[] prices) {

        if(prices==null||prices.length==0) return 0;
        int i=0;
        int peak=prices[0];
        int valley=prices[0];
        int maxProfit=0;
        int len=prices.length;
        while (i<len-1){
            while (i<len-1 && prices[i]>=prices[i+1]){//down
                i++;
            }
            valley=prices[i];
            while (i<len-1 && prices[i]<=prices[i+1]){//up
                i++;
            }
            peak = prices[i];
            maxProfit+=peak-valley;
        }
        return maxProfit;


    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4};
        System.out.println(new Stock2().maxProfit(arr));
    }

}
