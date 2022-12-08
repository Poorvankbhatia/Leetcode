/*

You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is
(0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).


Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0


 */
package bfsdfs.hard;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithObstacleElimination {

    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1, 0}};
    public int shortestPath(int[][] grid, int k) {
        if(grid==null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0});
        int ans = 0;
        boolean[][][] visited = new boolean[m][n][k+1];
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                if(x==m-1 && y==n-1) return ans;
                for (int[] d : dir) {
                    int nextX = x+d[0];
                    int nextY = y+d[1];
                    int z = current[2];
                    if(nextX<0 || nextY<0 || nextX>=m || nextY>=n) {
                        continue;
                    }
                    if(grid[nextX][nextY]==1) {
                        z++;
                    }
                    if(z<=k && !visited[nextX][nextY][z]) {
                        visited[nextX][nextY][z]=true;
                        queue.offer(new int[]{nextX,nextY,z});
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,0,0},
                {1,1,0},
                {0,1,1},
                {0,0,0}
        };
        System.out.println(new ShortestPathWithObstacleElimination().shortestPath(a,1));
    }

}
