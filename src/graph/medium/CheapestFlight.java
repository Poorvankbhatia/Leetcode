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

/**
 * Created by poorvank.b on 22/02/18.
 */
public class CheapestFlight {

    int[][] matrix;
    int[][][] dp; // Storing minimum cost to reach from a given point to destination.
    int n;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        this.n = n;
        matrix = new int[n][n];
        dp = new int[100][100][100];
        for (int[] f : flights) {
            matrix[f[0]][f[1]] = f[2];
        }
        int ans = util(src, dst, K);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int util(int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }
        if (K < 0) {
            return Integer.MAX_VALUE;
        }
        if (dp[src][dst][K] != 0) {
            return dp[src][dst][K];
        }
        int max = Integer.MAX_VALUE;

        for(int next=0;next<n;next++) {
            if(matrix[src][next]>0) {
                int currentPrice = matrix[src][next];
                int val = util(next, dst, K - 1);
                if (val == Integer.MAX_VALUE) {
                    continue;
                }
                max = Math.min(max, currentPrice + val);
            }
        }

        dp[src][dst][K] = max;
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
 */