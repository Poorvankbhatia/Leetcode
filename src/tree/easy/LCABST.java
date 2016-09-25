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

        if(root==p || root ==q) {
            return root;
        }

        if(p.val > q.val) {
            if(root.val>q.val && root.val<p.val) {
                return root;
            } else if(root.val < q.val) {
                return lowestCommonAncestor(root.right,p,q);
            } else {
                return lowestCommonAncestor(root.left,p,q);
            }
        } else {
            if(root.val<q.val && root.val>p.val) {
                return root;
            } else if(root.val < p.val) {
                return lowestCommonAncestor(root.right,p,q);
            } else {
                return lowestCommonAncestor(root.left,p,q);
            }
        }

    }

}
