/*
Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values
and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input:
         1
       /   \
      2     3
Output: 1
Explanation:
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1

 */

package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 23/04/17.
 */
public class Tilt {


    public int findTilt(TreeNode root) {

        int[] result = tiltUtil(root);
        return result[0];

    }

    // Fist element of the array contains tilt of the tree and 2nd its cumulative sum.
    private int[] tiltUtil(TreeNode root) {

        if(null==root) {
            return new int[]{0,0};
        }

        if(isLeaf(root)) {
            return new int[]{0,root.val};
        }

        int[] left = tiltUtil(root.left);
        int[] right = tiltUtil(root.right);

        int currentTilt = Math.abs(left[1]-right[1]);
        return new int[]{(currentTilt+left[0]+right[0]),root.val+left[1]+right[1]};

    }

    private boolean isLeaf(TreeNode root) {
        return root.right==null && root.left==null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(new Tilt().findTilt(root));
    }

}


/*

Can also be done using postorder traversal :

public class Solution {

    int tilt = 0;

    public int findTilt(TreeNode root) {
        postorder(root);
        return tilt;
    }

    public int postorder(TreeNode root) {
        if (root == null) return 0;
        int leftSum = postorder(root.left);
        int rightSum = postorder(root.right);
        tilt += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }

}

 */