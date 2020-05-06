/*
Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.

You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.


Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).

Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).

Input: grid = [[1,1,1,1,1,1,3]]
Output: true

Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
Output: true


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6
 */
package bfsdfs.medium;

import java.util.LinkedList;
import java.util.Queue;

public class CheckValidPath {

    public boolean hasValidPath(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        visited[0][0]=true;

        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            if(pop[0]==m-1 && pop[1]==n-1) {
                return true;
            }
            int value = grid[pop[0]][pop[1]];
            int x = pop[0];
            int y = pop[1];
            switch (value) {
                case 2 :
                    if(isValid(x+1,y,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x+1,y});
                        visited[x+1][y]=true;
                    }
                    if(isValid(x-1,y,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x-1,y});
                        visited[x-1][y]=true;
                    }
                    break;
                case 1:
                    if(isValid(x,y+1,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x,y+1});
                        visited[x][y+1]=true;
                    }
                    if(isValid(x,y-1,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x,y-1});
                        visited[x][y-1]=true;
                    }
                    break;
                case 3:
                    if(isValid(x+1,y,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x+1,y});
                        visited[x+1][y]=true;
                    }
                    if(isValid(x,y-1,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x,y-1});
                        visited[x][y-1]=true;
                    }
                    break;
                case 4:
                    if(isValid(x+1,y,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x+1,y});
                        visited[x+1][y]=true;
                    }
                    if(isValid(x,y+1,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x,y+1});
                        visited[x][y+1]=true;
                    }
                    break;
                case 5:
                    if(isValid(x-1,y,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x-1,y});
                        visited[x-1][y]=true;
                    }
                    if(isValid(x,y-1,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x,y-1});
                        visited[x][y-1]=true;
                    }
                    break;
                case 6:
                    if(isValid(x-1,y,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x-1,y});
                        visited[x-1][y]=true;
                    }
                    if(isValid(x,y+1,m,n,visited,x,y,grid)) {
                        queue.add(new int[]{x,y+1});
                        visited[x][y+1]=true;
                    }
                    break;
            }
        }

        return false;

    }

    private boolean isValid(int x,int y,int m,int n,boolean[][] visited,int oldX,int oldY,int[][] grid) {
         if(x<m && y<n && x>=0 && y>=0 && !visited[x][y]) {
             int newVal = grid[x][y];
             int oldVal = grid[oldX][oldY];
             switch (oldVal) {
                 case 1:
                     if(oldY+1==y) {
                         return newVal==1 || newVal==5 || newVal==3;
                     } else {
                         return newVal==1 || newVal==4 || newVal==6;
                     }
                 case 2:
                     if(oldX+1==x) {
                         return newVal==2 || newVal==5 || newVal==6;
                     } else {
                         return newVal==2 || newVal==3 || newVal==4;
                     }
                 case 3:
                     if(oldX+1==x) {
                         return newVal==2 || newVal==5 || newVal==6;
                     } else {
                         return newVal==1 || newVal==4 || newVal==6;
                     }
                 case 4:
                     if(oldY+1==y) {
                         return newVal==1 || newVal==3 || newVal==5;
                     } else {
                         return newVal==2 || newVal==5 || newVal==6;
                     }
                 case 5:
                     if(oldY-1==y) {
                         return newVal==1 || newVal==4 || newVal==6;
                     } else {
                         return newVal==2 || newVal==3 || newVal==4;
                     }
                 case 6:
                     if(oldY+1==y) {
                         return newVal==1 || newVal==3 || newVal==5;
                     } else {
                         return newVal==2 || newVal==3 || newVal==4;
                     }
             }
         }
         return false;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {6,1,3},
                {4,1,5}
        };
        System.out.println(new CheckValidPath().hasValidPath(arr));
    }

}

/*

Better Sol:

class Solution {
    int[][][] dirs = {
                {{0, -1}, {0, 1}},
                {{-1, 0}, {1, 0}},
                {{0, -1}, {1, 0}},
                {{0, 1}, {1, 0}},
                {{0, -1}, {-1, 0}},
                {{0, 1}, {-1, 0}}
    };
    //the idea is you need to check port direction match, you can go to next cell and check whether you can come back.
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            int num = grid[x][y] - 1;
            for (int[] dir : dirs[num]) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) continue;
                //go to the next cell and come back to orign to see if port directions are same
                for (int[] backDir : dirs[grid[nx][ny] - 1])
                    if (nx + backDir[0] == x && ny + backDir[1] == y) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
            }
        }
        return visited[m - 1][n - 1];
    }
}

 */
