/*

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at
the same time (ie, you must sell the stock before you buy again).

 */
package greedy.easy;

import java.util.ArrayList;

/**
 * Created by poorvank.b on 07/11/17.
 */
public class Stock2 {

    private static class Interval {

        public int sell;
        public int buy;

    }

    public int maxProfit(int[] prices) {

        if(prices==null || prices.length==0) {
            return 0;
        }

        ArrayList<Interval> arrayList = new ArrayList<>();

        int i = 0;

        while (i < prices.length) {

            Interval interval = new Interval();

            while (i < prices.length - 1 && (prices[i] >= prices[i + 1])) {
                i++;
            }

            interval.buy = i++;

            while (i < prices.length && (prices[i] >= prices[i - 1])) {
                i++;
            }

            interval.sell = i - 1;

            arrayList.add(interval);

        }

        int max=0;
        for (Interval interval : arrayList) {
            max += prices[interval.sell]-prices[interval.buy];
        }

        return max;


    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4};
        System.out.println(new Stock2().maxProfit(arr));
    }

}
