package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 04/11/18.
 */
public class ShortestBridge {

    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int k=2;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]==1){
                    Set<String> visited = new HashSet<>();
                    dfs(A,i,j,visited,k++);
                }
            }
        }
        int[][] distance = new int[m][n];

        for(int i=0;i<m;i++)
            Arrays.fill(distance[i],-1);

        Queue<String> q = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]==2){
                    q.add(i+","+j+","+0);
                    distance[i][j]=0;
                }
            }
        }
        return bfs(A,distance,q)-1;

    }


    public int bfs(int[][] A, int[][] output, Queue<String> queue){
        int min = Integer.MAX_VALUE;
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        while(!queue.isEmpty()){
            String[] curr = queue.poll().split(",");
            int u = Integer.parseInt(curr[0]);
            int v = Integer.parseInt(curr[1]);
            int distance = Integer.parseInt(curr[2]);
            for(int i=0;i<dx.length;i++){
                int x = u+dx[i];
                int y = v+dy[i];
                if(x<0||x>A.length-1|| y>A[0].length-1 || y<0 || output[x][y]!=-1) {
                    continue;
                }
                if(A[x][y]==3){
                    min = Math.min(min,distance+1);
                }
                else{
                    output[x][y]=distance+1;
                    queue.offer(x+","+y+","+output[x][y]);
                }
            }

        }
        return min;
    }

    public void dfs(int[][] A, int u, int v, Set<String> visited,int n){
        visited.add(u+""+v);
        A[u][v]=n;
        int[] dx= {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        for(int i=0;i<dx.length;i++){
            int x=u+dx[i];
            int y =v+dy[i];
            if(x<0||x>A.length-1|| y>A[0].length-1 || y<0 || visited.contains(x+","+y)) continue;
            if(A[x][y]==1){
                dfs(A,x,y,visited,n);
            }
        }
    }

}

/*

Using DFS mark the 2 connected components with different (connected component one with value 2 and connected component two with value 3).
Now the problem boils down to finding the shortest distance from any of the node (2) to Node(3).
* Add all the positions of one of the connected components(2) into queue and run BFS to find the shortest distance.

 */