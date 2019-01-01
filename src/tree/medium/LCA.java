/*

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
since a node can be a descendant of itself according to the LCA definition.

 */

package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 05/10/16.
 */
public class LCA {

    private static final int ONE_MATCH=1;
    private static final int TWO_MATCH=2;
    private static final int ZERO_MATCH=0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (q == p && (root.left == q || root.right == q)) {
            return root;
        }
        int nodesFromLeft = root.left!=null?findMatches(root.left, p, q):0;
        if (nodesFromLeft == TWO_MATCH) {
            if (root.left == p || root.left == q) {
                return root.left;
            } else {
                return lowestCommonAncestor(root.left, p, q);
            }
        } else if (nodesFromLeft == ONE_MATCH) {
            if (root == p) {
                return p;
            } else if (root == q) {
                return q;
            }
        }


        int nodesFromRight = root.right!=null?findMatches(root.right, p, q):0;
        if (nodesFromRight == TWO_MATCH) {
            if (root.right == p || root.right == q) {
                return root.right;
            } else {
                return lowestCommonAncestor(root.right, p, q);
            }
        } else if (nodesFromRight == ONE_MATCH) {
            if (root == p) {
                return p;
            } else if (root == q) {
                return q;
            }
        }

        if (nodesFromLeft == ONE_MATCH && nodesFromRight == ONE_MATCH) {
            return root;
        }

        return null;

    }


    private int findMatches(TreeNode root,TreeNode p,TreeNode q) {

        int value = ZERO_MATCH;

        if (root == null) {
            return value;
        }

        if (root == p || root == q) {
            value = 1;
        }

        value += findMatches(root.left, p, q);

        if (value == TWO_MATCH) {
            return value;
        }

        return value + findMatches(root.right, p, q);
    }
}

/*

Another Sol:
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left==null) {
            return right;
        }else if(right==null) {
            return left;
        } else {
            return root;
        }


    }

 */