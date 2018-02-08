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

        if (p != null) {

            if (p.right != null) {
                return minValue(p.right);
            }

            TreeNode successor = null;

            while (root != null) {

                if (p.val < root.val) {
                    successor = root;
                    root = root.left;
                } else if (p.val > root.val) {
                    root = root.right;
                } else {
                    break;
                }

            }

            return successor;

        }


        return null;

    }


    private TreeNode minValue(TreeNode root) {

        if (root != null) {

            TreeNode current = root;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        return null;

    }

}

/*

Time is O(log(n)) and space is O(1).

 */