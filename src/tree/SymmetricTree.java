/*

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3

 */

package tree;

/**
 * Created by poorvank on 13/09/16.
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {

        return root == null || isMirror(root.left, root.right);

    }


    private boolean isMirror(TreeNode p,TreeNode q) {

        if(p==null && q==null) {
            return true;
        }

        if(p==null || q==null) {
            return false;
        }

        return (p.val==q.val) && ((isMirror(p.left,q.right) && isMirror(p.right,q.left)));

    }
}
