/*

Convert a BST to a sorted circular doubly-linked list in-place.
Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

 */
package tree.medium;

import tree.TreeNode;

public class BSTToDLL {

    private TreeNode pre = null;
    private TreeNode head = null;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(TreeNode root) {
        if (root!=null) {
            inorder(root.left);
            if (pre == null) {
                head = root;
            } else {
                pre.right = root;
                root.left = pre;
            }
            pre = root;
            inorder(root.right);
        }
    }


}
