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

        //This is a bfs solution, bfs needs queue generally.

        if(grid==null || grid.length==0) {
            return -1;
        }

        int row = grid.length;
        int col = grid[0].length;
        int buildingCount = 0;

        int[][] dis = new int[row][col]; // distance sum of all bulding to dis[x][y];
        int[][] num = new int[row][col]; // how many buildings can reach num[x][y]

        for(int i=0 ; i< row; i++){
            for(int j = 0; j< col; j++){
                if(grid[i][j] == 1){
                    buildingCount++;
                    bfs(grid, dis, num, i, j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(grid[i][j] == 0 && dis[i][j] != 0 && num[i][j] == buildingCount){
                    min = Math.min(min, dis[i][j]);
                }
            }
        }
        if(min < Integer.MAX_VALUE) return min;
        return -1;
    }

    private void bfs(int[][] grid, int[][] dis, int[][] num, int x, int y){
        int row = grid.length;
        int col = grid[0].length;
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[row][col];
        int dist = 0;
        while(!queue.isEmpty()){
            dist++;
            int size = queue.size();// all size number of node, their neigbors belongs to next dist, which for distance.
            for(int i=0 ; i<size; i++){
                int[] top = queue.poll();
                for(int j=0; j< 4;j++){
                    int k = top[0] + dir[j][0];
                    int l = top[1] + dir[j][1];
                    if(k>=0 && k< row && l >= 0 && l < col && grid[k][l] == 0 && !visited[k][l]){
                        visited[k][l] = true;
                        dis[k][l] += dist;
                        num[k][l]++;
                        queue.add(new int[]{k,l});
                    }
                }
            }
        }

    }
}

/*

Understand the problem:
A BFS problem. Search from each building and calculate the distance to the building. One thing to note is an empty land must
 be reachable by all buildings. To achieve this, maintain an array of counters. Each time we reach a empty land from a building,
  increase the counter. Finally, a reachable point must have the counter equaling to the number of buildings.

 */