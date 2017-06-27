/*

Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d.
The root node is at depth 1.

The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as
 N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its
 original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all,
 then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.

Example 1:
Input:
A binary tree as following:
       4
     /   \
    2     6
   / \   /
  3   1 5

v = 1

d = 2

Output:
       4
      / \
     1   1
    /     \
   2       6
  / \     /
 3   1   5

Example 2:
Input:
A binary tree as following:
      4
     /
    2
   / \
  3   1

v = 1

d = 3

Output:
      4
     /
    2
   / \
  1   1
 /     \
3       1
Note:
The given d is in range [1, maximum depth of the given tree + 1].
The given binary tree has at least one tree node.

 */
package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 22/06/17.
 */
public class AddOneRow {

    public TreeNode addOneRow(TreeNode root, int v, int d) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //If u need to add value to the root node.
        if(d==1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        int current = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (d - 1 == current) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.remove();
                    TreeNode left = node.left;
                    TreeNode right = node.right;
                    node.left = new TreeNode(v);
                    node.right = new TreeNode(v);
                    node.left.left = left;
                    node.right.right = right;
                }
                break;
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.remove();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }

                }
                current++;
            }

        }

        return root;

    }

}
