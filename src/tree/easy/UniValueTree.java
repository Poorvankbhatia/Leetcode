/*

A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 30/12/18.
 */
public class UniValueTree {

    public boolean isUnivalTree(TreeNode root) {
        if(root==null) {
            return true;
        }

        if(root.left==null && root.right==null) {
            return true;
        }

        boolean left = isUnivalTree(root.left);
        boolean right = isUnivalTree(root.right);

        if(!left || !right) {
            return false;
        }

        if(root.left!=null && root.val!=root.left.val) {
            return false;
        }

        if(root.right!=null && root.val!=root.right.val) {
            return false;
        }

        return true;

    }

}
