/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.


 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 15/09/16.
 */
public class BSTIterator {

    private List<Integer> inorderList = new ArrayList<>();
    private int index;

    public BSTIterator(TreeNode root) {
        fillInorder(root);
        index = 0;
    }

    public boolean hasNext() {
        return index < inorderList.size();
    }

    public int next() {
        int next =  inorderList.get(index);
        index++;
        return next;
    }

    private void fillInorder(TreeNode root) {
        if(root==null) {
            return;
        }

        fillInorder(root.left);
        inorderList.add(root.val);
        fillInorder(root.right);
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