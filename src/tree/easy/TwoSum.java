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

O(n) time, O(Logn) space and does not modify BST.

Binary search method

public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root,  k);
    }

    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur)
            || (root.val < value) && search(root.right, cur, value)
                || (root.val > value) && search(root.left, cur, value);
    }

 */
