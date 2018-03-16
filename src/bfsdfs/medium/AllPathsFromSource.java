/*

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 11/03/18.
 */
public class AllPathsFromSource {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList<>();
        if(graph==null || graph.length==0) {
            return result;
        }

        Map<Integer,List<Integer>> map = new HashMap<>();

        for (int i=0;i<graph.length;i++) {
            if(!map.containsKey(i)) {
                map.put(i,new ArrayList<>());
            }
            for (int g : graph[i]) {
                map.get(i).add(g);
            }
        }

        fillPaths(0,graph.length-1,map,result,new ArrayList<>());

        return result;

    }

    private void fillPaths(int source,int destination,Map<Integer,List<Integer>> map,List<List<Integer>> result,List<Integer> list) {
        list.add(source);
        if(source==destination) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int neighbour : map.get(source)) {
            fillPaths(neighbour,destination,map,result,list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] {
                {1,2},{3},{3,4},{4},{}
        };
        System.out.println(new AllPathsFromSource().allPathsSourceTarget(graph));
    }

}


