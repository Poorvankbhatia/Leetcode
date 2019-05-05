/*

Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.

Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is
the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.

Return the smallest possible total score that you can achieve with some triangulation of the polygon.



Example 1:

Input: [1,2,3]
Output: 6
Explanation: The polygon is already triangulated, and the score of the only triangle is 6.

Input: [3,7,4,5]
Output: 144
Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
Example 3:

Input: [1,3,1,4,1,5]
Output: 13
Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.

 */
package dyanamicprogramming.medium;

public class MinCostTringulation {

    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (j>=i+2) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i+1; k < j; k++) {
                        int val = (A[i]*A[j]*A[k])+dp[i][k]+dp[k][j];
                        dp[i][j] =Math.min(dp[i][j],val);
                    }
                }
            }
        }
        return  dp[0][n-1];
    }

    public static void main(String[] args) {
        int[] val = new int[]{1,3,1,4,1,5};
        System.out.println(new MinCostTringulation().minScoreTriangulation(val));
    }

}

/*

The idea is to divide the polygon into three parts: a single triangle, the sub-polygon to the left,
and the sub-polygon to the right. We try all possible divisions like this and find the one that minimizes
the cost of the triangle plus the cost of the triangulation of the two sub-polygons.

Let Minimum Cost of triangulation of vertices from i to j be min(i, j)
If j <= i + 2 Then
  min(i, j) = 0
Else
  min(i, j) = Math.min { min(i, k) + min(k, j) + (A[i]*A[j]*A[k]) }  i+1<=k<=j-1


Cost of a triangle formed by edges (i, j), (j, k) and (k, i) is
  cost(i, j, k)  = dist(i, j) + dist(j, k) + dist(k, i)

 */