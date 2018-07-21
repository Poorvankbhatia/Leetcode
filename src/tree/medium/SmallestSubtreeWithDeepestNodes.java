/*

Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in it's subtree.



Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]

 */
package tree.medium;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 09/07/18.
 */
public class SmallestSubtreeWithDeepestNodes {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        getHeight(root, map);
        int leftHeight = map.get(root.left) == null ? 0 : map.get(root.left);
        int rightHeight = map.get(root.right) == null ? 0 : map.get(root.right);
        if (leftHeight > rightHeight) {
            return subtreeWithAllDeepest(root.left);
        } else if (leftHeight < rightHeight) {
            return subtreeWithAllDeepest(root.right);
        } else {
            return root;
        }
    }

    private int getHeight(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int h = Math.max(getHeight(root.left, map), getHeight(root.right, map)) + 1;
        map.put(root, h);
        return h;
    }

}

/*

 O(N), where N is the number of nodes in the tree.

 */
