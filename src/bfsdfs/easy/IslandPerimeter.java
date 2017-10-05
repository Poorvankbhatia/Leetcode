/*

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes"
(water inside that isn't connected to the water around the island). One cell is a square with side length 1.
The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16

 */
package bfsdfs.easy;

/**
 * Created by poorvank on 20/11/16.
 */
public class IslandPerimeter {

    private int[] XMOVE = new int[]{0,1,-1,0};
    private int[] YMOVE = new int[]{1,0,0,-1};

    public int islandPerimeter(int[][] grid) {

        int rows = grid.length;
        if(rows==0) {
            return 0;
        }
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(grid[i][j]==1 && !visited[i][j]) {
                    return perimeterUtil(i,j,rows,cols,grid,visited);
                }
            }
        }

        return 0;

    }


    private int perimeterUtil(int i,int j,int rows,int cols,int[][] grid,boolean[][] visited) {

        visited[i][j] = true;

        int perimeter=0;
        for (int k=0;k<4;k++) {


            int nextX = i+XMOVE[k];
            int nextY = j+YMOVE[k];

            if(isValid(nextX,nextY,rows,cols,grid)) {
                /*
                 Check all cases against this portion:
                 0,1,0,0
                 1,1,1,0
                 0,1,0,0

                 */
                if(!visited[nextX][nextY]) {
                    perimeter += perimeterUtil(nextX,nextY,rows,cols,grid,visited);
                }
            } else {
                perimeter++;
            }


        }

        return perimeter;

    }


    private boolean isValid(int x,int y,int rows,int col,int[][] grid) {
        return (x>=0 && x<rows && y>=0 && y<col && grid[x][y]==1);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };

        System.out.print(new IslandPerimeter().islandPerimeter(grid));


    }

}
