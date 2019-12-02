/*

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node,
v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 16/12/17.
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {
        List<int[]>[] arr = new List[N+1];
        for(int[] time : times) {
            if(arr[time[0]]==null) {
                arr[time[0]] = new ArrayList<>();
            }
            arr[time[0]].add(new int[]{time[1],time[2]});
        }
        int[] dp = new int[N+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[K]=0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));//Priority Queue: Dijikstra
        pq.offer(new int[]{K, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int distance = cur[1];
            if(arr[node]!=null) {
                for (int[] x: arr[node]) {
                    int absoluteDistance = distance + x[1];
                    int targetNode = x[0];
                    if(dp[targetNode]>absoluteDistance){
                        dp[targetNode]=absoluteDistance;
                        pq.offer(new int[]{targetNode, absoluteDistance});
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i=1;i<=N;i++) {
            max = Math.max(max,dp[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;

    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3,4},{1,2,1},{2,3,2}};
        System.out.println(new NetworkDelayTime().networkDelayTime(arr,3,1));
    }

}

/*

time: O(Nlog(N) + E)
space: O(N + E)

bfs and djikstra are very similar problems.
It's just that djikstra cost is different compared with bfs, so use priorityQueue instead a Queue for a standard bfs search.
There could be one node with different distances in the PriorityQ, but PriorityQ will always give you the shortest distance one.

DP Solution(Bellman Ford):

public int networkDelayTime(int[][] times, int N, int K) {
        List<int[]>[] arr = new List[N+1];
        for(int[] time : times) {
            if(arr[time[0]]==null) {
                arr[time[0]] = new ArrayList<>();
            }
            arr[time[0]].add(new int[]{time[1],time[2]});
        }
        int[] dp = new int[N+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[K]=0;
        boolean[] visited= new boolean[N+1];
        dfs(arr,K,dp,0,visited);
        int max = Integer.MIN_VALUE;
        for (int i=1;i<=N;i++) {
            max = Math.max(max,dp[i]);
        }
        return max==Integer.MAX_VALUE?-1:max;
    }

    private void dfs(List<int[]>[] arr,int K,int[] dp,int current,boolean[] visited) {
        visited[K]=true;
        if(arr[K]!=null) {
            for (int[] x: arr[K]) {
               if(!visited[x[0]]) {
                   if(dp[x[0]]>x[1]+current) {
                       dp[x[0]]=x[1]+current;
                       dfs(arr,x[0],dp,dp[x[0]],visited);
                   }
               }
            }
        }
        visited[K]=false;
    }



 */