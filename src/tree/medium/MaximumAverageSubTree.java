/*
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)


Input: 5
     /  \
    6    1
Output: 6.00000
Explanation:
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.

The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.

 */
package tree.medium;

import tree.TreeNode;

public class MaximumAverageSubTree {

    public double maximumAverageSubtree(TreeNode root) {
        return util(root)[2];
    }

    private double[] util(TreeNode root) {
        if(root==null) {
            return new double[]{0,0,0};//Current Value,Node count,Max Average so far
        }
        if(root.left == null && root.right==null) {
            return new double[]{root.val,1,root.val};
        }
        double[] left  =  util(root.left);
        double[] right =  util(root.right);

        double count = left[1]+right[1]+1;
        double val = root.val+left[0]+right[0];
        double max = Math.max(Math.max(left[2],right[2]),(val/count));

        return new double[]{(val),(count),max};

    }


}
