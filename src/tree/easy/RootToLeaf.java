/*

Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers modulo 10^9 + 7.

 */
package tree.easy;

import tree.TreeNode;

public class RootToLeaf {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(0, root);
    }
    private int mod = 1000000007;
    public int dfs(int parent, TreeNode node) {
        if(null == node) {
            return parent%mod;
        }
        int value = (parent * 2 + node.val)%mod;
        if(node.left == null && node.right == null) {
            return value%mod;
        }
        int sum = 0;
        if(null != node.left) {
            sum += dfs(value, node.left);
        }
        if(null != node.right) {
            sum += dfs(value, node.right);
        }
        return sum%mod;
    }

}

/*

We recursively pass the current value of path to the children.
If root == null, no value, return 0.
If root != null,
we double the value from its parent and add the node's value,
like the process of transforming base 2 to base 10.

In the end of recursion,
if root.left == root.right == null,
return the current val.


Time Complexity:
O(N) time, O(logN) for recursion.

 */