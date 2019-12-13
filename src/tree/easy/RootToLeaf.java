/*

Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

 */
package tree.easy;

import tree.TreeNode;

public class RootToLeaf {
    public int sumRootToLeaf(TreeNode root) {
        return util(root,0);
    }
    private int util(TreeNode root,int val) {
        if(root==null) {
            return 0;
        }
        val = (val*2)+root.val;
        if(root.left==null && root.right==null) {
            return val;
        }
        return util(root.left,val)+util(root.right,val);
    }
}

/*

Same as : SumRootToLeafNumbers

 */