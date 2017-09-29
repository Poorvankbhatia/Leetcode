/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.

For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 */
package bfsdfs.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank.b on 26/09/17.
 */
public class ShortestDistance {

    public int shortestDistance(int[][] grid) {

        if(grid==null || grid.length==0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Distance array fills the distance of each "0"(house) from all the buildings
        int[][] distance = new int[m][n];
        // Indicates if the house can be reached by all the buildings
        int[][] reach = new int[m][n];
        int numBuildings=0;

        // step 1: BFS and calculate the min dist from each building
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    Queue<Integer> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[m][n];
                    shortestDistance(distance,i,j,reach,queue,0,visited,grid);
                    numBuildings++;
                }
            }
        }

        // step 2: calculate the minimum distance
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == numBuildings) {
                    minDist = Math.min(minDist, distance[i][j]);
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;

    }


    private void shortestDistance(int[][] distance,int x,int y,int[][] reach,Queue<Integer> queue,int currentDistance,boolean[][] visited,int[][] grid) {

        fill(distance,x, y, x, y,visited,queue, currentDistance, reach,grid);

        int m = grid.length;
        int n = grid[0].length;

        while (!queue.isEmpty()) {
            int size = queue.size();
            currentDistance++;
            for (int sz = 0; sz < size; sz++) {
                int cord = queue.poll();
                int i = cord / n;
                int j = cord % n;

                fill(distance,x, y, i - 1, j, visited, queue, currentDistance, reach,grid);
                fill(distance,x, y, i + 1, j, visited, queue, currentDistance, reach,grid);
                fill(distance,x, y, i, j-1, visited, queue, currentDistance, reach,grid);
                fill(distance,x, y, i, j+1, visited, queue, currentDistance, reach,grid);
            }
        }

    }

    private void fill(int[][] distance,int x,int origX,int origY,int y,boolean[][] visited,Queue<Integer> queue,int currentDist,int[][] reach,int[][] grid){
        int m = distance.length;
        int n = distance[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
            return;
        }

        if ((x != origX || y != origY) && grid[x][y] != 0) {
            return;
        }

        visited[x][y] = true;

        distance[x][y] += currentDist;
        reach[x][y]++;

        queue.offer(x * n + y);

    }

}

/*

Understand the problem:
A BFS problem. Search from each building and calculate the distance to the building. One thing to note is an empty land must
 be reachable by all buildings. To achieve this, maintain an array of counters. Each time we reach a empty land from a building,
  increase the counter. Finally, a reachable point must have the counter equaling to the number of buildings.

 */