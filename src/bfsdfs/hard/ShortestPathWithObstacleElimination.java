package bfsdfs.hard;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithObstacleElimination {

    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1, 0}};
    public int shortestPath(int[][] grid, int k) {
        if(grid==null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0});
        int ans = 0;
        boolean[][][] visited = new boolean[m][n][k+1];
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                if(x==m-1 && y==n-1) return ans;
                for (int[] d : dir) {
                    int nextX = x+d[0];
                    int nextY = y+d[1];
                    int z = current[2];
                    if(nextX<0 || nextY<0 || nextX>=m || nextY>=n) {
                        continue;
                    }
                    if(grid[nextX][nextY]==1) {
                        z++;
                    }
                    if(z<=k && !visited[nextX][nextY][z]) {
                        visited[nextX][nextY][z]=true;
                        queue.offer(new int[]{nextX,nextY,z});
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,0,0},
                {1,1,0},
                {0,1,1},
                {0,0,0}
        };
        System.out.println(new ShortestPathWithObstacleElimination().shortestPath(a,1));
    }

}
