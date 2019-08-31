/*
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue,
and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a
blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the
edge colors alternate along the path (or -1 if such a path doesn't exist).



Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]


Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n

 */
package bfsdfs.medium;

import java.util.*;

public class ShortestPathAlternatingColors {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<Integer>> redMap = new HashMap<>();
        Map<Integer, List<Integer>> blueMap = new HashMap<>();

        for (int[] edge : red_edges) {
            if(!redMap.containsKey(edge[1])) {
                redMap.put(edge[1], new ArrayList<>());
            }
            redMap.get(edge[1]).add(edge[0]);
        }

        for (int[] edge : blue_edges) {
            if(!blueMap.containsKey(edge[1])) {
                blueMap.put(edge[1], new ArrayList<>());
            }
            blueMap.get(edge[1]).add(edge[0]);
        }


        /**
         * Queue contains an array of 3 elements:
         * First element signifies the index of the vertex from : 0 to n-1
         * Second signifies the edge color : 0-> blue & 1-> red.
         * Third signifies the length to reach.
         */
        Queue<int[]> queue = new LinkedList<>();
        int[] result = new int[n];
        for (int i=n-1;i>0;i--) {
            queue.add(new int[]{i,0,0});
            queue.add(new int[]{i,1,0});
            Set<String> set = new HashSet<>();
            boolean flag = true;
            while (!queue.isEmpty()) {
                int[] pop = queue.poll();
                if(pop[0] == 0) {
                    result[i] = pop[2];
                    flag = false;
                    break;
                }
                set.add(pop[0]+"_"+pop[1]);
                if(pop[1] ==0) {
                    if(blueMap.containsKey(pop[0])) {
                        for (int v : blueMap.get(pop[0])) {
                            if(!set.contains(v+"_"+1)) {
                                queue.add(new int[]{v,1,pop[2]+1});
                            }
                        }
                    }
                } else {
                    if(redMap.containsKey(pop[0])) {
                        for (int v : redMap.get(pop[0])) {
                            if(!set.contains(v+"_"+0)) {
                                queue.add(new int[]{v,0,pop[2]+1});
                            }
                        }
                    }
                }
            }
            if(!flag) {
                queue.clear();
            } else {
                result[i] = -1;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] blue = new int[][] {
                {0,1},
                {1,2},
                {2,3},
                {3,4}
        };

        int[][] red = new int[][] {
                {1,2},
                {2,3},
                {3,1}
        };
        System.out.println(Arrays.toString(new ShortestPathAlternatingColors().shortestAlternatingPaths(5,red,blue)));
    }

}
