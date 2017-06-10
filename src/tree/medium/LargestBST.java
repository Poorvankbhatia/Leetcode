/*

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 10/06/17.
 */
public class LargestBST {

    private class BSTInfo {

        int max;
        int min;
        boolean isBST;
        int size;
        int val;

        public BSTInfo(int max, int min, boolean isBST, int size, int val) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.size = size;
            this.val = val;
        }

        public BSTInfo() {
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        return util(root).val;
    }

    private BSTInfo util(TreeNode root) {

        if(root==null) {
            return new BSTInfo(Integer.MIN_VALUE,Integer.MAX_VALUE,true,0,0);
        }

        if(root.left==null && root.right==null) {
            return new BSTInfo(root.val,root.val,true,1,1);
        }

        BSTInfo left = util(root.left);
        BSTInfo right = util(root.right);

        BSTInfo result = new BSTInfo();
        result.size = 1+left.size+right.size;

        // If whole tree rooted under current root is
        // BST.
        if (left.isBST && right.isBST && left.max < root.val && right.min > root.val)
        {
            result.min = left.min;
            result.max = right.max;

            // Update answer for tree rooted under
            // current 'root'
            result.val = result.size;
            result.isBST = true;

            return result;
        }

        // If whole tree is not BST, return maximum
        // of left and right subtrees
        result.val = Math.max(left.val, right.val);
        result.isBST = false;

        return result;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        root.left    = new TreeNode(30);
        root.right   = new TreeNode(60);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(20);
        root.right.left = new TreeNode(55);
        root.right.left.left = new TreeNode(45);
        root.right.right = new TreeNode(70);
        root.right.right.left = new TreeNode(65);
        root.right.right.right = new TreeNode(80);

        System.out.println(new LargestBST().largestBSTSubtree(root));
    }

}

/*

A Tree is BST if following is true for every node x.

The largest value in left subtree (of x) is smaller than value of x.
The smallest value in right subtree (of x) is greater than value of x.
We traverse tree in bottom up manner. For every traversed node, we return maximum and minimum values in subtree rooted with it.

O(n)

 */