/*
Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from the vertex 1.
In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected.
The frog can not jump back to a visited vertex. In case the frog can jump to several vertices it jumps randomly
to one of them with the same probability, otherwise, when the frog can not jump to any unvisited vertex it jumps forever on the same vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means that exists an
edge connecting directly the vertices fromi and toi.

Return the probability that after t seconds the frog is on the vertex target.

Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
Output: 0.16666666666666666
Explanation: The figure above shows the given graph. The frog starts at vertex 1,
jumping with 1/3 probability to the vertex 2 after second 1 and then jumping with 1/2 probability to vertex 4 after second 2.
Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666.


Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
Output: 0.3333333333333333
Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after second 1.


Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
Output: 0.16666666666666666


Constraints:

1 <= n <= 100
edges.length == n-1
edges[i].length == 2
1 <= edges[i][0], edges[i][1] <= n
1 <= t <= 50
1 <= target <= n
Answers within 10^-5 of the actual value will be accepted as correct.
 */
package bfsdfs.hard;

import java.util.HashSet;
import java.util.Set;

public class FrogPositionAfterTSeconds {

    public double frogPosition(int n, int[][] edges, int t, int target) {

        // If no edges present
        if(edges==null || edges.length==0) {
            return 1.0;
        }

        // If target is one and time>0 then the frog has to jump.
        if(target==1) {
            return t==0?1.0:0.0;
        }
        // Storing neighbours
        Set<Integer>[] arr = new Set[n+1];
        for (int i=0;i<=n;i++) {
            arr[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            arr[edge[0]].add(edge[1]);
            arr[edge[1]].add(edge[0]);
        }
        //visited array.
        boolean[] visited = new boolean[n+1];
        return util(arr,t,target,visited,1,1);

    }

    private double util(Set<Integer>[] arr,int time,int target,boolean[] visited,int start,int fraction) {

        visited[start]=true;
        // If time is less than zero than we can't reach.
        if(time<=0) {
            return 0.0;
        }

        int childCount = countUnvisitedNeighbours(visited,start,arr); // Unvisited children of the start value.
        if(arr[start].contains(target)) { // Check if the current parent contains child
            int targetNeighbours = countUnvisitedNeighbours(visited,target,arr);
            // If  Neighbours of target value are yet to be visited and time>1 then the frog will have to jump, hence can't be at target at time "t".
            if(targetNeighbours>=1 && time>1) {
                return 0.0;
            }
            int x = childCount*fraction;
            return (double)1/(double)x;
        }
        double val = 0.0;
        for (int child : arr[start]) {
            if(!visited[child]) {
                val = util(arr,time-1,target,visited,child,childCount*fraction); // Multiply fraction every time.
                if(val>0) {
                    return val;
                }
            }
        }
        return val;
    }

    // Counts unvisited children for any given node.
    private int countUnvisitedNeighbours(boolean[] visited,int start,Set<Integer>[] arr) {
        int count = arr[start].size();
        for (int child : arr[start]) {
            if(visited[child]) {
                count--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {2,1},
                {3,1},
                {4,2},
                {5,1},
                {6,2},
                {7,5},
                {8,7},
                {9,7}
        };
        System.out.println(new FrogPositionAfterTSeconds().frogPosition(9,arr,6,8));
    }

}
