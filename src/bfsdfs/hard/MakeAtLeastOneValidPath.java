/*
Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell.
The sign of grid[i][j] can be:
1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some invalid signs on the cells of the grid which points outside the grid.

You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0)
and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.

Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3)
change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0)
change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3)
change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.


Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).


Input: grid = [[1,2],[4,3]]
Output: 1

Input: grid = [[2,2,2],[2,2,2]]
Output: 3



 */
package bfsdfs.hard;

import java.util.*;

@SuppressWarnings("unchecked")
public class MakeAtLeastOneValidPath {

    public int minCost(int[][] grid) {
        if(grid.length<=1) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        LinkedList<int[]> queue = new LinkedList<>();
        int[] arr = new int[]{0,0,grid[0][0],0};
        // Set of visited array. Checks if a particular direction for a column has been visited.
        Set[] visited = new Set[m*n];
        for (int i=0;i<m*n;i++) {
            visited[i]=new HashSet();
        }
        queue.add(arr);

        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            int count = pop[3];
            int coordinate = pop[0]*n+pop[1];
            if(pop[0]==m-1 && pop[1]==n-1) {
                return count;
            }
            int[] canMove = canMove(pop[0],pop[1],pop[2],m,n);
            if(canMove.length!=0 && !visited[canMove[0]*n+canMove[1]].contains(grid[canMove[0]][canMove[1]])) {
                queue.addFirst(new int[]{canMove[0],canMove[1],grid[canMove[0]][canMove[1]],count});
                visited[coordinate].add(pop[2]);
            }
            int[] right = new int[]{pop[0], pop[1], 1, count + 1};
            int[] left = new int[]{pop[0], pop[1], 2, count + 1};
            int[] down = new int[]{pop[0], pop[1], 3, count + 1};
            int[] up = new int[]{pop[0], pop[1], 4, count + 1};
            switch (pop[2]) {
                case 1:
                    if (visited[coordinate].add(2)) {
                        queue.add(left);
                    }
                    if (visited[coordinate].add(3)) {
                        queue.add(down);
                    }
                    if (visited[coordinate].add(4)) {
                        queue.add(up);
                    }
                    break;
                case 2:
                    if (visited[coordinate].add(1)) {
                        queue.add(right);
                    }
                    if (visited[coordinate].add(3)) {
                        queue.add(down);
                    }
                    if (visited[coordinate].add(4)) {
                        queue.add(up);
                    }
                    break;
                case 3:
                    if (visited[coordinate].add(1)) {
                        queue.add(right);
                    }
                    if (visited[coordinate].add(2)) {
                        queue.add(left);
                    }
                    if (visited[coordinate].add(4)) {
                        queue.add(up);
                    }
                    break;
                case 4:
                    if (visited[coordinate].add(1)) {
                        queue.add(right);
                    }
                    if (visited[coordinate].add(2)) {
                        queue.add(left);
                    }
                    if (visited[coordinate].add(3)) {
                        queue.add(down);
                    }
                    break;
                default:break;
            }

        }
        return -1;
    }

    private int[] canMove(int x,int y,int dir,int m,int n) {
        switch (dir) {
            case 1: return x<m && y+1<n?new int[]{x,y+1}:new int[0];
            case 2: return x>=0 && y-1>=0?new int[]{x,y-1}:new int[0];
            case 3: return x+1<m && y<n?new int[]{x+1,y}:new int[0];
            case 4: return x-1>=0 && y>=0?new int[]{x-1,y}:new int[0];
            default:return new int[0];
        }
    }

    public static void main(String[] args){
        int[][] grid = new int[][]{
                {1,2},
                {4,3}
        };
        System.out.println(new MakeAtLeastOneValidPath().minCost(grid));
    }

}

/*

Better methods:

BFS/DFS:

class Solution {
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length, cost = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        Queue<int[]> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                int[] top = bfs.poll();
                int r = top[0], c = top[1];
                for (int i = 0; i < 4; i++) dfs(grid, r + DIR[i][0], c + DIR[i][1], dp, cost, bfs);
            }
        }
        return dp[m - 1][n - 1];
    }

    void dfs(int[][] grid, int r, int c, int[][] dp, int cost, Queue<int[]> bfs) {
        int m = grid.length, n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != Integer.MAX_VALUE) return;
        dp[r][c] = cost;
        bfs.offer(new int[]{r, c}); // add to try to change direction later
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + DIR[nextDir][0], c + DIR[nextDir][1], dp, cost, bfs);
    }
}

Dijikstra's

 int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // minHeap by cost
        q.offer(new int[]{0, 0, 0});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int cost = top[0], r = top[1], c = top[2];
            if (dist[r][c] != cost) continue; // avoid outdated (dist[r,c], r, c) to traverse neighbors again!
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i][0], nc = c + DIR[i][1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int ncost = cost;
                    if (i != (grid[r][c] - 1)) ncost += 1; // change direction -> ncost = cost + 1
                    if (dist[nr][nc] > ncost) {
                        dist[nr][nc] = ncost;
                        q.offer(new int[]{ncost, nr, nc});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

For the problem, we can create a graph with 4mn edges and mn nodes. By using the Dijkstra algorithm, we can guarantee to achieve it in O(ElogV) ~ O(mn * log(mn))

Time: O(ElogV) ~ O(mn * log(mn)), E = 4mn, V = mn
Space: O(m*n)

Also check:

Swim in rising water
Maximum Minimum Value.

 */