/*
Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p
and value q in the tree.

The distance between two nodes is the number of edges on the path from one to the other.

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
Output: 3
Explanation: There are 3 edges between 5 and 0: 5-3-1-0.

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
Output: 2
Explanation: There are 2 edges between 5 and 7: 5-2-7.

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
Output: 0
Explanation: The distance between a node and itself is 0.


Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
All Node.val are unique.
p and q are values in the tree.

 */
package tree.medium;

import tree.TreeNode;

public class DistanceInBT {

    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = lowestCommonAncestor(root,p,q);
        int d1 = getDistance(lca,p,0);
        int d2 = getDistance(lca,q,0);
        return d1+d2;
    }

    private int getDistance(TreeNode root,int p,int d) {
        if(root==null) {
            return 0;
        }
        if(root.val==p) {
            return d;
        }
        int f = getDistance(root.left,p,d+1);
        if(f==0) {
            return getDistance(root.right,p,d+1);
        } else {
            return f;
        }
    }

    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if(root==null || root.val==p || root.val==q) {
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

}
/*
The answer = sum of distances between p-LCA and q-LCA
 */