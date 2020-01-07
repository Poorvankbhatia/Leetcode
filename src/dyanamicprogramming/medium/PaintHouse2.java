/*

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different.

You have to paint all the houses such that no two adjacent houses have the same color. The cost of painting each house
with a certain color is represented by a n x k cost matrix.

For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2,
and so on… Find the minimum cost to paint all houses.


Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 29/01/18.
 */
public class PaintHouse2 {

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int preMin = 0, preSecondMin = 0, preColor = -1;
        for (int i = 0; i < costs.length; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecondMin = Integer.MAX_VALUE;
            int curColor = -1;
            for (int j = 0; j < costs[0].length; j++) {
                costs[i][j] = costs[i][j] + (preColor == j ? preSecondMin : preMin);
                if (costs[i][j] < curMin) {
                    curSecondMin = curMin;
                    curMin = costs[i][j];
                    curColor = j;
                } else if (costs[i][j] < curSecondMin) {
                    curSecondMin = costs[i][j];
                }
            }
            preMin = curMin;
            preSecondMin = curSecondMin;
            preColor = curColor;
        }
        return preMin;
    }

    public static void main(String[] args) {
        int[][] cost = new int[][]{
                {2,3,0,5},
                {4,7,9,2},
                {9,8,2,1}
        };
        System.out.println(new PaintHouse2().minCostII(cost));
    }


}

/*

we can also define DP[i][j] as the minimum cost to paint house until i with color j.

Then DP[i][j] = cost[i][j] + min({DP[i - 1][m] | m = 0, 1, 2, …., k and m != j }). We can use an variable preMin to record
the minimum cost of DP[i – 1] [m], and the corresponding color minColor.

One problem is that the minColor (color k with minimum value) can equal to the current color.  In this case we need to use the color
with second smallest cost. So define another variable secondMin to record the second minimum cost of DP[i – 1][m] along with the
corresponding color secondMinColor.

 */