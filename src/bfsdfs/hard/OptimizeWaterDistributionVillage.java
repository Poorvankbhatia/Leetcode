/*

There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it.
The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents
the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.



Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation:
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

Constraints:

1 <= n <= 10000
wells.length == n
0 <= wells[i] <= 10^5
1 <= pipes.length <= 10000
1 <= pipes[i][0], pipes[i][1] <= n
0 <= pipes[i][2] <= 10^5
pipes[i][0] != pipes[i][1]

 */
package bfsdfs.hard;

import java.util.*;

public class OptimizeWaterDistributionVillage {

    private class UF {

        private int[] parent;
        private int[] size;

        public UF(int n) {
            this.parent = new int[n+1];
            this.size = new int[n+1];
            for (int i=0;i<=n;i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        boolean isConnected(int i, int j) {
            return findParent(i)==findParent(j);
        }

        int findParent(int i) {
            while (i!=parent[i]) {
                parent[i] = parent[parent[i]];
                i=parent[i];
            }
            return i;
        }

        void union(int x,int y) {
            int xRoot = findParent(x);
            int yRoot = findParent(y);

            if(xRoot==yRoot) {
                return;
            }

            if(size[xRoot]>size[yRoot]) {
                parent[yRoot] = xRoot;
                size[xRoot]+=size[yRoot];
            } else {
                parent[xRoot]=yRoot;
                size[yRoot]+=size[xRoot];
            }

        }

    }

    private Map<Integer, List<Map<Integer,Integer>>> map; // List because we can have multiple values.

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        map = new TreeMap<>();
        // Create a dummy house with value as 0 and each edge value = wells[i] Now find MST
        // The trick here is to view "digging new wells" as "laying new pipes to an original water-souce node 0".
        // Then it becomes a straight-forward MST problem.
        // The idea is to imagine that there is a well with a house number of 0.
        // Each well[i] is a connection between house 0 and house number "i" with a cost of well[i]. Then the rest becomes a regular spanning tree question.
        for (int i=1;i<=n;i++) {
            map.putIfAbsent(wells[i-1],new ArrayList<>());
            HashMap<Integer,Integer> m = new HashMap<>();
            m.put(0,i);
            map.get(wells[i-1]).add(m);
        }
        for (int[] pipe : pipes) {
            int start = pipe[0];
            int end = pipe[1];
            int weight = pipe[2];
            map.putIfAbsent(weight,new ArrayList<>());
            HashMap<Integer,Integer> m = new HashMap<>();
            m.put(start, end);
            map.get(weight).add(m);
        }

        UF uf = new UF(n);
        int ans = 0;
        for (Map.Entry<Integer,List<Map<Integer,Integer>>> entry : map.entrySet()) {
            int key = entry.getKey();
            for (Map<Integer,Integer> m : entry.getValue()) {
                for (Map.Entry<Integer,Integer> e : m.entrySet()) {
                    int start = e.getKey();
                    int end = e.getValue();
                    if(!uf.isConnected(start,end)) {
                        uf.union(start,end);
                        ans += key;
                    }
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        int n = 3;
        int[] wells = new int[]{6,3,4};
        int[][] pipes = new int[][] {
                {1,2,3},
                {3,1,3}
        };
        System.out.println(new OptimizeWaterDistributionVillage().minCostToSupplyWater(n,wells,pipes));
    }

}

/*

Intuition
We cannot build any well.
There is one and only one hidding well in dummy house.
The cost to lay pipe between house[i] and my house is wells[i].

In order to supply water to the whole village,
we need to make the village a connected graph.


Explanation
Merge all costs of pipes together and sort by key.
Greedily lay the pipes if it can connect two seperate union.
Apply union find to record which houses are connected.


Complexity
Time O(ElogE)
Space O(N)


 */
