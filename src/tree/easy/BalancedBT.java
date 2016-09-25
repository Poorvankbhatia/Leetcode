/*

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree
in which the depth of the two subtrees of every node never differ by more than 1.

 */

package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 13/09/16.
 */
public class BalancedBT {

    private static class Height {
        int val;
        public Height(int val) {
            this.val = val;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedUtil(root,new Height(0));
    }

    private boolean isBalancedUtil(TreeNode root,Height h) {

        if(root==null) {
            h.val = 0;
            return true;
        }

        Height lHeight = new Height(0);
        Height rHeight = new Height(0);
        boolean l = isBalancedUtil(root.left,lHeight);
        boolean r = isBalancedUtil(root.right,rHeight);

        h.val = Math.max(lHeight.val,rHeight.val) + 1;

        if((lHeight.val-rHeight.val>=2) || (rHeight.val-lHeight.val)>=2) {
            return false;
        }

        return (l&&r);

    }


}
