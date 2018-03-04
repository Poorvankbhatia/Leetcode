/*

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), check if these edges form a valid tree.

 */
package graph.medium;

import java.util.*;

/**
 * Created by poorvank.b on 09/02/18.
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {

        Map<Integer,List<Integer>> map = new HashMap<>();

        for (int i=0;i<n;i++) {
            map.put(i,new ArrayList<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if(visited.contains(current)) {
                return false;
            }
            visited.add(current);
            for (Integer next : map.get(current)) {
                if(!visited.contains(next)) {
                    queue.add(next);
                }
            }
        }


        for (int i=0;i<n;i++) {
            if(!visited.contains(i)) {
                return false;
            }
        }

        return true;

    }

}
