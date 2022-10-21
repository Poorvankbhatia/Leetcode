/*

There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where
 connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.

Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.


Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.

 */
package graph.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnections {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] arr= new List[n];
        for (List<Integer> connection : connections) {
            int start = connection.get(0);
            int end = connection.get(1);
            if(arr[start] == null) {
                arr[start] = new ArrayList<>();
            }
            arr[start].add(end);
            if(arr[end] == null) {
                arr[end] = new ArrayList<>();
            }
            arr[end].add(start);
        }
        List<List<Integer>> result = new ArrayList<>();
        fillCriticalConnections(0,-1,0,arr,result,new int[n],new  boolean[n],new int[n]);
        return result;
    }

    private void fillCriticalConnections(int id, int prevNode, int node ,List<Integer>[] arr, List<List<Integer>> result,
                                         int[] ids, boolean[] visited, int[] lowLinks) {
        ids[node]=id;
        visited[node] =true;
        lowLinks[node] = id;
        id++;
        for (int next : arr[node]) {
            /*
            Consider a--critical edge-->b & lowValue[b]>lowValue[a] , if this is not done, since it is an undirected
            graph, after visiting b, a can
            come up again, setting the lowValue[b] to lowValue[a]. quite possible that might fail if id[a]==lowValue[a].
             */
            if(next == prevNode) {
                continue;
            }
            if(!visited[next]) {
                fillCriticalConnections(id,node, next,arr,result,ids,visited,lowLinks);
                lowLinks[node] = Math.min(lowLinks[node],lowLinks[next]);
                if(ids[node]<lowLinks[next]) {
                    result.add(Arrays.asList(node,next));
                }
            } else {
                lowLinks[node] = Math.min(lowLinks[node],ids[next]);
            }
        }

    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(0,1);
        List<Integer> l2 = Arrays.asList(1,2);
        List<Integer> l3 = Arrays.asList(2,0);
        List<Integer> l4 = Arrays.asList(1,3);
        System.out.println(new CriticalConnections().criticalConnections(4,Arrays.asList(l1,l2,l3,l4)));
    }



}

/*

https://emre.me/algorithms/tarjans-algorithm/
 */