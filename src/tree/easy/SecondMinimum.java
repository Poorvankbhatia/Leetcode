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
        if(root==null) {
            return -1;
        }
        int val = Math.min(findMinValGreaterThan(root.left,root.val),findMinValGreaterThan(root.right,root.val));
        return val==Integer.MAX_VALUE?-1:val;
    }

    private int findMinValGreaterThan(TreeNode root,int val) {
        if(root==null) {
            return Integer.MAX_VALUE;
        }
        if(root.val==val) {
            return Math.min(findMinValGreaterThan(root.left,val),findMinValGreaterThan(root.right,val));
        } else {
            return root.val;
        }
    }

}
