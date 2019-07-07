/*

Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.


       1
     /   \
    2    3
  /  \  / \
 4   5 6  7
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]


Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.

 */
package tree.medium;

import tree.TreeNode;

import java.util.*;

public class DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new ArrayList<>();
        if (root == null) return forest;
        Set<Integer> set = new HashSet<>();
        for(int i : to_delete) {
            set.add(i);
        }
        deleteNodes(root, set, forest);
        if (!set.contains(root.val)) {
            forest.add(root);
        }
        return forest;
    }

    private TreeNode deleteNodes(TreeNode node, Set<Integer> set, List<TreeNode> forest) {
        if (node == null) return null;

        node.left = deleteNodes(node.left, set, forest);
        node.right = deleteNodes(node.right, set, forest);

        if (set.contains(node.val)) {
            if (node.left != null) forest.add(node.left);
            if (node.right != null) forest.add(node.right);
            return null;
        }

        return node;
    }

}
