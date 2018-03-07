/*

Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \
    1   3

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
Note:

The size of the BST will be between 2 and 100.
The BST is always valid, each node's value is an integer, and each node's value is different.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 11/02/18.
 */
public class MinimumDistanceBetweenTwoBSTNodes {

    TreeNode prev=null;
    Integer minDiff = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        inorderTraversal(root);
        return minDiff;
    }

    private void inorderTraversal(TreeNode root) {

        if(root!=null) {
            inorderTraversal(root.left);
            if(prev!=null) {
                minDiff = Math.min(root.val-prev.val,minDiff);
            }
            prev = root;
            inorderTraversal(root.right);
        }

    }

}
