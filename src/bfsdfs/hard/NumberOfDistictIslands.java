/*

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally
(horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be
 translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.

 */
package bfsdfs.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 22/10/17.
 */
public class NumberOfDistictIslands {

    int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    // The path taken by our depth-first search will be the same if and only if the shape is the same.
    Set<String> shapeHash = new HashSet<>();

    public int numDistinctIslands(int[][] grid) {

        if(grid==null || grid.length==0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i][j]==1 && !visited[i][j]) {
                    String hash = calculateIslands(grid,m,n,i,j,visited,new StringBuilder());
                    System.out.println("hash - "+ hash);
                    if(!shapeHash.contains(hash)) {
                        shapeHash.add(hash);
                    }
                }
            }
        }

        return shapeHash.size();

    }

    private String calculateIslands(int[][] grid,int m,int n,int x,int y,boolean[][] visited,StringBuilder shape) {

        visited[x][y]=true;

        for (int i=0;i<dir.length;i++) {
            int nextX = dir[i][0]+x;
            int nextY = dir[i][1]+y;

            if(nextX>=0 && nextY>=0 && nextX<m && nextY<n && grid[nextX][nextY]==1 && !visited[nextX][nextY]) {
                shape.append(i);
                calculateIslands(grid,m,n,nextX,nextY,visited,shape);
            }

        }

        return shape.toString();

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };
        System.out.println(new NumberOfDistictIslands().numDistinctIslands(arr));
    }

}

/*

https://leetcode.com/articles/number-of-distinct-islands/

 */