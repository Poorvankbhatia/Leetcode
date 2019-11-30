/*

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1:
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2:
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four dir: up, down, left and right.

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 19/03/17.
 */
public class Matrix01 {

    int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j]==0) {
                    queue.add(new int[]{i,j,0});
                } else {
                    result[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i=0;i<4;i++) {
                int nextX = poll[0]+dir[i][0];
                int nextY = poll[1]+dir[i][1];
                if(nextX>=0 && nextY>=0 && nextX<m && nextY<n) { // No visited array required as some other 0 can also be close to it.
                    if(result[nextX][nextY]>poll[2]+1) {
                        result[nextX][nextY] = poll[2]+1;
                        queue.add(new int[]{nextX,nextY,poll[2]+1});
                    }
                }
            }
        }
        return result;
    }
}

/*

Simple BFS.
Walls And Gates.

 */