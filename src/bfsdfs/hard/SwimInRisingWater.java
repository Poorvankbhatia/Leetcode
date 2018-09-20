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

    private int[] dirX = {0,1,-1,0};
    private int[] dirY = {1,0,0,-1};

    public int swimInWater(int[][] grid) {

        if(grid==null || grid.length==0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;


        HashSet<Integer> visited = new HashSet<>();

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return grid[o1/n][o1%n]-grid[o2/n][o2%n];
            }
        });


        priorityQueue.add(0);

        int ans=0;
        while (!priorityQueue.isEmpty()) {
            int poll = priorityQueue.poll();
            int r = poll/n;
            int c = poll%n;
            ans = Math.max(ans,grid[r][c]);

            if(r==m-1 && c ==n-1) {
                return ans;
            }

            for (int i=0;i<4;i++) {
                int nextR = r+dirX[i];
                int nextC = c+dirY[i];

                int next = nextR*n+nextC;

                if(nextR>=0 && nextR<m && nextC>=0 && nextC<n && !visited.contains(next)) {
                    priorityQueue.add(next);
                    visited.add(next);
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