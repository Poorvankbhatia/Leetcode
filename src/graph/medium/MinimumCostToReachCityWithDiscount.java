/*

A series of highways connect n cities numbered from 0 to n - 1. You are given a 2D integer array
highways where highways[i] = [city1i, city2i, tolli] indicates that there is a highway that connects
city1i and city2i, allowing a car to go from city1i to city2i and vice versa for a cost of tolli.

You are also given an integer discounts which represents the number of discounts you have.
You can use a discount to travel across the ith highway for a cost of tolli / 2 (integer division).
Each discount may only be used once, and you can only use at most one discount per highway.

Return the minimum total cost to go from city 0 to city n - 1, or -1 if it is not possible to go from city 0 to city n - 1.

Input: n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
Output: 9
Explanation:
Go from 0 to 1 for a cost of 4.
Go from 1 to 4 and use a discount for a cost of 11 / 2 = 5.
The minimum cost to go from 0 to 4 is 4 + 5 = 9.

Input: n = 4, highways = [[0,1,3],[2,3,2]], discounts = 0
Output: -1
Explanation:
It is impossible to go from 0 to 3 so return -1.


Constraints:

2 <= n <= 1000
1 <= highways.length <= 1000
highways[i].length == 3
0 <= city1i, city2i <= n - 1
city1i != city2i
0 <= tolli <= 105
0 <= discounts <= 500
There are no duplicate highways.

 */
package graph.medium;

import java.util.*;

public class MinimumCostToReachCityWithDiscount {

    public int minimumCost(int n, int[][] highways, int discounts) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] x : highways) {
            int a = x[0], b = x[1], c = x[2];
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[]{0, 0, 0}); // {cost, node, discount}
        int[][] dp = new int[n][discounts + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], city = cur[1], dis = cur[2];

            if (city == n - 1) return cost;

            for (int[] x : graph[city]) {
                int next = x[0], weight = x[1];
                if (cost + weight < dp[next][dis]) {
                    pq.offer(new int[]{cost + weight, next, dis});
                    dp[next][dis] = cost + weight;
                }
                if (dis < discounts && cost + weight / 2 < dp[next][dis + 1]) {
                    pq.offer(new int[]{cost + weight / 2, next, dis + 1});
                    dp[next][dis + 1] = cost + weight / 2;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] h = new int[][] {
                {0,1,4},{2,1,3},{1,4,11},{3,2,3},{3,4,2}
        };
        System.out.println(new MinimumCostToReachCityWithDiscount().minimumCost(5,h,1));
    }

}

/*

Also check CheapestFlight

Dijkstra is used to solve single source, positive weight and minimum path questions like this.
The only trick here is to use 2D visited array to check and do the pruning work when the next city is put into the
PriorityQueue, one for city and one for the amount of discounts used.
The principle of Dijkstra Algorithm determines the fact that the first time the destination is reached,
it will be the optimal solution.

 */