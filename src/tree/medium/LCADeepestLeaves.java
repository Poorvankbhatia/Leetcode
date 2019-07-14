/*

Given a rooted binary tree, find the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.


Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Example 2:

Input: root = [1,2,3,4]
Output: [4]
Example 3:

Input: root = [1,2,3,4,5]
Output: [2,4,5]


Constraints:

The given tree will have between 1 and 1000 nodes.
Each node of the tree will have a distinct value between 1 and 1000.

 */
package tree.medium;

import tree.TreeNode;

public class LCADeepestLeaves {

    private class NodeVal {
        public TreeNode lowestCommonAncestor;//The lowestCommonAncestor of the deepest leaves on the current subtree
        public int nodeDepth;   //The nodeDepth of the deepest leaves on the current subtree

         NodeVal(int d, TreeNode n) {
            nodeDepth = d;
            lowestCommonAncestor = n;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        NodeVal res = find(root);
        return res.lowestCommonAncestor;
    }

    private NodeVal find(TreeNode root) {
        if (root == null) {
            return new NodeVal(0, null);
        } else {
            NodeVal left = find(root.left);
            NodeVal right = find(root.right);

            if (left.nodeDepth == right.nodeDepth) {
                return new NodeVal(left.nodeDepth + 1, root);
            } else {
                return new NodeVal(Math.max(right.nodeDepth, left.nodeDepth) + 1, right.nodeDepth > left.nodeDepth ?
                        right.lowestCommonAncestor : left.lowestCommonAncestor);
            }

        }
    }
}

/*

Another sol:

public class LCADeepestLeaves {
    private TreeNode res;
    private int maxDepth;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private int dfs(TreeNode node, int depth){
        if(node == null){
            return depth;
        }
        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth  +1);
        if(left == right && left >= maxDepth){
            res = node;
            maxDepth = left;
        }
        return Math.max(left, right);
    }
}

 */