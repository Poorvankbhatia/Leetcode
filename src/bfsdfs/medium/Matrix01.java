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
The cells are adjacent in only four directions: up, down, left and right.

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 19/03/17.
 */
public class Matrix01 {

    private class Cell {
        int x,y,value;

        public Cell(int x, int y,int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    private int[] xMove = {0,1,-1,0};
    private int[] yMove = {1,0,0,-1};

    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {

        List<List<Integer>> result = new ArrayList<>();

        int m = matrix.size();
        if(m==0) {
            return result;
        }
        int n  = matrix.get(0).size();

        Integer[][] resultArr = new Integer[m][n];


        Queue<Cell> queue = new LinkedList<>();
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(matrix.get(i).get(j)==0) {
                    resultArr[i][j] = 0;
                    Cell cell = new Cell(i,j,0);
                    queue.add(cell);
                } else {
                    resultArr[i][j] = Integer.MAX_VALUE;
                }

            }
        }

        while (!queue.isEmpty()) {

            Cell current = queue.poll();
            for (int i=0;i<4;i++) {
                int nextX = current.x+xMove[i];
                int nextY = current.y+yMove[i];
                if(nextX>=0 && nextX<m && nextY>=0 && nextY<n) {
                   Cell newCell;
                   //Only add into queue if value is smaller than currently calculated distance
                   if(resultArr[nextX][nextY]>current.value+1) {
                       resultArr[nextX][nextY] = current.value+1;
                       newCell = new Cell(nextX,nextY,current.value+1);
                       queue.add(newCell);
                   }
                }
            }

        }

        for (Integer[] array : resultArr) {
            result.add(Arrays.asList(array));
        }

        return result;
    }

}

/*

Simple BFS

 */