/*

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

 */
package bfsdfs.medium;

/**
 * Created by poorvank.b on 04/06/18.
 */
public class BombEnemy {

    class Solution {
        public int maxKilledEnemies(char[][] grid) {
            int m = grid.length;
            if(m==0) {
                return 0;
            }
            int n = grid[0].length;
            int res=0;
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(grid[i][j]=='0') {
                        int ans = util(i,j,grid);
                        //System.out.println(ans);
                        res = Math.max(res,ans);
                    }
                }
            }
            return res;
        }

        private int util(int x,int y,char[][] grid) {
            int xCopy = x,yCopy=y;
            int count=0;
            //up
            while(xCopy>0) {
                if(grid[xCopy-1][y]=='W') {
                    break;
                } else if(grid[xCopy-1][y]=='E') {
                    count++;
                }
                xCopy--;
            }
            //down
            xCopy=x;
            while(xCopy<grid.length-1) {
                if(grid[xCopy+1][y]=='W') {
                    break;
                } else if(grid[xCopy+1][y]=='E') {
                    count++;
                }
                xCopy++;
            }
            //left
            while(yCopy>0) {
                if(grid[x][yCopy-1]=='W') {
                    break;
                } else if(grid[x][yCopy-1]=='E') {
                    count++;
                }
                yCopy--;
            }
            yCopy=y;
            while(yCopy<grid[0].length-1) {
                if(grid[x][yCopy+1]=='W') {
                    break;
                } else if(grid[x][yCopy+1]=='E') {
                    count++;
                }
                yCopy++;
            }
            return count;
        }
    }

}

/*

Above time complexity  :   O(mn*(m+n))

O(mn)

public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] count = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'E') tmp++;
                if (grid[i][j] == 'W') tmp = 0;
                if (grid[i][j] == '0') {
                    count[i][j] += tmp;
                    res = Math.max(count[i][j], res);
                }
            }
            tmp = 0;
            for (int j = n-1; j >= 0; j--) {
                if (grid[i][j] == 'E') tmp++;
                if (grid[i][j] == 'W') tmp = 0;
                if (grid[i][j] == '0') {
                    count[i][j] += tmp;
                    res = Math.max(count[i][j], res);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int tmp = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 'E') tmp++;
                if (grid[i][j] == 'W') tmp = 0;
                if (grid[i][j] == '0') {
                    count[i][j] += tmp;
                    res = Math.max(count[i][j], res);
                }
            }
            tmp = 0;
            for (int i = m-1; i >= 0; i--) {
                if (grid[i][j] == 'E') tmp++;
                if (grid[i][j] == 'W') tmp = 0;
                if (grid[i][j] == '0') {
                    count[i][j] += tmp;
                    res = Math.max(count[i][j], res);
                }
            }
        }
        return res;
    }

 */