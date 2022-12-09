/*

You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n.
You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i]
will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.

Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -> 3 -> 2).

Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
Output: [3,2,3,2]
Explanation: We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).


Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
m == queries.length
1 <= m <= min(n, 104)
1 <= queries[i] <= n
queries[i] != root.val


 */
package tree.hard;

import tree.TreeNode;

import java.util.*;

public class HeightOfBTAfterSubtreeRemoval {

    Map<Integer, Integer> depth;
    Map<Integer, Integer> height;
    Map<Integer, Queue<int[]>> map;

    private void populateCousins() {
        for (int val : depth.keySet()) {
            int depth = this.depth.get(val);
            int height = this.height.get(val);
            if (!map.containsKey(depth)) {
                Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
                map.put(depth, queue);
            }
            Queue<int[]> queue = map.get(depth);
            queue.add(new int[]{val, height});
            map.put(depth, queue);
        }
    }

    private int populateDepthAndHeight(TreeNode root, int depth) {
        if (root == null) return -1;
        this.depth.put(root.val, depth);
        int maxHeight = Math.max(populateDepthAndHeight(root.left, depth + 1),
                populateDepthAndHeight(root.right, depth + 1)) + 1;
        height.put(root.val, maxHeight);
        return maxHeight;
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        depth = new HashMap<>();
        height = new HashMap<>();
        map = new HashMap<>();
        populateDepthAndHeight(root, 0);
        populateCousins();

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i];
            int depth = this.depth.get(val);
            Queue<int[]> cousins = map.get(depth);
            if (cousins.size() == 1) {
                result[i] = depth - 1;
            } else {
                int[] first = cousins.remove();
                if (first[0] == val) {
                    int[] second = cousins.remove();
                    result[i] = depth + second[1];
                    cousins.add(first);
                    cousins.add(second);
                } else {
                    result[i] = depth + first[1];
                    cousins.add(first);
                }
            }
        }
        return result;
    }

}
