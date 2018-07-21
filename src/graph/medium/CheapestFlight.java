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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank.b on 22/02/18.
 */
public class CheapestFlight {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        if(flights==null || flights.length==0) {
            return -1;
        }

        int[][] adjMatrix = new int[n][n];

        int[] cost = new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[src]=0;
        for (int[] flight : flights) {
            adjMatrix[flight[0]][flight[1]]=flight[2];
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            int size=queue.size();
            for (int i=0;i<size;i++) {
                int current = queue.poll();
                for (int[] flight : flights) {
                    int start = flight[0],end = flight[1];
                    if(start==current && cost[end]>cost[start]+adjMatrix[start][end]) {
                        cost[end]=cost[start]+adjMatrix[start][end];
                        queue.add(end);
                    }
                }
            }
            K--;
            //If k is one we can go till 2 stops.
            if(K<0) {
                break;
            }
        }

        return cost[dst]!=Integer.MAX_VALUE?cost[dst]:-1;

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
