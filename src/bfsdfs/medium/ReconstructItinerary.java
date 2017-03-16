/*

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].


All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

 */

package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 16/03/17.
 */
public class ReconstructItinerary {

    public List<String> findItinerary(String[][] tickets) {

        List<String> result = new ArrayList<>();

        if(null==tickets) {
            return result;
        }

        Map<String,PriorityQueue<String>> map = new HashMap<>();

        for (String[] ticket : tickets) {
            if(map.containsKey(ticket[0])) {
                PriorityQueue<String> list = map.get(ticket[0]);
                list.add(ticket[1]);
                map.put(ticket[0],list);
            } else {
                PriorityQueue<String> list = new PriorityQueue<>();
                list.add(ticket[1]);
                map.put(ticket[0],list);
            }
        }
        fillList(map,"JFK",result);

        return result;

    }

    private void fillList(Map<String,PriorityQueue<String>> map,String start,List<String> result) {
        PriorityQueue<String> priorityQueue = map.get(start);
        while (null!=priorityQueue && !priorityQueue.isEmpty()) {
            String next = priorityQueue.poll();
            fillList(map,next,result);
        }
        //Once the path from a node is visited then only it is added to result
        result.add(0,start);

    }

    public static void main(String[] args) {
        String[][] arr = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        System.out.println(new ReconstructItinerary().findItinerary(arr));
    }

}


/*

All the airports are vertices and tickets are directed edges. Then all these tickets form a directed graph.

The graph must be Eulerian since we know that a Eulerian path exists.

Thus, start from "JFK", we can apply the Hierholzer's algorithm to find a Eulerian path in the graph which is a valid reconstruction.

Another method:

    private void dfsRoute(String v) {
        // base case: vertex v is not in adjacency list
        // v is not a starting point in any itinerary, or we would have stored it
        // thus we have reached end point in our DFS
        if (!adjList.containsKey(v)) return;

        List<String> list = adjList.get(v);

        for (int i = 0; i < list.size(); ++i) {
            String neighbor = list.get(i);
            // remove ticket(route) from graph

            list.remove(i);
            route.add(neighbor);
            numTicketsUsed++;
            dfsRoute(neighbor);
            // we only return when we have used all tickets
            if (numTickets == numTicketsUsed) return;

            // otherwise we need to revert the changes and try other tickets
            list.add(i, neighbor);

            // This line took me a long time to debug
            // we must remove the last airport, since in an itinerary, the same airport can appear many times!!
            route.removeLast();
            numTicketsUsed--;
        }
    }


 */