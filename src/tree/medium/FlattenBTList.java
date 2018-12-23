/*

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

 */

package tree.medium;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by poorvank on 24/09/16.
 */
public class FlattenBTList {

    public void flatten(TreeNode root) {

        if(root==null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode prev = null;

        while (!stack.isEmpty()) {

            TreeNode current = stack.pop();

            if(prev!=null) {
                prev.right = current;
                prev.left = null;
            }

            prev = current;

            if(current.right!=null) {
                stack.push(current.right);
            }

            if(current.left!=null) {
                stack.push(current.left);
            }


        }

        while (root!=null) {
            System.out.println(root.right);
            root = root.right;
        }

    }

}

/*

If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

Better sol:

class Solution {

    private TreeNode prev=null;

    public void flatten(TreeNode root) {

        if(root==null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.left=null;
        root.right=prev;
        prev=root;
    }
}


 */