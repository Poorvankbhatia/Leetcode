/*

Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.

Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.

The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component,
or on the boundary of the grid (the first or last row or column).

Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.



Example 1:

Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
Output: [[3, 3], [3, 2]]
Example 2:

Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
Output: [[1, 3, 3], [2, 3, 3]]
Example 3:

Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]


Note:

1 <= grid.length <= 50
1 <= grid[0].length <= 50
1 <= grid[i][j] <= 1000
0 <= r0 < grid.length
0 <= c0 < grid[0].length
1 <= color <= 1000

 */
package bfsdfs.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ColoringBorder {

    int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int val = grid[r0][c0];
        boolean[][] visited = new boolean[m][n];
        queue.add(new int[]{r0,c0});
        visited[r0][c0]=true;
        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            for(int i=0;i<4;i++) {
                int nextX = poll[0]+dir[i][0];
                int nextY = poll[1]+dir[i][1];
                boolean isValid = validIndex(nextX,nextY,m,n);
                if(isValid && grid[nextX][nextY]==val && !visited[nextX][nextY]) {
                    queue.add(new int[]{nextX,nextY});
                    visited[nextX][nextY]=true;
                } else if(!isValid || grid[nextX][nextY] != val && !visited[nextX][nextY]) {
                    grid[poll[0]][poll[1]]=color;
                }
            }
        }
        return grid;
    }

    private boolean validIndex(int x, int y,int m, int n) {
        return x>=0 && y>=0 && x<m && y<n;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,2,1,2},
                {2,2,2,1},
                {1,2,2,2}
        };
        new ColoringBorder().colorBorder(a,1,2,5);
    }

}
