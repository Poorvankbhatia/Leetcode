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
        inorder(root);
        return isValidBSTUtil();
    }


    private void inorder(TreeNode root) {

        if(root==null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);

    }

    private boolean isValidBSTUtil() {

        for (int i=0;i<list.size()-1;i++) {
            if(list.get(i+1)<list.get(i)) {
                return false;
            }
        }

        return true;

    }

}
