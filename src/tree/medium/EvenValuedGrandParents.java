/*

Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.

 */
package tree.medium;

import tree.TreeNode;

public class EvenValuedGrandParents {

    private int count=0;
    public int sumEvenGrandparent(TreeNode root) {
        if(root==null) {
            return 0;
        }
        preorder(null,null,root);
        return count;
    }

    private void preorder(TreeNode grandparent, TreeNode parent, TreeNode child) {
        if(grandparent!=null && parent!=null && child!=null && (grandparent.val%2==0)) {
            count+=child.val;
        }
        if(child==null)
            return;
        preorder(parent,child,child.left);
        preorder(parent,child,child.right);
    }

}
