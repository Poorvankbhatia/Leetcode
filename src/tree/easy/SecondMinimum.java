/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node.
If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 04/09/17.
 */
public class SecondMinimum {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int res = secondMinValue(root, root.val);
        return res == root.val ? -1 : res;
    }

    private int secondMinValue(TreeNode root, int val) {

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int left = secondMinValue(root.left, val);
        int right = secondMinValue(root.right, val);

        // If any of the child's value is equal to the roots value return other child's value.
        if (left == val) {
            return right;
        } else if (right == val) {
            return left;
        } else {
            // if both child are greater than the roots value(Not immediate children but grand children, Take min of both)
            return Math.min(left, right);
        }

    }

}
