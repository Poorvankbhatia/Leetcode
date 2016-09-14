/*

Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

 */
package tree;

/**
 * Created by poorvank on 13/09/16.
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p==null && q==null) {
            return true;
        }

        if(p==null || q==null) {
            return false;
        }

        return ((p.val==q.val) && isSameTree(p.left,q.left) && isSameTree(p.right,q.right));

    }

}
