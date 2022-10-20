/*
There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1.
You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i.
Since node 0 is the root, parents[0] == -1.

Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed.
The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it.
The score of the node is the product of the sizes of all those subtrees.

Return the number of nodes that have the highest score.

       0
     /   \
   2      4
 /  \
3    1

Input: parents = [-1,2,0,2,0]
Output: 3
Explanation:
- The score of node 0 is: 3 * 1 = 3
- The score of node 1 is: 4 = 4
- The score of node 2 is: 1 * 1 * 2 = 2
- The score of node 3 is: 4 = 4
- The score of node 4 is: 4 = 4
The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.

Input: parents = [-1,2,0]
Output: 2
Explanation:
- The score of node 0 is: 2 = 2
- The score of node 1 is: 2 = 2
- The score of node 2 is: 1 * 1 = 1
The highest score is 2, and two nodes (node 0 and node 1) have the highest score.


Constraints:

n == parents.length
2 <= n <= 105
parents[0] == -1
0 <= parents[i] <= n - 1 for i != 0
parents represents a valid binary tree.

 */

package tree.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountHighestScoreNodes {

    public int countHighestScoreNodes(int[] parents) {
        Map<Integer, List<Integer>> childMap = new HashMap<>();
        int root = 0;
        // Compute child map.
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] != -1) {
                childMap.put(parents[i], childMap.getOrDefault(parents[i], new ArrayList<>()));
                childMap.get(parents[i]).add(i);
            } else {
                root = i;
            }
        }
        int[] totalCount = new int[parents.length];
        // Count total nodes below a subtree
        totalNodesInSubtree(childMap, root, totalCount);
        long maxScore = 1;
        long[] scores = new long[parents.length];
        for (int i = 0; i < parents.length; i++) {
            long score = 1;
            // If a parent multiple child counts.
            if (childMap.containsKey(i)) {
                for (int child : childMap.get(i)) {
                    score *= (totalCount[child]);
                }
            }
            // if node has a parent (subtract current node count from total node count to get parent node count)
            if (parents[i] != -1) {
                score *= ((totalCount[root]) - totalCount[i]);
            }
            scores[i]=score;
            maxScore = Math.max(maxScore, score);
        }

        int ans = 0;
        for (long x : scores) {
            if(x==maxScore) ans++;
        }
        return ans;
    }

    private int totalNodesInSubtree(Map<Integer, List<Integer>> map, int root, int[] totalCount) {
        if (!map.containsKey(root)) {
            totalCount[root] = 1;
            return totalCount[root];
        }
        for (int child : map.get(root)) {
            totalCount[root] += totalNodesInSubtree(map, child, totalCount);
        }
        totalCount[root]++;
        return totalCount[root];
    }

    public static void main(String[] args) {
        System.out.println(new CountHighestScoreNodes().countHighestScoreNodes(new int[]{-1,2,0}));
    }

}

/*

Another method:

class Solution {
    long maxScore; // stores the maximum score
    int count; // store the count of maximum score
    public int countHighestScoreNodes(int[] parent) {
        int n=parent.length;

        // creating the tree
        List<Integer> list[]=new ArrayList[n];
        for(int i=0;i<n;i++)
            list[i]=new ArrayList<>();
        for(int i=1;i<n;i++){
            list[parent[i]].add(i);
        }

        maxScore=0l;
        count=0;

        dfs(0,list,n);

        return count;
    }

    // returns the number of nodes in the subtree with root u.
    public int dfs(int u, List<Integer> list[], int n){

        int total=0; // stores total number of nodes in the subtree with root u, excluding u.
        long prod=1l,rem,val;

        // traversing children in the subtree
        for(int v:list[u]){
            val=dfs(v,list,n); // number of nodes in the subtree with root v.
            total+=val;
            prod*=val;
        }
        rem=(long)(n-total-1); // number of nodes in the remaining tree excluding the subtree with root u.

        if(rem>0)// only count the remaining part if there is at least one node.
            prod*=rem;

        // updating maxScore and count.
        if(prod>maxScore){
            maxScore=prod;
            count=1;
        }
        else if(prod==maxScore){
            count++;
        }

        return total+1; // returning total number of nodes in the subtree with node u.
    }
}

 */