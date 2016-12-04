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
