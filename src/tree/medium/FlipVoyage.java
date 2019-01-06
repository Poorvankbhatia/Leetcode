/*

Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.
Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child,
then preorder-traverse the right child.)

Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 06/01/19.
 */
public class FlipVoyage {

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> path = new ArrayList<>();
        if (preorder(root, voyage, 0, path) == -1) {
            List<Integer> result = new ArrayList<>();
            result.add(-1);
            return result;
        }
        return path;
    }
    private int preorder(TreeNode root, int[] voyage, int i, List<Integer> path) {
        if (root == null) {
            return i;
        }
        if (root.val != voyage[i]) {
            return -1;
        }
        int left = preorder(root.left, voyage, i + 1, path);
        if (left != -1) {
            return preorder(root.right, voyage, left, path);
        }
        // need a flip
        path.add(root.val);
        int right = preorder(root.right, voyage, i + 1, path);
        if (right != -1) {
            return preorder(root.left, voyage, right, path);
        }
        return -1;
    }

}
