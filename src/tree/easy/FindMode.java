/*

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

 */
package tree.easy;

import tree.TreeNode;
import java.util.Arrays;

/**
 * Created by poorvank on 05/02/17.
 */
public class FindMode {

    private int currVal = 0;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;

    private int[] modes;

    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private void checkCurrentNode(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }


    private void inorder(TreeNode root) {

        if(root==null) {
            return;
        }
        inorder(root.left);
        checkCurrentNode(root.val);
        inorder(root.right);

    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);


        System.out.println(Arrays.toString(new FindMode().findMode(node)));
    }

}

/*

the way to do it properly is to do two passes.
One to find the highest number of occurrences of any value, and then a second pass to collect all values occurring that often.

a (two-pass) solution that I think can rightfully be called O(1) space. Both passes keep track of the current value etc,
and the second pass additionally collects the modes in the result array. I took the value handling out of the in-order
traversal into its own function for clarity.


O(N) time & O(N) Space

Use a dictionary to store the frequency of each interger. Then simply find the largest frequency and return all the associated keys.
Note we do not use the property of BST in this solution.

O(N) time and O(1) Space

Write BST Iterator class which gives the next element in_order. Now the problem reduces to finding mode in a sorted array.
Instead of a BST iterator, we can use a recursive inorder traversal and store a class variable pre to indicate the previous integer.

Divide and Conquer

Mode lies entirely in left subtree, or in right subtree or the middle element is the mode.
Time would be NlogN at best and space O(1)

 */