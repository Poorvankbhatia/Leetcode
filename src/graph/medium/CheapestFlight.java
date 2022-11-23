/*

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops.
If there is no such route, output -1.

Example 1:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200


Example 2:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500

 */
package graph.medium;

import java.util.*;

/**
 * Created by poorvank.b on 22/02/18.
 */
public class CheapestFlight {

    Map<Integer, List<int[]>> map ;
    int[][][] dp; // Storing minimum cost to reach from a given point to destination.
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        map = new HashMap<>();
        dp = new int[n][n][K+1];
        for(int[] f : flights) {
            if(!map.containsKey(f[0])) {
                map.put(f[0],new ArrayList<>());
            }
            map.get(f[0]).add(new int[]{f[1],f[2]});
        }
        int ans =  util(src,dst,K);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    private int util(int src,int dst,int K) {
        if(src==dst) {
            return 0;
        }
        if(K<0) {
            return Integer.MAX_VALUE;
        }
        if(dp[src][dst][K]!=0) {
            return dp[src][dst][K];
        }
        int max=Integer.MAX_VALUE;
        if(map.containsKey(src)) {
            for(int[] next : map.get(src)) {
                int currentPrice = next[1];
                int val = util(next[0],dst,K-1);
                if(val==Integer.MAX_VALUE) {
                    continue;
                }
                max = Math.min(max,currentPrice+val);
            }
        }
        dp[src][dst][K]=max;
        return max;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };
        System.out.println(new CheapestFlight().findCheapestPrice(3, arr, 0, 2, 1));
    }

}

/*

Also check MinimumCostToReachCityWithDiscount

Dijikstra:

// modified Dijkstra.
    public int findCheapestPrice(int n, int[][] flights,
                                 int src, int dst, int k) {
        // Build graph.
        // node i -> list of (node j, cost)
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] f : flights) {
            graph.get(f[0]).add(new int[]{f[1], f[2]});
        }

        // Queue node is int[] -> loc, cost, stops;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{src, 0, 0});

        // Min cost tracker.
        // min cost for k stop arriving at n node.
        int[][] minCost = new int[n][k+2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k+2; j++) {
                minCost[i][j] = Integer.MAX_VALUE;
            }
        }

        minCost[src][0] = 0;

        // Best first search.
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int curNode = cur[0];
            int curCost = cur[1];
            int curStops = cur[2];

            if (curNode == dst) return curCost;

            // No more stops possible, don't expand.
            if (curStops == k + 1) continue;

            // outdated values.
            if (curCost > minCost[cur[0]][curStops]) continue;

            // Expand cur best path.
            for (int[] e : graph.get(curNode)) {
                if (curCost + e[1] < minCost[e[0]][curStops+1]) {
                    // cur -> e[0];
                    minCost[e[0]][curStops + 1] = curCost + e[1];
                    pq.offer(new int[]{e[0],
                                       minCost[e[0]][curStops+1],
                                       curStops + 1});
                }
            }
        }

        return -1;
    }


dp[i][j] means within i flights, the min cost to city j.



    class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        double[][] dp = new double[K + 2][n];
        for (int i = 0; i < K + 2; i++) {
        	for (int j = 0; j < n; j++) {
        		dp[i][j] = 1e9;
        	}
        }
        dp[0][src] = 0;
        for (int i = 1; i <= K + 1; i++) {
        	dp[i][src] = 0;
        	for (int[] flight : flights) {
        		dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
        	}
        }
        return dp[K + 1][dst] >= 1e9 ? -1 : (int)dp[K + 1][dst];
    }
}

 */