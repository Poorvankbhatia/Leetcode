/*

Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).


Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation:
The path with the maximum score is highlighted in yellow.


Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2



Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3

 */
package bfsdfs.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximumMinimumValue {

    private int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int maximumMinimumPath(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        Set<String> visited = new HashSet<>();

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->(b[0]-a[0]));
        queue.add(new int[]{A[m-1][n-1],m-1,n-1});

        int smallest = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            int[] pop = queue.poll();
            if(pop[1]==0 && pop[2]==0) {
                break;
            }
            smallest = Math.min(pop[0], smallest);
            for(int i=0;i<4;i++) {
                int nextX = pop[1]+dir[i][0];
                int nextY = pop[2]+dir[i][1];
                if(nextX>=0 && nextY>=0 && nextX<m && nextY<n && visited.add(nextX+"_"+nextY)) {
                    queue.add(new int[]{A[nextX][nextY],nextX,nextY});
                }
            }
        }

        return smallest;

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {5,4,5},
                {1,2,6},
                {7,4,6}
        };
        System.out.println(new MaximumMinimumValue().maximumMinimumPath(arr));
    }

}
