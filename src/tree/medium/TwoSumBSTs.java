/*

Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

 Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false


Constraints:

Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9

 */
package tree.medium;

import tree.TreeNode;

import java.util.Stack;

public class TwoSumBSTs {

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        // if either of the tree is empty
        if (root1 == null || root2 == null)
            return false;

        // stack 'stack1' used for the inorde traversal of BST 1
        // stack 'stack2' used for the reverse inorder traversal of BST 2
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode t1, t2;

        while (true) {
            // LeftMost Node.
            while (root1 != null) {
                stack1.push(root1);
                root1 = root1.left;
            }
            // RighMost Node.
            while (root2 != null) {
                stack2.push(root2);
                root2 = root2.right;
            }
            // If either is empty then break.
            if (stack1.empty() || stack2.empty())
                break;

            t1 = stack1.peek();
            t2 = stack2.peek();

            // if the sum of the node's is equal to 'target'
            if ((t1.val + t2.val) == target) {
                return true;
            }

            // move to next possible node in the inorder traversal of root 1
            else if ((t1.val + t2.val) < target) {
                stack1.pop();
                root1 = t1.right;
            }

            // move to next possible node in the reverse inorder traversal of root 2
            else {
                stack2.pop();
                root2 = t2.left;
            }
        }

        return false;
    }
}

/*

Traverse root 1 from smallest value to node to largest.
Traverse root 2 from largest value node to smallest.
Sum up the corresponding node’s value : If sum == target return true
 If target > sum,
 then move to the inorder successor of the current node of root1,
 else
 move to the inorder predecessor of the current node of root2.


 */