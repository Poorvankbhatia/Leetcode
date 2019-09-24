/*

There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1
if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted list.
There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in
the sorted array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty list if there is no solution.



Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
Output: [6,3,4,1,5,2,0,7]
Example 2:

Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
Output: []
Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.



Constraints:

1 <= m <= n <= 3*10^4
group.length == beforeItems.length == n
-1 <= group[i] <= m-1
0 <= beforeItems[i].length <= n-1
0 <= beforeItems[i][j] <= n-1
i != beforeItems[i][j]
beforeItems[i] does not contain duplicates elements.

 */
package graph.hard;

import java.util.*;

public class SortItemsByGroups {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Item dependency graph creation.
        Map<Integer,Set<Integer>> itemGraph = new HashMap<>();
        Map<Integer,Integer> itemInDegree = new HashMap<>();

        for (int i=0;i<n;i++) {
            itemGraph.putIfAbsent(i,new HashSet<>());
        }

        // Group dependency graph creation
        Map<Integer,Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer,Integer> groupInDegree = new HashMap<>();

        for (int g : group) {
            groupGraph.putIfAbsent(g,new HashSet<>());
        }

        for (int i=0;i<beforeItems.size();i++) {
            List<Integer> list = beforeItems.get(i);
            if(list.size()!=0) {
                for (int val : list) {
                    itemGraph.get(val).add(i);
                    itemInDegree.put(i,itemInDegree.getOrDefault(i,0)+1);
                    // If an item(i1) is dependent on another(i2) then its group(g1) should also be dependent on (g2)
                    int g1 = group[val];
                    int g2 = group[i];
                    if (g1 != g2 && groupGraph.get(g1).add(g2)) {
                        groupInDegree.put(g2,groupInDegree.getOrDefault(g2,0)+1);
                    }
                }
            }
        }

        List<Integer> itemOrdering = topologicalSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrdering = topologicalSort(groupGraph, groupInDegree, groupGraph.size());

        // In case we find a cycle.
        if(itemOrdering.size()==0 || groupOrdering.size()==0) {
            return new int[0];
        }

        Map<Integer,List<Integer>> map = new HashMap<>();

        // Put items in respective buckets.
        for (int item : itemOrdering) {
            int g = group[item];
            map.putIfAbsent(g,new ArrayList<>());
            map.get(g).add(item);
        }

        int[] result = new int[n];
        int i=0;

        // Get result, by looping over group ordering.
        for (int g : groupOrdering) {
            List<Integer> list = map.get(g);
            for (int item : list) {
                result[i] = item;
                i++;
            }
        }

        return result;

    }

    // Kahn’s algorithm
    private List<Integer> topologicalSort(Map<Integer,Set<Integer>> graph,
                                          Map<Integer,Integer> inDegree,int count) {

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int key : graph.keySet()) {
            if(inDegree.getOrDefault(key,0)==0) {
                queue.add(key);
            }
        }

        while (!queue.isEmpty()) {
            int pop = queue.poll();
            count--;
            result.add(pop);
            for (int next : graph.get(pop)) {
                int val = inDegree.get(next);
                inDegree.put(next,val-1);
                if(inDegree.get(next) ==0) {
                    queue.add(next);
                }
            }
        }
        return count==0 ? result : new ArrayList<>();

    }


}
