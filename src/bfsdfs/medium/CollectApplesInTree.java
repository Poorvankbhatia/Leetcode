/*
Given an undirected tree consisting of n vertices numbered from 0 to n-1,
which has some apples in their vertices. You spend 1 second to walk over one edge
of the tree. Return the minimum time in seconds you have to spend in order to collect all apples
in the tree starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi]
means that exists an edge connecting the vertices fromi and toi. Additionally, there is a boolean
array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise, it does not have any apple.

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
Example 2:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple.
One optimal path to collect all apples is shown by the green arrows.
Example 3:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0


Constraints:

1 <= n <= 10^5
edges.length == n-1
edges[i].length == 2
0 <= fromi, toi <= n-1
fromi < toi
hasApple.length == n
 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

public class CollectApplesInTree {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] lists = new List[n]; // Lists of vertices and neighbours.
        for (int[] edge : edges) {
            if(lists[edge[0]]==null) {
                lists[edge[0]]= new ArrayList<>();
            }
            lists[edge[0]].add(edge[1]);
        }
        return dfs(0,0,lists,hasApple);
    }

    private int dfs(int start,int time,List<Integer>[] lists,List<Boolean> hasApple) {
        int totalTime = time;
        if(lists[start]!=null) {
            for (int next : lists[start]) {
                totalTime=dfs(next,totalTime+1,lists,hasApple); // Every iteration pass totalTime+1 to visit children.
            }
        }
        if(hasApple.get(start) || totalTime>time) { // if current vertex has an apple or any child has an apple/
            return start==0?totalTime:totalTime+1; //return totalTime+1 to add one to return time after visiting apple vertex.
            // Don't add one for start==0 (starting vertex)
        }
        return start==0?totalTime:time-1;// Return time-1 since this should not be visited.
    }
}


/*

Another Sol:

public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
        }

        dfs(graph, 0, hasApple);
        return count;
    }

    int count = 0;
    private boolean dfs(List<List<Integer>> graph, int curNode, List<Boolean> hasA) {
        boolean res = hasA.get(curNode);
        for (int i = 0; i < graph.get(curNode).size(); i++) {
            if (dfs(graph, graph.get(curNode).get(i), hasA)) {
                count += 2;
                res = true;
            }
        }

        return res;
    }

 */