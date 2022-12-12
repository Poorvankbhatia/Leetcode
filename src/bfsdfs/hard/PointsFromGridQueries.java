/*

You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queres[i] you start in the top left cell of the matrix and
repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is
your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to
visit the same cell multiple times.

Return the resulting array answer.

Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.

Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.


Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
k == queries.length
1 <= k <= 104
1 <= grid[i][j], queries[i] <= 106

 */
package bfsdfs.hard;

import java.util.*;

public class PointsFromGridQueries {

    public int[] maxPoints(int[][] grid, int[] queries) {

        int m = grid.length;
        int n = grid[0].length;
        int[] newQueries = queries.clone();
        // sort the input array.
        Arrays.sort(newQueries);
        Queue<int[]> next = new LinkedList<>();
        Queue<int[]> current = new LinkedList<>();
        current.add(new int[]{0,0});
        int[] result = new int[queries.length];
        boolean[][] seen = new boolean[m][n];
        seen[0][0]=true;
        int[] map = new int[1000000];
        for (int i=0;i<newQueries.length;i++) {
            // do a bfs for every query and get points, along with the next indixes to start.
            int ans = bfs(current,newQueries[i],next,grid,seen);
            // append the previous result to this one.
            result[i]=i==0?ans:ans+result[i-1];
            map[newQueries[i]] = result[i];
            current = next;
            next = new LinkedList<>();
        }
        for (int i=0;i<queries.length;i++) {
            result[i] = map[queries[i]];
        }
        return result;
    }

    private int[][] DIR = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    private int bfs(Queue<int[]> current,int query,Queue<int[]> next,int[][] grid,boolean[][] seen) {
        int ans=0;
        while (!current.isEmpty()) {
            int[] pop = current.poll();
            int x = pop[0];
            int y = pop[1];
            if(query>grid[x][y]) {
                ans++;
                for (int[] d : DIR) {
                    int newX = d[0]+x;
                    int newY = d[1]+y;
                    if(newX>=0 && newY>=0 && newX<grid.length && newY<grid[0].length && !seen[newX][newY]) {
                        // add to the current queue if<query
                        if(grid[newX][newY]<query) {
                            current.add(new int[]{newX,newY});
                        } else {
                            // add to the next queue.
                            next.add(new int[]{newX,newY});
                        }
                        seen[newX][newY]=true;
                    }
                }
            } else {
                next.add(new int[]{x,y});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {67650,426517,146447,190319,379151,184754,479219,106819,138473,865661,799297,228827},
                {390392,789371,772048,730506,7144,862164,650590,21524,879440,396198,408897,851020},
                {932044,662093,436861,246956,128943,167432,267483,148325,458128,418348,900594,831373}};
        System.out.println(Arrays.toString(new PointsFromGridQueries().maxPoints(a, new int[]{690474})));
    }
}

/*
O(m*n*klogk)

for "small query" to "large query":
    start new BFS using points from the previous BFS as a starting points
		count new points that are strictly less than current `query`
		collect new points in `nextBfs` that are not strictly less than current `query`,
		we will use those to start the next BFS

 */