/*
You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from
node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.

Return the length of the longest cycle in the graph. If no cycle exists, return -1.

A cycle is a path that starts and ends at the same node.

Input: edges = [3,3,4,2,3]
Output: 3
Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
The length of this cycle is 3, so 3 is returned.


Input: edges = [2,-1,3,1]
Output: -1
Explanation: There are no cycles in this graph.


Constraints:

n == edges.length
2 <= n <= 105
-1 <= edges[i] < n
edges[i] != i

 */
package graph.hard;
import java.util.Arrays;

public class LongestCycleInAGraph {

    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] indegree = new int[n];
        for (int edge : edges) {
            if(edge!=-1) {
                indegree[edge]++;
            }
        }
        int ans = 0;
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist,-1);
        for (int i=0;i<n;i++) {
            // if indegree ==0 cannot form a cycle.
            if(indegree[i]>0 && !visited[i]) {
                ans = Math.max(ans, dfs(i,edges,visited,dist,0));
            }
        }

        return ans==0?-1:ans;
    }


    private int dfs(int start,int[] edges, boolean[] visited,int[] distanceFromStart,int current) {

        // if not visited.
        if(!visited[start]) {
            visited[start] = true;
            // distance from start = current.
            distanceFromStart[start] = current;
            int ans = 0;
            if(edges[start]!=-1) {
                // loop to next.
                ans = dfs(edges[start],edges,visited,distanceFromStart,current+1);
            }
            distanceFromStart[start]=-1;
            return ans;
        } else {
            // if visited and dist[start]!=-1 means loop found.
            if(distanceFromStart[start]!=-1) {
                return current-distanceFromStart[start];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCycleInAGraph().longestCycle(new int[]{-1,0,1,4,2}));
    }

}

/*

Time Complexity: O(n)
Space Complexity: O(n)

 */