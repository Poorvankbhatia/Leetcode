/*

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and
q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 13/09/16.
 */
public class LCABST {



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root==null) {
            return null;
        }

        while(true) {
            if(root.val>p.val && root.val>q.val) {
                root=root.left;
            }
            else if(root.val<p.val && root.val<q.val) {
                root=root.right;
            }
            else {
                break;
            }
        }

        return root;

    }

}
