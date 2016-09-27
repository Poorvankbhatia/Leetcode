/*

Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path need to be from parent to child (cannot be the reverse).
For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    /
   2
  /
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

 */

package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 27/09/16.
 */
public class LongestConsecutivePath {

    public int longestConsecutive(TreeNode root) {

        if(root==null) {
            return 0;
        }

        return longestConsecutiveHelper(root, 0, root.val + 1);

    }

    /*

           1
            \
             3
            / \
           2   4
                \
                 5

     */
    private int longestConsecutiveHelper(TreeNode root, int curLen, int target) {
        if (root == null) {
            return curLen;
        }

        if (root.val == target) {
            curLen += 1;
        } else {
            curLen = 1;
        }

        int left = longestConsecutiveHelper(root.left, curLen, root.val + 1);
        int right = longestConsecutiveHelper(root.right, curLen, root.val + 1);

        return Math.max(curLen, Math.max(left, right));
    }

}
