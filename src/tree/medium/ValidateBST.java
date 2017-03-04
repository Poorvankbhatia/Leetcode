/*

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 15/09/16.
 */
public class ValidateBST {

    private List<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
        return isValidBSTUtil(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }


    private boolean isValidBSTUtil(TreeNode root,long min,long max) {

        if(root==null) {
            return true;
        }
        if(root.val>=max || root.val<=min) {
            return false;
        }
        return isValidBSTUtil(root.left,min,root.val) && isValidBSTUtil(root.right,root.val,max);

    }

}
