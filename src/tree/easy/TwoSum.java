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
        TreeNode c1 = root;
        TreeNode c2 = root;

        while (!stack1.isEmpty() || !stack2.isEmpty() || c1!=null || c2!=null) {

            if(c1!=null || c2!=null) {
                while (c1!=null) {
                    stack1.push(c1);
                    c1=c1.left;
                }

                while (c2!=null) {
                    stack2.push(c2);
                    c2=c2.right;
                }
            } else {
                int val1 = stack1.peek().val;
                int val2 = stack2.peek().val;

                if(val1==val2) {
                    break;
                }

                if(val1+val2==k) {
                    return true;
                }
                if(val1+val2<k) {
                    c1=stack1.pop();
                    c1=c1.right;
                } else {
                    c2 = stack2.pop();
                    c2=c2.left;
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
