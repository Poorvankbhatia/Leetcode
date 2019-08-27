/*

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation:
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.

 */
package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxLevelSum {

    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null) {
            queue.add(root);
        } else {
            return 0;
        }
        int level = 1;
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i=0; i < size; i++) {
                TreeNode poll = queue.poll();
                sum += poll.val;
                if(poll.left != null) {
                    queue.add(poll.left);
                }
                if(poll.right != null) {
                    queue.add(poll.right);
                }
            }
            if(sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }
            level++;
        }

        return maxLevel;

    }

}
