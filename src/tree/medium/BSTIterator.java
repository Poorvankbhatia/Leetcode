/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.


 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank on 15/09/16.
 */
public class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        while (root!=null) {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return stack.size()>0;
    }

    public int next() {
        TreeNode current = stack.pop();
        int result = current.val;
        if(current.right!=null) {
            current = current.right;
            while (current!=null) {
                stack.push(current);
                current = current.left;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(25);
        root.left = new TreeNode(6);
        root.right = new TreeNode(29);
        root.left.right = new TreeNode(20);
        root.left.right.left = new TreeNode(16);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(0);
        root.left.left.right = new TreeNode(5);

        BSTIterator i = new BSTIterator(root);
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }

    }

}

/*

In order traversal stack can also be used.

 */