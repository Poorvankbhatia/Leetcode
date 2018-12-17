/*

Given a binary tree and a sum, determine if the tree has a root-to-leaf path
such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.



 */

package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 13/09/16.
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) {
            return false;
        }
        return util(root,sum);
    }

    private boolean util(TreeNode root,int sum) {
        if(root==null) {
            return sum==0;
        }
        sum -=root.val;
        if(sum==0 && root.left==null && root.right==null) {
            return true;
        }

        return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
    }

}

/*

Alternate Sol:

public boolean hasPathSum(TreeNode root, int sum) {

        if(root==null) {
            return false;
        }

        if(root.left==null && root.right==null) {
            return sum==root.val;
        }

        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);

    }

 */