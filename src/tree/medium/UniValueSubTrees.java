/*

Given a binary tree, count the number of uni-value subtrees.
A Uni-value subtree means all nodes of the subtree have the same value.
For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 27/09/16.
 */
public class UniValueSubTrees {

    private static int count;

    public int countUnivalSubtrees(TreeNode root) {

        if(root==null) {
            return 0;
        }

        count = 0;

        countUtil(root);

        return count;

    }

    private  boolean countUtil(TreeNode root) {

        if(root==null) {
            return true;
        }

        boolean left = countUtil(root.left);
        boolean right = countUtil(root.right);

        if(!left || !right) {
            return false;
        }

        if(root.left!=null && root.left.val!=root.val) {
            return false;
        }

        if(root.right!=null && root.right.val!=root.val) {
            return false;
        }

        count++;

        return true;


    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        System.out.println(new UniValueSubTrees().countUnivalSubtrees(root));
    }

}
