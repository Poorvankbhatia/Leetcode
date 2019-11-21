/*

On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent
square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time.
Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 -0- -1-  -2-  -3-  -4-
24   23   22   21   -5-
-12 -13- -14- -15- -16-
-11  17   18   19   20
-10  -9-  -8-  -7-  -6-

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].

 */
package bfsdfs.hard;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by poorvank.b on 04/02/18.
 */
public class SwimInRisingWater {

    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> grid[o[0]][o[1]]));
        pq.add(new int[]{0,0});
        HashSet<String> visited = new HashSet<>();
        int max=0;
        while(!pq.isEmpty()) {
            int[] poll = pq.poll();
            int x = poll[0];
            int y = poll[1];
            max = Math.max(grid[x][y],max);
            if(x==n-1 && y==n-1) {
                return max;
            }
            visited.add(x+"_"+y);
            for(int[] d: dir) {
                int nextX = x+d[0];
                int nextY = y+d[1];
                if(nextX>=0 && nextY>=0 && nextX<n && nextY<n && visited.add(nextX+"_"+nextY)) {
                    pq.add(new int[]{nextX,nextY});
                }
            }
        }
        return -1;
    }

}

/*

a priority queue of which square we can walk in next. We always walk in the smallest one that is 4-directionally adjacent to ones we've visited.

When we reach the target, the largest number we've visited so far is the answer.


O(N^2logN). We may expand O(N^2) nodes, and each one requires O(logN) time to perform the heap operations

 */