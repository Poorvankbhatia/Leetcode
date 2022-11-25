/*

You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:

0 represents an empty cell,
1 represents an obstacle that may be removed.
You can move up, down, left, or right from and to an empty cell.

Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0)
to the lower right corner (m - 1, n - 1).

Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
Output: 2
Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
It can be shown that we need to remove at least 2 obstacles, so we return 2.
Note that there may be other ways to remove 2 obstacles to create a path.

Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
Output: 0
Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 105
2 <= m * n <= 105
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0



 */
package graph.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumObstacleRemoval {

    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0,0,0});
        // minCost to reach from the ith Index to jth Index.
        int[][] minCost = new int[m][n];
        for (int[] r : minCost) {
            Arrays.fill(r,Integer.MAX_VALUE);
        }
        minCost[0][0]=grid[0][0]==1?1:0;
        while (!pq.isEmpty()) {
            int[] poll= pq.poll();
            int currentWeight = poll[2];
            if(poll[0]==m-1 && poll[1]==n-1) return currentWeight;
            for (int[] d : dir) {
                int nextX = poll[0]+d[0];
                int nextY = poll[1]+d[1];
                if(nextX<0 || nextY<0 || nextX>=m || nextY>=n || minCost[nextX][nextY]==0) continue;
                int nextWeight = grid[nextX][nextY]==1?1:0;
                // if the current minCost to reach nextX,nextY > current path cost (replace it.)
                if(minCost[nextX][nextY]>nextWeight+currentWeight) {
                    minCost[nextX][nextY]=nextWeight+currentWeight;
                    pq.add(new int[]{nextX,nextY,minCost[nextX][nextY]});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {0,1,0,0,0},
                {0,1,0,1,0},
                {0,0,0,1,0}
        };
        System.out.println(new MinimumObstacleRemoval().minimumObstacles(a));
    }

}

/*

Simple Dijikstra's

Model the grid as a graph where cells are nodes and edges are between adjacent cells.
Edges to cells with obstacles have a cost of 1 and all other edges have a cost of 0.

 */