/*

Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
Output: 20
Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.

Input: root = [4,3,null,1,2]
Output: 2
Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
Example 3:

Input: root = [-4,-2,-5]
Output: 0
Explanation: All values are negatives. Return an empty BST.
Example 4:

Input: root = [2,1,3]
Output: 6
Example 5:

Input: root = [5,4,8,3,null,6,3]
Output: 7


Constraints:

Each tree has at most 40000 nodes..
Each node's value is between [-4 * 10^4 , 4 * 10^4].

 */
package tree.hard;

import tree.TreeNode;

public class MaximumSumBST {

    private class BSTInfo {
        boolean isBST;
        int sum;
        int currentMax;
        int max;
        int min;
        BSTInfo(int max, int min, boolean isBST, int sum, int currentSum) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.sum = sum;
            currentMax = currentSum;
        }
        BSTInfo() {
        }
    }

    private int maxSum;

    public int maxSumBST(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        int ans = util(root).currentMax;
        return ans<0?0:ans;
    }

    private BSTInfo util(TreeNode root) {
        if (root == null) {
            return new BSTInfo( Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0, 0 );
        }

        if (root.left == null && root.right == null) {
            maxSum = Math.max(maxSum, root.val);
            return new BSTInfo( root.val, root.val, true, root.val, maxSum);
        }

        // Store information about the left subtree
        BSTInfo leftTree = util(root.left);

        // Store information about the right subtree
        BSTInfo rightTree = util(root.right);

        BSTInfo bstInfo=new BSTInfo();

        // Check If the subtree rooted under the current node is a Binary Search Tree
        if (leftTree.isBST && rightTree.isBST && leftTree.max < root.val && rightTree.min > root.val) {
            bstInfo.max = Math.max(root.val, Math.max(leftTree.max, rightTree.max));
            bstInfo.min = Math.min(root.val, Math.min(leftTree.min, rightTree.min));
            maxSum = Math.max(maxSum, rightTree.sum + root.val + leftTree.sum);
            bstInfo.sum = rightTree.sum + root.val + leftTree.sum;
            // Update the current maximum sum
            bstInfo.currentMax = maxSum;
            bstInfo.isBST = true;
            return bstInfo;
        }

        // If the entire tree is not a Binary Search Tree then simply update the current maximum sum
        bstInfo.isBST = false;
        bstInfo.currentMax = maxSum;
        bstInfo.sum = rightTree.sum + root.val + leftTree.sum;
        return bstInfo;
    }

}
