/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
 */
package bfsdfs.easy;

/**
 * Created by poorvank on 15/10/17.
 */
public class MaxArea {

    int[] dirX = new int[]{-1,1,0,0};
    int[] dirY = new int[]{0,0,1,-1};

    public int maxAreaOfIsland(int[][] grid) {

        int m = grid.length;
        if(m==0) {
            return 0;
        }
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int max = Integer.MIN_VALUE;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(!visited[i][j] && grid[i][j]==1) {
                    max=Math.max(max,dfs(grid,visited,i,j,m,n));
                }
            }
        }

        return max==Integer.MIN_VALUE?0:max;

    }

    private int dfs(int[][] grid,boolean[][] visited,int x,int y,int m,int n) {

        visited[x][y] = true;

        int count=1;
        for (int k=0;k<4;k++) {
            int nextX = x+dirX[k];
            int nextY = y+dirY[k];
            if(isSafe(nextX,nextY,grid,m,n,visited)) {
                count += dfs(grid,visited,nextX,nextY,m,n);
            }
        }

        return count;

    }

    private boolean isSafe(int x,int y,int[][] grid,int m,int n,boolean[][] visited) {
        return (x>=0 && y>=0 && x<m && y<n && grid[x][y]==1 && !visited[x][y]);
    }


    public static void main(String[] args) {
        int[][] arr =  new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                                    {0,1,1,0,1,0,0,0,0,0,0,0,0},
                                    {0,1,0,0,1,1,0,0,1,0,1,0,0},
                                    {0,1,0,0,1,1,0,0,1,1,1,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,1,0,0},
                                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                                    {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println(new MaxArea().maxAreaOfIsland(arr));

    }

}


/*

O(mn)

Shorter implementation dfs:

 public int maxAreaOfIsland(int[][] grid) {
        int max_area = 0;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] == 1)max_area = Math.max(max_area, AreaOfIsland(grid, i, j));
        return max_area;
    }

    public int AreaOfIsland(int[][] grid, int i, int j){
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;
            return 1 + AreaOfIsland(grid, i+1, j) + AreaOfIsland(grid, i-1, j) + AreaOfIsland(grid, i, j-1) + AreaOfIsland(grid, i, j+1);
        }
        return 0;
    }




 */