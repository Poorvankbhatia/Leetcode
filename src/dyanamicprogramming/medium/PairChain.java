/*

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].

 */
package dyanamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 31/07/17.
 */
public class PairChain {

    public int findLongestChain(int[][] pairs) {

        int n = pairs.length;
        if(n==0) {
            return 0;
        }

        Arrays.sort(pairs,(a,b)-> (a[0]-b[0]));

        int[][] dp = new int[n][n];
        int len = 0;

        // In case we have negative numbers, so that chain doesn't start from (0,0)
        dp[0] = pairs[0];

        for (int i=1;i<n;i++) {
            int pos = binarySearch(dp, pairs[i], len);
            // For example {-9,8},{-6,2}. Later should be replaced by former.
            if ((pairs[i][1] < dp[pos][0]) || (pairs[i][1]<dp[pos][1])) {
                dp[pos] = pairs[i];
            }
            if (pos > len) {
                len = pos;
                dp[len] = pairs[i];
            }
        }

        return len+1;
    }

    private int binarySearch(int[][] dp,int[] val,int len) {
        int left = 0;
        int right = len;
        while (left+1<right) {
            int mid = (right-left)/2 + left;
            if(dp[mid][1]<val[0]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if(dp[right][1]<val[0]) return len+1;
        if(dp[left][0]>=val[1] || (dp[left][1]>val[1])) return left;
        return right;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}
                /*{-9,8},{-6,2},{3,4}*/
        };
        System.out.println(new PairChain().findLongestChain(a));
    }

}
