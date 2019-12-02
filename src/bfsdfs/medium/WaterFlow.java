/*

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four dir (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

 */

package bfsdfs.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 17/10/16.
 */
public class WaterFlow {

    private int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        if(matrix==null || matrix.length==0) {
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        Boolean[][] pacificFlow = new Boolean[m][n];
        Boolean[][] atlanticFLow = new Boolean[m][n];

        for(int i=0;i<m;i++) { // Mark top
            pacificFlow[i][0]=true;
        }
        for(int j=0;j<n;j++) { // Mark Left
            pacificFlow[0][j]=true;
        }
        for(int i=m-1;i>=0;i--) { // Mark Bottom
            atlanticFLow[i][n-1]=true;
        }
        for(int j=n-1;j>=0;j--) { // Mark Right
            atlanticFLow[m-1][j]=true;
        }
        boolean[][] visited = new boolean[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                pacificFlow[i][j] = isFlowPossible(i,j,pacificFlow,matrix,visited);
                atlanticFLow[i][j] = isFlowPossible(i,j,atlanticFLow,matrix,visited);
                if(pacificFlow[i][j] && atlanticFLow[i][j]) {
                    List<Integer> list = Arrays.asList(i,j);
                    result.add(list);
                }
            }
        }

        return result;
    }

    private boolean isFlowPossible(int i, int j, Boolean[][] flow, int[][] matrix, boolean[][] visited) {
        if(flow[i][j]!=null) {
            return flow[i][j];
        }
        visited[i][j]=true;
        for(int k=0;k<4;k++) {
            int nextX = dir[k][0]+i;
            int nextY = dir[k][1]+j;
            if(nextX>=0 && nextY>=0 && nextX<matrix.length && nextY<matrix[0].length && !visited[nextX][nextY] && matrix[i][j]>=matrix[nextX][nextY]) {
                if(isFlowPossible(nextX,nextY,flow,matrix,visited)) { // If get true once just return.
                    visited[i][j]=false;
                    flow[i][j]=true;
                    return true;
                }
            }
        }
        visited[i][j]=false;
        return false;
    }

    public static void main(String[] args) {

        int[][] arr = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        new WaterFlow().pacificAtlantic(arr);

    }

}

/*

Explanation:

The matrix is the continent with water on it and the boundaries are the oceans. Left and top being Pacific and right and bottom being the Atlantic.
The water on the continent (in the matrix) wants to flow out in the ocean. (Nature huh.)
The numbers in the matrix is the height of the water for that point.
For every point you have to ask the question. Can the water at this point and this height flow out in both the oceans under
the constraints of flowing through only four(up, down, right, left) directions and flow into channels with same height or less height?
If yes you return the coordinate of that point. Else you ignore it.


BFS:

public class Solution {
    int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        //One visited map for each ocean
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for(int i=0; i<n; i++){ //Vertical border
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, m-1});
            pacific[i][0] = true;
            atlantic[i][m-1] = true;
        }
        for(int i=0; i<m; i++){ //Horizontal border
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{n-1, i});
            pacific[0][i] = true;
            atlantic[n-1][i] = true;
        }
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i,j});
            }
        }
        return res;
    }
    public void bfs(int[][]matrix, Queue<int[]> queue, boolean[][]visited){
        int n = matrix.length, m = matrix[0].length;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] d:dir){
                int x = cur[0]+d[0];
                int y = cur[1]+d[1];
                if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]){
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}

 */
