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
            Map<Integer,List<Integer>> blueMap = new HashMap<>();
            Queue<int[]> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            visited.add("0_0");
            visited.add("0_1");
            for(int[] e: red_edges) {
                redMap.putIfAbsent(e[0],new ArrayList<>());
                redMap.get(e[0]).add(e[1]);
                if(e[0]==0) {
                    queue.add(new int[]{e[1],1});
                }
            }
            for(int[] e: blue_edges) {
                blueMap.putIfAbsent(e[0],new ArrayList<>());
                blueMap.get(e[0]).add(e[1]);
                if(e[0]==0) {
                    queue.add(new int[]{e[1],0});
                }
            }

            int[] result = new int[n];
            Arrays.fill(result,-1);
            result[0]=0;
            int val=1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i=0;i<size;i++) {
                    int[] pop = queue.poll();
                    if(result[pop[0]]==-1) {
                        result[pop[0]]=val;
                    }
                    if(pop[1]==1) {
                        if(blueMap.containsKey(pop[0])) {
                            for(int next : blueMap.get(pop[0])) {
                                String check = next+"_"+0;
                                if(visited.add(check)) {
                                    queue.add(new int[]{next,0});
                                }
                            }
                        }
                    } else {
                        if(redMap.containsKey(pop[0])) {
                            for(int next : redMap.get(pop[0])) {
                                String check = next+"_"+1;
                                if(visited.add(check)) {
                                    queue.add(new int[]{next,1});
                                }
                            }
                        }
                    }
                }
                val++;
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
