/*

Given the root of a binary tree and a leaf node, reroot the tree so that the leaf is the new root.

You can reroot the tree with the following steps for each node cur on the path starting from the leaf up to the root
excluding the root:

If cur has a left child, then that child becomes cur's right child.
cur's original parent becomes cur's left child. Note that in this process the original parent's pointer
to cur becomes null, making it have at most one child.
Return the new root of the rerooted tree.

Note: Ensure that your solution sets the Node.parent pointers correctly after rerooting or you will receive "Wrong Answer".

Input: root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 7
Output: [7,2,null,5,4,3,6,null,null,null,1,null,null,0,8]
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 0
Output: [0,1,null,3,8,5,null,null,null,6,2,null,null,7,4]


Constraints:

The number of nodes in the tree is in the range [2, 100].
-109 <= Node.val <= 109
All Node.val are unique.
leaf exist in the tree.



 */
package tree.medium;

public class ChangeRootOfBT {

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    Node original_root;

    public Node flipBinaryTree(Node root, Node leaf) {
        original_root = root;
        return helper(leaf, null); // the new parent of the leaf node must be null
    }

    public Node helper(Node node, Node new_parent) {
        Node old_parent = node.parent;
        node.parent = new_parent;

        if (node.left == new_parent) node.left = null;
        if (node.right == new_parent) node.right = null;

        if (node == original_root) return node;

        if (node.left != null) node.right = node.left;
        node.left = helper(old_parent, node);

        return node;
    }
}

/*

Think of the parent pointer as the next pointer in a Linked List. Follow the parent pointer,
climb up the list starting from the leaf node, and swap old parent with new parent.

For every node:

Save the old parent as a variable for later use
Point the parent pointer to the new parent (initially set to null)
Before we reset the right child and left child, clean the left and right children if they point to the new parent


 */