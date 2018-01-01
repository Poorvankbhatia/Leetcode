/*

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node)
or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1

 */

package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 30/12/17.
 */
public class UpsideDownBT {

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        if(root==null || root.left==null) {
            return root;
        }

        TreeNode node = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return node;

    }


}

/*

O(n) space:



Iterative Soln O(1) space

 public TreeNode UpsideDownBinaryTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode temp = null;

        while (curr != null) {
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

 */