/*

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

 */
package bfsdfs.medium;

/**
 * Created by poorvank on 20/10/16.
 */
public class NumberOfIslands {

    private int[] xMove = new int[]{0,1,-1,0};
    private int[] yMOve = new int[]{1,0,0,-1};

    public int numIslands(char[][] grid) {

        int count = 0;
        if(grid.length==0) {
            return count;
        }

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                if(grid[i][j]=='1' && !visited[i][j]) {
                    numIslandsUtil(grid,row,col,i,j,visited);
                    count++;
                }
            }
        }

        return count;

    }


    private void numIslandsUtil(char[][] grid,int row,int col,int x,int y,boolean[][] visited) {

        visited[x][y] = true;

        for (int i=0;i<4;i++) {

            int newX = x+xMove[i];
            int newY = y+yMOve[i];

            if(isSafe(newX,newY,row,col,visited,grid)) {
                numIslandsUtil(grid,row,col,newX,newY,visited);
            }

        }

    }

    private boolean isSafe(int x,int y,int row,int col,boolean[][] visited,char[][] grid) {
        return (x>=0 && y>=0 && x<row && y<col && !visited[x][y] && grid[x][y]=='1');
    }

}

/*

BFS:

int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m==0) {
            return 0;
        }
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int ans=0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]=='1' && !visited[i][j]) {
                    bfs(i,j,visited,grid);
                    ans++;
                }
            }
        }

        return ans;
    }

    private void bfs(int i,int j,boolean[][] visited,char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        visited[i][j]=true;
        while(!queue.isEmpty()) {
            int[] pop = queue.poll();
            for(int k=0;k<4;k++) {
                int nextX = pop[0]+dir[k][0];
                int nextY = pop[1]+dir[k][1];
                if(nextX>=0 && nextY>=0 && nextX<grid.length && nextY<grid[0].length && !visited[nextX][nextY] && grid[nextX][nextY]=='1') {
                    queue.add(new int[]{nextX,nextY});
                    visited[nextX][nextY]=true;
                }
            }
        }

    }

 */