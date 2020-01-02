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
        List<Integer>[] stopBusList = new List[100001];
        for(int i=0;i<routes.length;i++) {
            for(int j=0;j<routes[i].length;j++) {
                int stop = routes[i][j];
                if(stopBusList[stop]==null) {
                    stopBusList[stop] = new ArrayList<>();
                }
                stopBusList[stop].add(i);
            }
        }
        boolean[] visitedStop = new boolean[100001];
        boolean[] visitedBus = new boolean[501];
        Queue<Integer> queue = new LinkedList<>();
        int busCount=0;
        if(stopBusList[S]!=null && stopBusList[S].size()>0) {
            busCount++;
            queue.add(S);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int stop=queue.poll();
                List<Integer> busList = new ArrayList<>();
                if(stopBusList[stop]!=null) {
                    busList = stopBusList[stop];
                }
                for(int bus : busList) {
                    if(!visitedBus[bus]) {
                        visitedBus[bus]=true;
                        for(int k=0;k<routes[bus].length;k++) {
                            if(routes[bus][k]==T) {
                                return busCount;
                            }
                            if(!visitedStop[routes[bus][k]]) {
                                visitedStop[routes[bus][k]]=true;
                                queue.add(routes[bus][k]);
                            }
                        }
                    }
                }
            }
            busCount++;
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

For each of the bus stop, we maintain all the buses (bus routes) that go through it. To do that, we use a HashMap,
where bus stop number is the key and all the buses (bus routes) that go through it are added to an ArrayList.

We use BFS, where we process elements in a level-wise manner. We add the Start bus stop in the queue. Next, when we enter the while loop,
we add all the bus stops that are reachable by all the bus routes that go via the Start.
Thus, if we have the input as [[1, 2, 7], [3, 6, 7]] and Start as 6, then upon processing bus stop 6, we would add bus stops 3 and 7.
With this approach, all the bus stops at a given level, are "equal distance" from the start node, in terms of number of buses that need to be changed.

To avoid loops, we also maintain a HashSet that stores the buses that we have already visited.



 */