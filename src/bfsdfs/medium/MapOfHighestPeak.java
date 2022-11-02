/*
You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

If isWater[i][j] == 0, cell (i, j) is a land cell.
If isWater[i][j] == 1, cell (i, j) is a water cell.
You must assign each cell a height in a way that follows these rules:

The height of each cell must be non-negative.
If the cell is a water cell, its height must be 0.
Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another
cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
Find an assignment of heights such that the maximum height in the matrix is maximized.

Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple
solutions, return any of them.

Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.

Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.


Constraints:

m == isWater.length
n == isWater[i].length
1 <= m, n <= 1000
isWater[i][j] is 0 or 1.
There is at least one water cell.

 */
package bfsdfs.medium;

import java.util.*;

public class MapOfHighestPeak {

    public int[][] highestPeak(int[][] isWater) {

        int m = isWater.length;
        int n = isWater[0].length;

        int[][] result = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(isWater[i][j]==1) {
                    result[i][j]=0;
                    visited[i][j]=true;
                    queue.add(new int[]{i, j});
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        bfs(result,isWater,m,n,queue,visited);

        return result;

    }

    private int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    private void bfs(int[][] result, int[][] isWater, int m, int n, Queue<int[]> queue,boolean[][] visited) {
        int val = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                int[] poll = queue.poll();
                for (int[] d : dirs) {
                    int nextX = d[0]+poll[0];
                    int nextY = d[1]+poll[1];
                    if(nextX>=0 && nextY>=0 && nextX<m && nextY<n && !visited[nextX][nextY] && isWater[nextX][nextY]!=1) {
                        visited[nextX][nextY] = true;
                        if(result[nextX][nextY] > val) {
                            result[nextX][nextY] = val;
                        }
                        queue.add(new int[]{nextX,nextY});
                    }
                }
            }
            val++;
        }

    }


    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,1},{0,0}
        };
        System.out.println(Arrays.toString(new MapOfHighestPeak().highestPeak(a)));
    }

}

/*
BFS from all the water nodes.
 */