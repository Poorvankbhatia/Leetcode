/*

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 26/02/17.
 */
public class MinimumAbsoluteDiff {

    private TreeNode previous = null;
    private int difference = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {

        diffUtil(root);
        return difference;
    }

    private void diffUtil(TreeNode root) {

       if(root!=null) {

           diffUtil(root.left);

           if(previous!=null) {
                if(difference >Math.abs(root.val-previous.val)) {
                    difference = Math.abs(root.val-previous.val);
                }
           }

           previous = root;
           diffUtil(root.right);

       }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);

        System.out.println(new MinimumAbsoluteDiff().getMinimumDifference(root));
    }

}

/*

Same concept used as Recover a BST using standard inorder traversal

 */
