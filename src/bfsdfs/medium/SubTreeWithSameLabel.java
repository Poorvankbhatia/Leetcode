/*
Given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and
exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case
character given in the string labels (i.e. The node with the number i has the label labels[i]).

The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.

Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.

A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
Output: [2,1,1,1,1,1,1]
Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice
that any node is part of its sub-tree.
Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than
node 1, the answer is just 1 (the node itself).

Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
Output: [4,2,1,1]
Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
The sub-tree of node 3 contains only node 3, so the answer is 1.
The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.

Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
Output: [3,2,1,1,1]
Example 4:

Input: n = 6, edges = [[0,1],[0,2],[1,3],[3,4],[4,5]], labels = "cbabaa"
Output: [1,2,1,1,2,1]
Example 5:

Input: n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[4,5],[5,6]], labels = "aaabaaa"
Output: [6,5,4,1,3,2,1]


Constraints:

1 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
labels.length == n
labels is consisting of only of lower-case English letters.
 */
package bfsdfs.medium;

import java.util.*;

public class SubTreeWithSameLabel {

    public int[] countSubTrees(int n, int[][] edges, String labels) {

        List<Integer>[] lists = new List[n];
        for (int[] edge : edges) {
            if(lists[edge[0]]==null) {
                lists[edge[0]]=new ArrayList<>();
            }
            if(lists[edge[1]]==null) {
                lists[edge[1]]=new ArrayList<>();
            }
            lists[edge[0]].add(edge[1]);
            lists[edge[1]].add(edge[0]);
        }


        int[] ans = new int[n];
        dfs(0, ans, new boolean[n], lists, labels);
        return ans;
    }

    // Returns a map of the current count of each label.
    private Map<Character,Integer> dfs(int start, int[] ans, boolean[] visited, List<Integer>[] lists,
                     String labels) {
        visited[start] = true;
        char label = labels.charAt(start);
        Map<Character,Integer> returnMap = new HashMap<>();
        returnMap.put(label,1);
        if(lists[start]!=null) {
            for (int child : lists[start]) {
                if(!visited[child]) {
                    Map<Character,Integer> map = dfs(child, ans, visited, lists, labels);
                    // Merging all child maps.
                    for (Map.Entry<Character,Integer> entry : map.entrySet()) {
                        returnMap.put(entry.getKey(),returnMap.getOrDefault(entry.getKey(),0)+entry.getValue());
                    }
                }
            }
        }
        ans[start]+=returnMap.getOrDefault(label,0);
        return returnMap;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0,1},
                {0,3},
                {1,2}
        };
        System.out.println(Arrays.toString(new SubTreeWithSameLabel().countSubTrees(4, edges, "bbbb")));
    }

}
