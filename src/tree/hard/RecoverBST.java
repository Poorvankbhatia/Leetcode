/*

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 */

package tree.hard;

import tree.TreeNode;

/**
 * Created by poorvank on 16/09/16.
 */
public class RecoverBST {

    private static TreeNode first,previous,last,middle;
    public void recoverTree(TreeNode root) {
        first=last=previous=middle=null;

        correctTreeUtil(root);
        if (first != null && last != null) {
            swap(first, last);
        } else if (first != null && middle != null) {
            swap(first, middle);
        }
    }
    private static void swap(TreeNode n1, TreeNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
    private static void correctTreeUtil(TreeNode root) {
        if (root != null) {
            correctTreeUtil(root.left);
            if (previous != null && root.val < previous.val) {
                if (first == null) {
                    first = previous;
                    middle = root;
                    System.out.println();
                } else {
                    last = root;
                }
            }
            previous = root;
            correctTreeUtil(root.right);
        }
    }
}
