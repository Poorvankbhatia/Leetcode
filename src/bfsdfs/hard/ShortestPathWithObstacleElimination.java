/*

Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles.
If it is not possible to find such walk return -1.



Example 1:

Input:
grid =
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]],
k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).


Example 2:

Input:
grid =
[[0,1,1],
 [1,1,1],
 [1,0,0]],
k = 1
Output: -1
Explanation:
We need to eliminate at least two obstacles to find such a walk.


Constraints:

grid.length == m
grid[0].length == n
1 <= m, n <= 40
1 <= k <= m*n
grid[i][j] == 0 or 1
grid[0][0] == grid[m-1][n-1] == 0

 */
package bfsdfs.hard;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithObstacleElimination {

    int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        if(m==0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] obstacleThatCanBeEliminated = new int[m][n]; // Number of obstacles that can be eliminated.
        boolean[][] visited = new boolean[m][n];// Check if the index has been visited.
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,k});// x,y,obstacleCount
        obstacleThatCanBeEliminated[0][0]=k; // Initially we can eliminate k obstacles.
        int step=1;
        while(!queue.isEmpty()) {
            int size=queue.size();
            for(int i=0;i<size;i++) {
                int[] poll = queue.poll();
                if(poll[0]==m-1 && poll[1]==n-1) {
                    return step-1;
                }
                int currentObstacleCount = poll[2];
                for(int[] d : dir) {
                    int nextX = poll[0]+d[0];
                    int nextY = poll[1]+d[1];
                    if(nextX>=0 && nextY>=0 && nextX<m && nextY<n &&
                            (obstacleThatCanBeEliminated[nextX][nextY]<currentObstacleCount || !visited[nextX][nextY])
                            //Add the next element to the queue if it has not been visited yet or it has
                            //been visited but the number of obstacles encountered are greater than the current path,
                            // hence we can replace it with the current path.
                            && (grid[nextX][nextY]==0 || (grid[nextX][nextY]==1 && currentObstacleCount>0))
                        /*currentObstacleCount>0 it means the current obstacle can also be eliminated*/) {
                        if(grid[nextX][nextY]==1) {
                            queue.add(new int[]{nextX,nextY,currentObstacleCount-1});
                            obstacleThatCanBeEliminated[nextX][nextY]=currentObstacleCount-1;
                        } else {
                            queue.add(new int[]{nextX,nextY,currentObstacleCount});
                            obstacleThatCanBeEliminated[nextX][nextY]=currentObstacleCount;
                        }
                        visited[nextX][nextY]=true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {0,1,0,0,0,1,0,0},
                {0,1,0,1,0,1,0,1},
                {0,0,0,1,0,0,1,0}
        };
        System.out.println(new ShortestPathWithObstacleElimination().shortestPath(a,1)); // 13
    }

}
