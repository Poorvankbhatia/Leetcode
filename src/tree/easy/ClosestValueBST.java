/*

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 11/06/17.
 */
public class ClosestValueBST {

    private double min = Double.MAX_VALUE;
    private int ans = 0;
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        closestValueHelper(root, target);

        return ans;
    }

    private void closestValueHelper(TreeNode root, double target) {
        if (root == null) {
            return;
        }

        if (Math.abs((double) root.val - target) < min) {
            min = Math.abs((double) root.val - target);
            ans = root.val;
        }

        if (root.val > target) {
            closestValueHelper(root.left, target);
        } else if (root.val < target) {
            closestValueHelper(root.right, target);
        }
    }

}
