/*

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length
 of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

 */

package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 19/03/17.
 */
public class Diameter {

    private int max=0;
    public int diameterOfBinaryTree(TreeNode root) {

        if(root==null) {
            return 0;
        }

        int lHeight = height(root.left);
        int rHeight = height(root.right);

        max= Math.max(max,(lHeight+rHeight+1));
        return max;
    }

    private int height(TreeNode root) {

        if(root==null) {
            return 0;
        } else {
            return Math.max(height(root.right),height(root.left))+1;
        }

    }

}

/*
 G I

 Another approach:
 class Solution {
    private int max=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) {
            return 0;
        }
        height(root);
        return max-1;
    }
     private int height(TreeNode root) {

        if(root==null) {
            return 0;
        }
        int lHeight = height(root.left);
        int rHeight = height(root.right);
        max= Math.max(max,(lHeight+rHeight+1));
        return Math.max(lHeight,rHeight)+1;
    }

}

 */