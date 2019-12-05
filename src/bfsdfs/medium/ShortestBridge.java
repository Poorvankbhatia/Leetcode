/*

In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)



Example 1:

Input: [[0,1],[1,0]]
Output: 1
Example 2:

Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1


Note:

1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 04/11/18.
 */
public class ShortestBridge {

    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int k=2;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(A[i][j]==1 && !visited[i][j]) {
                    dfs(A,i,j,visited,k++);
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(A[i][j]==2) {
                    queue.add(new int[]{i,j,0});
                }
            }
        }
        return bfs(queue,A);
    }

    private int bfs(Queue<int[]> queue,int[][] A) {
        boolean[][] visited = new boolean[A.length][A[0].length];
        visited[queue.peek()[0]][queue.peek()[1]]=true;
        while(!queue.isEmpty())  {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] pop = queue.poll();
                if(A[pop[0]][pop[1]]==3) {
                    return pop[2]-1;
                }
                for(int k=0;k<4;k++) {
                    int nextX = dir[k][0]+pop[0];
                    int nextY = dir[k][1]+pop[1];
                    if(nextX>=0 && nextY>=0 && nextX<A.length && nextY<A[0].length && !visited[nextX][nextY]) {
                        queue.add(new int[]{nextX,nextY,pop[2]+1});
                        visited[nextX][nextY]=true;
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(int[][] A,int x,int y,boolean[][] visited,int k) {
        A[x][y]=k;
        visited[x][y]=true;
        for(int i=0;i<4;i++) {
            int nextX = dir[i][0]+x;
            int nextY = dir[i][1]+y;
            if(nextX>=0 && nextY>=0 && nextX<A.length && nextY<A[0].length && !visited[nextX][nextY] && A[nextX][nextY]==1) {
                dfs(A,nextX,nextY,visited,k);
            }
        }
    }

}

/*

Using DFS mark the 2 connected components with different (connected component one with value 2 and connected component two with value 3).
Now the problem boils down to finding the shortest distance from any of the node (2) to Node(3).
* Add all the positions of one of the connected components(2) into queue and run BFS to find the shortest distance.


Conceptually, our method is very straightforward: find both islands, then for one of the islands, keep "growing" it by
1 until we touch the second island.

We can use a depth-first search to find the islands, and a breadth-first search to "grow" one of them. This leads to a
verbose but correct solution.

Algorithm

To find both islands, look for a square with a 1 we haven't visited, and dfs to get the component of that region. Do this twice.
After, we have two components source and target.

To find the shortest bridge, do a BFS from the nodes source. When we reach any node in target, we will have found the shortest distance.

 */