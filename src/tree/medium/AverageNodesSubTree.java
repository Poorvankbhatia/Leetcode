/*
Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.

Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation:
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.

 */
package tree.medium;

import tree.TreeNode;

public class AverageNodesSubTree {

    public int averageOfSubtree(TreeNode root) {
        return compute(root)[2];
    }

    // Returns an int[] => 0 = total subtree sum, 1 = total node count, 2= averageNode count.
    private int[] compute(TreeNode node) {
        if(node == null) {
            return new int[3];
        }

        if(node.left==null && node.right==null) {
            return new int[]{node.val,1,1};
        }

        int[] result = new int[3];
        if(node.right!=null) {
            int[] right = compute(node.right);
            result[0] += right[0];
            result[1] += right[1];
            result[2] += right[2];
        }
        if(node.left!=null) {
            int[] left = compute(node.left);
            result[0] += left[0];
            result[1] += left[1];
            result[2] += left[2];
        }
        result[1]++;
        result[0]+=node.val;
        if(node.val==(result[0])/(result[1])) {
            result[2]++;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        System.out.println(new AverageNodesSubTree().averageOfSubtree(root));
    }

}
