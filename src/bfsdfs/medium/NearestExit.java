/*
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column
of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot
step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell
that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.

Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.

maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.
 */
package bfsdfs.medium;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExit {

    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        boolean[][] visited = new boolean[m][n];
        int ans = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] poll = queue.poll();
                //System.out.println(Arrays.toString(poll));
                if(poll[0]==0 || poll[0]==m-1 || poll[1]==0 || poll[1]==n-1) {
                    if(!(poll[0]+""+poll[1]).equals(entrance[0]+""+entrance[1])) return ans;
                }
                for (int[] d : dir) {
                    int x = poll[0] + d[0];
                    int y = poll[1] + d[1];
                    if(x>=0 && y>=0 && x<m && y<n && maze[x][y]!='+' && !visited[x][y]) {
                        visited[x][y]=true;
                        queue.add(new int[]{x,y});
                    }
                }
            }
            ans++;
        }
        return -1;
    }

}
