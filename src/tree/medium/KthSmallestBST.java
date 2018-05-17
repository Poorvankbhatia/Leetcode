/*

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 17/05/18.
 */
public class KthSmallestBST {

    public int kthSmallest(TreeNode root, int k) {
        int count  = countNodes(root.left);
        if(count>=k) {
            return kthSmallest(root.left,k);
        } else if(count==k-1) {
            return root.val;
        } else {
            return kthSmallest(root.right,k-1-count);
        }
    }

    private int countNodes(TreeNode root) {
        if(root==null) {
            return 0;
        }

        return 1+countNodes(root.left)+countNodes(root.right);
    }

}

/*

If we could add a count field in the BST node class, it will take O(n) time when we calculate the count value for the whole tree, but after that,
it will take O(logn) time when insert/delete a node or calculate the kth smallest element.

   public class Solution {
        public int kthSmallest(TreeNode root, int k) {
            TreeNodeWithCount rootWithCount = buildTreeWithCount(root);
            return kthSmallest(rootWithCount, k);
        }

        private TreeNodeWithCount buildTreeWithCount(TreeNode root) {
            if (root == null) return null;
            TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
            rootWithCount.left = buildTreeWithCount(root.left);
            rootWithCount.right = buildTreeWithCount(root.right);
            if (rootWithCount.left != null) rootWithCount.count += rootWithCount.left.count;
            if (rootWithCount.right != null) rootWithCount.count += rootWithCount.right.count;
            return rootWithCount;
        }

        private int kthSmallest(TreeNodeWithCount rootWithCount, int k) {
            if (k <= 0 || k > rootWithCount.count) return -1;
            if (rootWithCount.left != null) {
                if (rootWithCount.left.count >= k) return kthSmallest(rootWithCount.left, k);
                if (rootWithCount.left.count == k-1) return rootWithCount.val;
                return kthSmallest(rootWithCount.right, k-1-rootWithCount.left.count);
            } else {
                if (k == 1) return rootWithCount.val;
                return kthSmallest(rootWithCount.right, k-1);
            }
        }

        class TreeNodeWithCount {
            int val;
            int count;
            TreeNodeWithCount left;
            TreeNodeWithCount right;
            TreeNodeWithCount(int x) {val = x; count = 1;};
        }
    }

 */