/**
 You are given a 0-indexed m x n integer matrix grid and an integer k.
 You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.

 Return the number of paths where the sum of the elements on the path is divisible by k.
 Since the answer may be very large, return it modulo 109 + 7.

 Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
 Output: 2
 Explanation: There are two paths where the sum of the elements on the path is divisible by k.
 The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
 The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.

 Input: grid = [[0,0]], k = 5
 Output: 1
 Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.

 Example 3:
 Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
 Output: 10
 Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.


 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 5 * 104
 1 <= m * n <= 5 * 104
 0 <= grid[i][j] <= 100
 1 <= k <= 50
 */
package dyanamicprogramming.hard;

public class PathInMatrixSumDivisibleK {

    private final int mod = (int) (Math.pow(10,9) + 7);

    public int numberOfPaths(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        int[][][] modGrid = new int[m][n][k];
        modGrid[m-1][n-1][grid[m-1][n-1]%k]+=1;

        for (int i=m-2,j=n-1;i>=0;i--) {
            for (int p=0;p<k;p++) {
                if(modGrid[i+1][j][p]>0) {
                    modGrid[i][j][((grid[i][j])+p)%k]+=1;
                }
            }
        }

        for (int i=m-1,j=n-2;j>=0;j--) {
            for (int p=0;p<k;p++) {
                if(modGrid[i][j+1][p]>0) {
                    modGrid[i][j][((grid[i][j])+p)%k]+=1;
                }
            }
        }

        for (int i=m-2;i>=0;i--) {
            for (int j=n-2;j>=0;j--) {
                // right
                for (int p=0;p<k;p++) {
                    if(modGrid[i][j+1][p]>0) {
                        modGrid[i][j][(grid[i][j]+p)%k]+=(modGrid[i][j+1][p])%mod;
                    }
                }
                // down
                for (int p=0;p<k;p++) {
                    if(modGrid[i+1][j][p]>0) {
                        modGrid[i][j][(grid[i][j]+p)%k]+=(modGrid[i+1][j][p])%mod;
                    }
                }
            }
        }
        return modGrid[0][0][0]%mod;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0,3,5},
                {3,5,5},
                {1,7,8},
                {7,10,9}
        };
        System.out.println(new PathInMatrixSumDivisibleK().numberOfPaths(grid,5));
    }

}

/**
 * Let dp[i][j][value] represent the number of paths where the sum of the elements on
 * the path has a remainder of value when divided by k.
 */