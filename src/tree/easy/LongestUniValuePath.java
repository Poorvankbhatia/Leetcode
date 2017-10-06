/*
Given a binary tree, find the length of the longest path where each node in the path has the same value.
This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 01/10/17.
 */
public class LongestUniValuePath {

    private int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {

        util(root);
        return maxPath;

    }

    private int util(TreeNode root) {
        if(root==null) {
            return 0;
        }


        int left = util(root.left);
        int right = util(root.right);

        int leftSum=0,rightSum=0;
        if(root.left!=null && root.left.val==root.val) {
            leftSum=left+1;
        }

        if(root.right!=null && root.right.val==root.val) {
            rightSum=right+1;
        }

        maxPath = Math.max(maxPath,leftSum+rightSum);

        return Math.max(leftSum,rightSum);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right=new TreeNode(5);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(4);
        root.right.left=new TreeNode(5);
        System.out.print(new LongestUniValuePath().longestUnivaluePath(root));
    }

}
