/*
There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col
representing the number of rows and columns in the matrix, respectively.

Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a
1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith
column (1-based coordinates) will be covered with water (i.e., changed to 1).

You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells.
You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four
cardinal directions (left, right, up, and down).

Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
Output: 2
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 2.



Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
Output: 3
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 3.
 */
package binarysearch.hard;

import java.util.LinkedList;
import java.util.Queue;

public class LastDayToCross {

    public int latestDayToCross(int row, int col, int[][] cells) {
        int hi = cells.length;
        int lo = 1;
        int ans =0;
        while (lo<= hi) {
            int mid = lo + (hi - lo)/2;
            if(canCross(mid,row,col,cells)) {
                ans = mid;
                lo = mid+1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private final int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    private boolean canCross(int day, int row, int col, int[][] cells) {
        int[][] temp = new int[row][col];
        for (int[] cell : cells) {
            while (day-->0 ) {
                temp[cell[day]-1][cell[day]-1] = 1;
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        int n = temp[0].length;
        boolean[] visited = new boolean[temp.length*n];
        for (int i=0; i<temp[0].length; i++) {
            if(temp[0][i]!=1) {
                queue.add(new int[]{0,i});
                visited[i]=true;
            }
        }
        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            int x = pop[0];
            int y = pop[1];
            if(x==temp.length-1 && temp[x][y]==0) {
                return true;
            }
            for (int[] d: dir) {
                int nextX = x + d[0];
                int nextY = y + d[1];
                if(nextX>=0 && nextY>=0 && nextX<=temp.length-1 && nextY<=temp[0].length-1 && temp[nextX][nextY]==0 &&
                        !visited[nextX*n+nextY]) {
                    visited[nextX*n+nextY]=true;
                    queue.add(new int[]{nextX,nextY});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}
        };
        System.out.println(new LastDayToCross().latestDayToCross(3,3,a));
    }

}

/*

Union Find:

Using Disjoint Set to check if there is a path from top to bottom with all lands.
Since each land turns into water would break the connection between adjacent lands,
we can think the problem in a reverse manner, that is, from last to first. If we process the array in reverse,
then for each day a cell would turn into land from water, so the union operation of disjoint set can be applied.

There is a trick to check if there is a path from top to bottom. Set two virtual points called "top" and "bottom"
in disjoint set. Connect all lands at the first row with "top" and connecct all lands at the last row with "bottom".
Then it only needs to check if "top" and "bottom" are connected.



class Solution {
    class UnionFind {
        int[] parent;
        int[] size;
        int components;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI == rootJ)
                return;
            if (size[rootI] <= size[rootJ]) {
                parent[rootI] = rootJ;
                size[rootJ] += size[rootI];
            }
            else {
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            }
            components --;
        }

        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }
    }
    public int latestDayToCross(int m, int n, int[][] cells) {
        int[][] end = new int[m][n];
        for (int[] rows : end) {
            Arrays.fill(rows, 0);
        }

        for (int[] cell : cells) {
            end[cell[0]-1][cell[1]-1] = 1;
        }

        UnionFind uf = new UnionFind(m * n + 2);
        int top = m * n, bot = m * n + 1;
        for (int j = 0; j < n; ++j) {
            if (end[0][j] == 0)
                uf.union(j, top);
            if (end[m-1][j] == 0)
                uf.union((m-1) * n + j, bot);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (end[i][j] == 0) {
                    if (i > 0 && end[i-1][j] == 0) {
                        uf.union(i*n+j, (i-1)*n+j);
                    }
                    if (j > 0 && end[i][j-1] == 0) {
                        uf.union(i*n+j, i*n+j-1);
                    }
                }
            }
        }

        for (int i = cells.length - 1; i >= 0; --i) {
            if (uf.connected(top, bot))
                return i + 1;
            int r = cells[i][0] - 1, c = cells[i][1] - 1;
            end[r][c] = 0;
            if (r == 0)
                uf.union(top, r*n + c);
            if (r == m - 1)
                uf.union(bot, r*n + c);
            int[] dirs = new int[]{-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; ++k) {
                int nr = r + dirs[k], nc = c + dirs[k+1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && end[nr][nc] == 0) {
                    uf.union(r*n+c, nr*n+nc);
                }
            }
        }

        return 0;
    }
}

 */
