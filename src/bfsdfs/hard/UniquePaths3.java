/*

On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



Example 1:

Input: [[1,0,0,0],
        [0,0,0,0],
        [0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation:
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.


Note:

1 <= grid.length * grid[0].length <= 20

 */
package bfsdfs.hard;

/**
 * Created by poorvank.b on 20/01/19.
 */
public class UniquePaths3 {
    private int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int uniquePathsIII(int[][] grid) {
        if(grid==null || grid.length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        int[] start = new int[2];
        int[] end = new int[2];
        int space=0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) {
                    space++;
                } else if(grid[i][j]==1) {
                    start[0]=i;
                    start[1]=j;
                } else if(grid[i][j]==2) {
                    end[0]=i;
                    end[1]=j;
                }
            }
        }

        return util(grid,start,end,space,-1);

    }

    private int util(int[][] grid,int[] start,int[] end,int space,int current) {

        if(start[0]==end[0] && start[1]==end[1] && current==space) {
            return 1;
        }

        if(start[0]==end[0] && start[1]==end[1]) {
            return 0;
        }

        int count=0;
        int val = grid[start[0]][start[1]];
        grid[start[0]][start[1]]=-1;
        for(int i=0;i<4;i++) {
            int nextX = start[0]+dir[i][0];
            int nextY = start[1]+dir[i][1];
            if(nextX>=0 && nextY>=0 && nextX<grid.length && nextY<grid[0].length && grid[nextX][nextY]!=-1) {
                count+=util(grid,new int[]{nextX,nextY},end,space,current+1);
            }
        }

        grid[start[0]][start[1]]=val;
        return count;

    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,2}
        };
        System.out.println(new UniquePaths3().uniquePathsIII(a));
    }

}
