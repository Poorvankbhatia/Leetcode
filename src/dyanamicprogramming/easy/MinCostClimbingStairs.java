/*

On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor,
 and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

 */
package dyanamicprogramming.easy;

/**
 * Created by poorvank.b on 17/12/17.
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {

        if(cost==null || cost.length==0) {
            return 0;
        }

        int n = cost.length;
        int[] finalCost = new int[n]; // finalCost[i] -> Represents minimum cost to reach the ith step.

        finalCost[0]=0;
        finalCost[1]=0;

        for (int i=2;i<n;i++) {
            finalCost[i] = Math.min(cost[i-1]+finalCost[i-1],cost[i-2]+finalCost[i-2]);
        }

        return Math.min(cost[n-1]+finalCost[n-1],cost[n-2]+finalCost[n-2]);

    }

    public static void main(String[] args) {
        int[] cost = new int[]{0,0,0,1};
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(cost));
    }

}
