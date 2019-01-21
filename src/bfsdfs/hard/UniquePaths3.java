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
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int uniquePathsIII(int[][] grid) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int spaces = 1;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    startPoint[0] = i;
                    startPoint[1] = j;
                }else if(grid[i][j] == 2){
                    endPoint[0] = i;
                    endPoint[1] = j;
                }else if(grid[i][j] == 0){
                    spaces++;
                }
            }
        }

        return dfs(grid, startPoint, endPoint, 0, spaces);
    }



    public int dfs(int[][] grid, int[] currentPos, int[] des, int currentStep, int totalSteps){
        if(currentPos[0]<0 || currentPos[0]>=grid.length || currentPos[1]<0 || currentPos[1]>=grid[0].length || currentStep > totalSteps){
            return 0;
        }
        int curX = currentPos[0];
        int curY = currentPos[1];
        if(curX == des[0] && curY == des[1] && currentStep == totalSteps){
            return 1;
        }else if(grid[curX][curY] == -1 || (grid[curX][curY] == 1 && currentStep!=0) || grid[curX][curY] == 2){
            return 0;
        }

        int count = 0;
        grid[curX][curY] = -1;
        for(int[] dir: dirs){
            int[] next = new int[]{curX + dir[0], curY + dir[1]};
            count += dfs(grid, next, des, currentStep+1, totalSteps);
        }
        grid[curX][curY] = 0;
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
