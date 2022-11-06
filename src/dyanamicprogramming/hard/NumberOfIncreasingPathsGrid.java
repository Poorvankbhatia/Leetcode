/*

You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell.
 Since the answer may be very large, return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.


Input: grid = [[1,1],[3,4]]
Output: 8
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
- Paths with length 3: [1 -> 3 -> 4].
The total number of paths is 4 + 3 + 1 = 8.
Example 2:

Input: grid = [[1],[2]]
Output: 3
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [2].
- Paths with length 2: [1 -> 2].
The total number of paths is 2 + 1 = 3.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
1 <= grid[i][j] <= 105

 */
package dyanamicprogramming.hard;

public class NumberOfIncreasingPathsGrid {

    private int mod = (int) (Math.pow(10,9))+7;

    public int countPaths(int[][] grid) {

        int m =grid.length;
        int n = grid[0].length;

        long[][] pathCount = new long[m][n];
        boolean[][] visited = new boolean[m][n];
        long ans = 0;
        for (int i = 0; i < m;i++) {
            for (int j=0;j<n;j++) {
                ans = (ans%mod + dfs(i,j,visited,pathCount,grid)%mod)%mod;
            }
        }

        return (int) ans;
    }

    private int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    private long dfs(int i,int j,boolean[][] visited, long[][] pathCount,int[][] grid) {
        visited[i][j]=true;
        if(pathCount[i][j]>0) {
            visited[i][j]=false;
            return pathCount[i][j];
        }
        long ans = 1;
        for(int[] d : dir) {
            int nextI = i+d[0];
            int nextJ = j+d[1];
            if(nextI>=0 && nextJ>=0 && nextI<pathCount.length && nextJ<pathCount[0].length
                    && grid[nextI][nextJ]>grid[i][j] && !visited[nextI][nextJ]) {
                ans = (ans%mod+ dfs(nextI, nextJ,visited,pathCount,grid)%mod)%mod;
            }
        }
        visited[i][j] = false;
        pathCount[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1},{4},{9}
        };
        System.out.println(new NumberOfIncreasingPathsGrid().countPaths(a));
    }

}

/*

Similar to the longest increasing path.
 */