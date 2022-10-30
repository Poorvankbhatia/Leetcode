/*
You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2)
will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

x for x >= 0.
-x for x < 0.

Input: points = [[1,2,3],[1,5,1],[3,1,1]]
Output: 9
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.

Input: points = [[1,5],[2,3],[4,2]]
Output: 11
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.


Constraints:

m == points.length
n == points[r].length
1 <= m, n <= 105
1 <= m * n <= 105
0 <= points[r][c] <= 105

 */
package dyanamicprogramming.medium;

public class MaxPointsWithCost {

    public long maxPoints(int[][] points) {
        long ans = 0;
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[j] += points[i][j];
            }
            for(int j=1;j<n;j++){
                dp[j] = Math.max(dp[j], dp[j-1] - 1);
            }
            for(int j=n-2;j>=0;j--){
                dp[j] = Math.max(dp[j], dp[j+1] - 1);
            }
        }
        for(int i=0;i<n;i++){
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] p = new int[][]{
                {1,5},{2,3},{4,2}
        };
        System.out.println(new MaxPointsWithCost().maxPoints(p));
    }

}

/*

Causes TLE : O(m*n*n)

public long maxPoints(int[][] points) {

        int m = points.length;
        int n = points[0].length;

        long[][] maxPoints = new long[m][n];
        for (int i = 0; i < n; i++) {
            maxPoints[0][i] = points[0][i];
        }
        long ans = 0;
        for (int i=1;i<m;i++) {
            for (int j=0;j<n;j++) {
                long max = Long.MIN_VALUE;
                for (int k=0;k<n;k++) {
                    max = Math.max(max, maxPoints[i-1][k]-(Math.abs(j-k)));
                }
                maxPoints[i][j] = max + points[i][j];
                ans = Math.max(ans, maxPoints[i][j]);
            }
        }

        return ans;

    }


Check:
https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344908/C%2B%2BJavaPython-3-DP-Explanation-with-pictures-O(MN)-Time-O(N)-Space

At a first glance, for the index i in curr, we have:
curr[i] = max(prev[j] - abs(j - i) for j in range(n)) + points[X+1][i], but we don't want to literally compare every
index in prev with every index i in points[X+1], which brings O(N ^ 2) time for a single row and O(N ^ 3) for the
whole grids.


Notice that, for a certain index i, the maximum value for i is a index that could either come from its left,
or its right(inclusive). Thus we can build two arrays, lft and rgt, and
focus on the maximum value only coming from its left or right. Finding the best fit
for a single index i could just cost O(1) time from then on.

Take a look at how we build lft.
Apparently, lft[0] is just prev[0], since there is no other values coming from its left.

For lft[1], we need to make a choice, the value is the larger one between prev[1] or lft[0] - 1,
(considering the index shift so we need to substract 1 from lft[0]).

Assume we do compare: lft[1] - 1 and lft[0] - 2, that is, to get max(lft[2], lft[1] - 1, lft[0] - 2)
Add 1 to last two terms and we have: lft[1], lft[0] - 1.
Looks familiar? That is exactly the previous comparison we made for index=1, we have already selected the maximum
value in the selection on the previous index and saved it in curr[1], shifting all previous candidates by 1
doesn't change the result. We can just safetly use the larger result from the previous index.

Build rgt using the same method.








 */