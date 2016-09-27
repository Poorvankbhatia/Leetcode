/*

Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

 */

package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 27/09/16.
 */
public class LeftLeavesSum {

    public int sumOfLeftLeaves(TreeNode root) {

        return sum(root,false,0);

    }

    private int sum(TreeNode root,boolean flag,int s) {

        if(root==null) {
            return 0;
        }

        if(root.left==null && root.right==null && flag) {
            s += root.val;
            return s;
        }

        return (sum(root.left,true,s) + sum(root.right,false,s));

    }

}
