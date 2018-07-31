/*

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 10/06/17.
 */
public class LargestBST {

    private class Result {  // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
        int size;
        int lower;
        int upper;

        Result(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }

    int max = 0;

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) { return 0; }
        traverse(root);
        return max;
    }

    private Result traverse(TreeNode root) {
        if (root == null) { return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE); }
        Result left = traverse(root.left);
        Result right = traverse(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
            return new Result(-1, 0, 0);
        }
        int size = left.size + 1 + right.size;
        max = Math.max(size, max);
        return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
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