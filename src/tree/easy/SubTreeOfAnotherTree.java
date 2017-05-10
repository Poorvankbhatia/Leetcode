/*

Given two non-empty binary trees s and t, check whether tree t has exactly the same
structure and node values with a subtree of s. A subtree of s is a tree consists of a node in
s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 07/05/17.
 */
public class SubTreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (t == null) {
            return true;
        }

        if (s == null) {
            return false;
        }

        return isIdentical(s, t) || (isSubtree(s.left, t) || isSubtree(s.right, t));

    }


    public boolean isIdentical(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }


        return (root1.val == root2.val && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));


    }
}
