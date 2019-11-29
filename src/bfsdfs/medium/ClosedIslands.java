/*

Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a
closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.


Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1



Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 */
package bfsdfs.medium;

public class ClosedIslands {

    int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int closedIsland(int[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==0) {
                    count+=dfs(grid,i,j)?1:0;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid,int i,int j) {
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length) {
            return false;
        }
        if(grid[i][j]==1) {
            return true;
        }
        grid[i][j]=1;
        boolean isSurrounded = true;
        for(int k=0;k<4;k++) {
            int nextX = i+dir[k][0];
            int nextY = j+dir[k][1];
            isSurrounded = isSurrounded & dfs(grid,nextX,nextY);
        }
        return isSurrounded;
    }

}

/*

Similar to surrounded regions.

 */