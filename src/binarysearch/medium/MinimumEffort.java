/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell,
(0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells,
which is better than route [1,3,5,3,5].

 */
package binarysearch.medium;

public class MinimumEffort {

    private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int minimumEffortPath(int[][] heights) {
        int max = Integer.MIN_VALUE;
        for (int[] r : heights) {
            for (int e : r) {
                max = Math.max(max,e);
            }
        }
        if(max==1) {
            return 0;
        }
        int low =0;
        int high = max;
        while (low<high) {
            int mid = low+(high-low)/2;
            if(dfs(heights, 0, 0, mid, new boolean[heights.length][heights[0].length], heights[0][0])) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    private boolean dfs(int[][] heights, int x, int y, int diff, boolean[][] visited, int prev) {
        int m = heights.length;
        int n = heights[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y])  {
            return false;
        }
        if (Math.abs(heights[x][y] - prev) > diff)  {
            return false;
        }
        if (x == m - 1 && y == n - 1)  {
            return true;
        }

        visited[x][y] = true;
        for (int[] d : dirs) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (dfs(heights, newX, newY, diff, visited, heights[x][y])) {
                return true;
            }
        }
        return false;
    }

    /* Causes TLE
    private boolean bfs(int[][] heights, int diff) {
        int m = heights.length;
        int n = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        Set<String> visited = new HashSet<>();
        visited.add("0-0");
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                if(x==m-1 && y==n-1) {
                    return true;
                }
                if(x+1<m && Math.abs(heights[x+1][y]-heights[x][y])<=diff && visited.add((x+1)+"-"+(y))) {
                    queue.add(new int[]{x+1,y});
                }
                if(y+1<n && Math.abs(heights[x][y+1]-heights[x][y])<=diff && visited.add((x)+"-"+(y+1))) {
                    queue.add(new int[]{x,y+1});
                }
                if(x-1>=0 && Math.abs(heights[x-1][y]-heights[x][y])<=diff && visited.add((x-1)+"-"+(y))) {
                    queue.add(new int[]{x-1,y});
                }
                if(y-1>=0 && Math.abs(heights[x][y-1]-heights[x][y])<=diff && visited.add((x)+"-"+(y-1))) {
                    queue.add(new int[]{x,y-1});
                }
            }
        }
        return false;
    }*/

    public static void main(String[] args) {
        int[][] heights = new int[][] {
                {1,2,2},
                {3,8,2},
                {5,3,5}
        };
        System.out.println(new MinimumEffort().minimumEffortPath(heights));
    }

}

/*

If you are given threshold k, check if it is possible to go from (0, 0) to (n-1, m-1) using only edges of ≤ k cost.
Binary search the k value. (log(mn)*mn)

Dijikstra:

If we observe, this problem is to find the shortest path from a source cell (0, 0) to a destination cell (m-1, n-1).
Here, total path cost is defined as maximum absolute difference in heights between two consecutive cells of the path.
Thus, we could use Dijikstra's algorithm which is used to find the shortest path in a weighted graph with a slight
modification of criteria for the shortest path, which costs O(E log V),
where E is number of edges E = 4*M*N, V is number of veritices V = M*N

class Solution {
    int[] DIR = new int[]{0, 1, 0, -1, 0};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, 0, 0}); // distance, row, col
        dist[0][0] = 0;

        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int d = top[0], r = top[1], c = top[2];
            if (d > dist[r][c]) continue; // this is an outdated version -> skip it
            if (r == m - 1 && c == n - 1) return d; // Reach to bottom right
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newDist = Math.max(d, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (dist[nr][nc] > newDist) {
                        dist[nr][nc] = newDist;
                        minHeap.offer(new int[]{dist[nr][nc], nr, nc});
                    }
                }
            }
        }
        return 0; // Unreachable code, Java require to return integer value.
    }
}



 */