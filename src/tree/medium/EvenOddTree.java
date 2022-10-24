/*
A binary tree is named Even-Odd if it meets the following conditions:

The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
Output: true
Explanation: The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.


Input: root = [5,4,2,3,3,7]
Output: false
Explanation: The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.

Input: root = [5,9,1,3,5,7]
Output: false
Explanation: Node values in the level 1 should be even integers.


Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 106
 */
package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                boolean oddLevel = (level & 1)==1;
                if((!oddLevel && (poll.val & 1)==0) || (oddLevel && (poll.val & 1)==1)) {
                    return false;
                }
                if((!oddLevel && poll.val<=max) || (oddLevel && poll.val>=min)) {
                    return false;
                }
                if (oddLevel) {
                    min = poll.val;
                } else {
                    max = poll.val;
                }
                if(poll.left!=null) {
                    queue.add(poll.left);
                }
                if(poll.right!=null) {
                    queue.add(poll.right);
                }
            }
            level++;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(10);
        node.right = new TreeNode(8);
        System.out.println(new EvenOddTree().isEvenOddTree(node));
    }
}
