/*

Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid,
but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be
parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 09/04/17.
 */
public class LongestConsecutivePath2 {

    private int max = 0;

    private class Result {

        TreeNode node;
        int maxValue;
        int increasing;
        int decreasing;

        public Result() {
        }
    }

    public int longestConsecutive(TreeNode root) {

        if(root==null) {
            return 0;
        }

        int val = longestConsecutiveUtil(root).maxValue;
        return Math.max(max,val);

    }

    private Result longestConsecutiveUtil(TreeNode node) {

        if (node == null) return null;

        Result left = longestConsecutiveUtil(node.left);
        Result right = longestConsecutiveUtil(node.right);

        Result current = new Result();
        current.node = node;
        current.increasing = 1;
        current.decreasing = 1;

        if (left != null) {
            if (node.val - left.node.val == 1) {
                current.increasing = Math.max(current.increasing, left.increasing + 1);
            }
            else if (left.node.val - node.val == 1) {
                current.decreasing = Math.max(current.decreasing, left.decreasing + 1);
            }
        }

        if (right != null) {
            if (node.val - right.node.val == 1) {
                current.increasing = Math.max(current.increasing, right.increasing + 1);
            }
            else if (right.node.val - node.val == 1) {
                current.decreasing = Math.max(current.decreasing, right.decreasing + 1);
            }
        }

        max = Math.max(max, current.increasing + current.decreasing - 1);

        return current;

    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(2);
        System.out.print(new LongestConsecutivePath2().longestConsecutive(node));
    }

}
