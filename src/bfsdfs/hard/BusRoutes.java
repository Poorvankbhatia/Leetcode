/*

We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only,
what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input:
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation:
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.

 */
package bfsdfs.hard;

import java.util.*;

/**
 * Created by poorvank.b on 08/04/18.
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int S, int T) {

        if(routes==null || routes.length==0 || S==T) {
            return 0;
        }

        Map<Integer,List<Integer>> stopBusMapping = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (stopBusMapping.containsKey(routes[i][j])) {
                    stopBusMapping.get(routes[i][j]).add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    stopBusMapping.put(routes[i][j], list);
                }
            }
        }

        Set<Integer> visitedBus = new HashSet<>();
        Set<Integer> visitedStop = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);

        int busCount=0;

        while (!queue.isEmpty()) {

            busCount++;
            int size = queue.size();

            for (int i=0;i<size;i++) {
                int stop = queue.poll();
                visitedStop.add(stop);

                List<Integer> buses = stopBusMapping.get(stop);

                for (Integer bus : buses) {
                    if(!visitedBus.contains(bus)) {
                        visitedBus.add(bus);
                        for (int k = 0; k < routes[bus].length; k++) {
                            if (routes[bus][k] == T) {
                                return busCount;
                            }
                            if(!visitedStop.contains(routes[bus][k])) {
                                queue.add(routes[bus][k]);
                            }
                        }

                    }
                }

            }

        }

        return -1;

    }

    public static void main(String[] args) {
        int[][] routes =  new int[][] {
                {24},
                {3,6,11,14,22},
                {1,23,24},
                {0,6,14},
                {1,3,8,11,20}
        };
        System.out.println(new BusRoutes().numBusesToDestination(routes,20,8));
    }

}

/*

The idea is BFS the bus, not the stop. If we get the a stop which a bus pass by, then we can get to every stop of this route.

 */