/*

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.


 */
package bfsdfs.medium;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    private int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int freshCount=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i][j]==2) {
                    queue.add(new int[]{i,j});
                }
                if(grid[i][j]==1) {
                    freshCount++;
                }
            }
        }

        if(freshCount==0) {
            return 0;
        }

        int min=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                int[] current = queue.poll();
                for (int k=0;k<4;k++) {
                    int nextX = current[0]+dir[k][0];
                    int nextY = current[1]+dir[k][1];
                    if(nextX>=0 && nextY>=0 && nextX<m && nextY<n && grid[nextX][nextY]==1) {
                        grid[nextX][nextY]=2;
                        queue.add(new int[]{nextX,nextY});
                        freshCount--;
                    }
                }
            }
            min++;
        }

        return freshCount==0?min-1:-1;

    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        System.out.println(new RottenOranges().orangesRotting(grid));
    }

}
