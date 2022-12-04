/*
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.

Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.

Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],
["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.


 */
package bfsdfs.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathToGetFood {

    private int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int x = 0;
        int y = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]=='*') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;
        int ans = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] pop = queue.poll();
                if(grid[pop[0]][pop[1]]=='#') return ans;
                for(int[] d : dir) {
                    int nextX = pop[0]+d[0];
                    int nextY = pop[1]+d[1];
                    if(nextX>=0 && nextY>=0 && nextX<m && nextY<n && grid[nextX][nextY]!='X' && !visited[nextX][nextY]) {
                        visited[nextX][nextY]=true;
                        queue.add(new int[]{nextX,nextY});
                    }
                }
            }
            ans++;
        }
        return -1;
    }

}

/*
BFS O(mn)
 */