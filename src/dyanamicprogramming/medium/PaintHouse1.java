/*

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green,
 and so on... Find the minimum cost to paint all houses.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 29/01/18.
 */
public class PaintHouse1 {

    public int minCost(int[][] costs) {

        if(costs==null || costs.length==0) {
            return 0;
        }

        for (int i=1;i<costs.length;i++) {
            costs[i][0] = Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1] = Math.min(costs[i-1][2],costs[i-1][0]);
            costs[i][2] = Math.min(costs[i-1][1],costs[i-1][0]);
        }

        int m = costs.length;
        return Math.min(costs[m-1][0],Math.min(costs[m-1][1],costs[m-1][2]));

    }

}

/*

The 1st row is the prices for the 1st house, we can change the matrix to present sum of prices from the 2nd row. i.e,
the costs[1][0] represent minimum price to paint the second house red plus the 1st house.

 */