/*

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal
to the given target.

Example 1:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

 */
package tree.easy;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by poorvank on 06/08/17.
 */
public class TwoSum {

    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode cur1 = root;
        TreeNode cur2 = root;

        while (!stack1.isEmpty() || !stack2.isEmpty() || cur1 != null || cur2 != null) {
            if (cur1 != null || cur2 != null) {
                if (cur1 != null) {
                    stack1.push(cur1);
                    cur1 = cur1.left;
                }

                if (cur2 != null) {
                    stack2.push(cur2);
                    cur2 = cur2.right;
                }
            } else {
                int val1 = stack1.peek().val;
                int val2 = stack2.peek().val;

                if (stack1.peek() == stack2.peek()) break;

                if (val1 +  val2 == k) return true;

                if (val1 + val2 < k) {
                    cur1 = stack1.pop();
                    cur1 = cur1.right;
                } else {
                    cur2 = stack2.pop();
                    cur2 = cur2.left;
                }
            }
        }

        return false;
    }

}

/*

O(n) time, O(Logn) space and doesn’t modify BST.

We traverse BST in Normal Inorder and Reverse Inorder simultaneously. In reverse inorder, we start from the rightmost node which is the maximum
value node. In normal inorder, we start from the left most node which is minimum value node. We add sum of current nodes in both traversals
and compare this sum with given target sum. If the sum is same as target sum, we return true. If the sum is more than target sum, we move
to next node in reverse inorder traversal, otherwise we move to next node in normal inorder traversal. If any of the traversals is
finished without finding a pair, we return false.

 */
