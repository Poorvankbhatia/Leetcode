/*

We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)




 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 08/04/18.
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {

        if(root==null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if(root.left==null && root.right==null && root.val==0) {
            return null;
        }

        return root;

    }

}
