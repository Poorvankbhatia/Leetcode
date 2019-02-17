/*

In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true



 */
package tree.easy;

import tree.TreeNode;

public class CousinsBT {

    public boolean isCousins(TreeNode root, int x, int y) {
        return level(root, x, 0) == level(root, y, 0) && !isSibling(root, x, y);
    }

    private int level(TreeNode root,int val,int level) {
        if(root!=null) {
            if(root.val==val) {
                return level;
            }
            int left = level(root.left,val,level+1);
            if(left!=-1) {
                return left;
            }
            return level(root.right,val,level+1);
        }
        return -1;
    }

    private boolean isSibling(TreeNode root,int a,int b) {
        return root != null && ((root.left!=null && root.right!=null && root.left.val == a && root.right.val == b) ||
                (root.left!=null && root.right!=null && root.right.val == a && root.left.val == b) || (isSibling(root.left, a, b) || isSibling(root.right, a, b)));
    }

}
