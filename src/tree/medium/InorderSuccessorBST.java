/*

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 15/07/17.
 */
public class InorderSuccessorBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if(root==null) {
            return null;
        }

        if(p.right==null) {
            return null;
        }

        TreeNode parent = null;
        TreeNode current = root;
        while (current!=null && current.val!=p.val) {
            if(current.val>p.val) {
                parent = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if(current==null) {
            return null;
        }

        if(current.right==null) {
            return parent;
        }

        current = current.right;
        while (current.left!=null) {
            current = current.left;
        }

        return current;

    }

}

/*

Time is O(log(n)) and space is O(1).

 */