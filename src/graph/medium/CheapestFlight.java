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
        dp = new int[100][100][100];
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
                {0,1,100},
                {1,2,100},
                {0,2,500}
        };
        System.out.println(new CheapestFlight().findCheapestPrice(3,arr,0,2,1));
    }

}

/*

Priority Queue: Dijikstra:

 public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[] {0, src, k});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops >= 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }

 */