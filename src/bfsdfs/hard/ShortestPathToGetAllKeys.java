package bfsdfs.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by poorvank.b on 12/07/18.
 */
public class ShortestPathToGetAllKeys {

    int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int shortestPathAllKeys(String[] grid) {

        if(grid==null || grid.length==0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length();

        Queue<int[]> queue = new LinkedList<>();

        int keyCount=0;

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i].charAt(j)=='@') {
                    queue.add(new int[]{i,j});
                }
                if(grid[i].charAt(j)>='a' && grid[i].charAt(j)<='z') {
                    keyCount++;
                }
            }
        }


        Set<Character> keySet = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length()];

        int jumps=0;
        visited[queue.peek()[0]][queue.peek()[1]]=true;
        while (!queue.isEmpty()) {
            int size=queue.size();
            while (size>0) {
                if(keyCount==0) {
                    return jumps;
                }
                int[] pop = queue.poll();
                int i = pop[0];
                int j = pop[1];
                for (int[] d : dir) {
                    int nextI = d[0]+i;
                    int nextJ = d[1]+j;

                    if(isSafe(nextI,nextJ,m,n,visited,grid)) {
                        if(grid[nextI].charAt(nextJ)>='a' && grid[nextI].charAt(nextJ)<='z')  {
                            keySet.add(grid[nextI].charAt(nextJ));
                            keyCount--;
                            if(keyCount==0) {
                                return jumps+1;
                            }
                            queue.add(new int[]{nextI,nextJ});
                            visited[nextI][nextJ]=true;
                        } else if(grid[nextI].charAt(nextJ)>='A' && grid[nextI].charAt(nextJ)<='Z') {
                            if(keySet.contains(Character.toLowerCase(grid[nextI].charAt(nextJ)))) {
                                queue.add(new int[]{nextI,nextJ});
                                visited[nextI][nextJ]=true;
                            }
                        } else  {
                            queue.add(new int[]{nextI,nextJ});
                            visited[nextI][nextJ]=true;
                        }
                    }
                }
                size--;
            }
            jumps++;
        }

        return -1;

    }

    private boolean isSafe(int i,int j,int m,int n,boolean[][] visited,String[] grid) {
        return i>=0 && j>=0 && i<m && j<n && !visited[i][j] && grid[i].charAt(j)!='#';
    }

    public static void main(String[] args) {
        String[] s = new String[]{"@...a",".###A","b.BCc"};
        System.out.println(new ShortestPathToGetAllKeys().shortestPathAllKeys(s));
    }

}
