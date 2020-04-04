/*

A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]

Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.



Example 1:

Input: satisfaction = [-1,-8,0,5,-9]
Output: 14
Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.
Example 2:

Input: satisfaction = [4,3,2]
Output: 20
Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
Example 3:

Input: satisfaction = [-1,-4,-5]
Output: 0
Explanation: People don't like the dishes. No dish is prepared.
Example 4:

Input: satisfaction = [-2,5,-1,0,3,-3]
Output: 35


Constraints:

n == satisfaction.length
1 <= n <= 500
-10^3 <= satisfaction[i] <= 10^3
 */
package dyanamicprogramming.hard;

import java.util.*;

public class ReducingDishes {

    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);
        int postSum=0,cur=0,res=0;
        for(int i=n-1; i>=0; i--){
            postSum += satisfaction[i];
            cur += postSum;
            res = Math.max(res, cur);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ReducingDishes().maxSatisfaction(new int[]{34,-27,-49,-6,65,70,72,-37,-57,92,-72,36,6,-91,18,61,77,-91,5,64,-16,5,20,
                -60,-94,-15,-23,-10,-61,27,89,38,46,57,33,94,-79,43,-67,-73,-39,72,-52,13,65,-82,26,69,67}));
    }

}

/*

Very Similar to Kadane's Algorithm/ Max Subarray sum

 It's easy to see we want to spend most time on highest number so we sort the satisfatction.

 We can calculate in linear time that point using prefix sums as each time we consider the next satisfaction,
 increasing all previous times with 1 is the same as adding the prefix sum so far.

 Another method:

 int[][] dp;
    private int dfs(int[] satisfaction, int p, int t) {
        if (p >= satisfaction.length)
            return 0;
        if (dp[p][t]!=0)
            return dp[p][t];
        dp[p][t] = Math.max(dfs(satisfaction, p + 1, t), satisfaction[p] * t + dfs(satisfaction, p + 1, t + 1));
        return dp[p][t];
    }
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        dp = new int[501][501];
        return dfs(satisfaction, 0, 1);
    }

 */