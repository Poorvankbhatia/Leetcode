/*
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees
which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation:
    5
   /
  10

Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 20/08/17.
 */
public class EqualPartition {
    
    public boolean checkEqualTree(TreeNode root) {
        if(root!=null && root.left==null && root.right==null) {
            return false;
        }
        return checkEquality(root,0);
    }
    
    private boolean checkEquality(TreeNode root,int sum) {

        if(root==null) {
            return false;
        }

        /*
         Either break left branch or right branch
         */
        int rightSum=subTreeSum(root.right);
        int leftSum=subTreeSum(root.left);
        
        return sum+leftSum+root.val==rightSum || sum+rightSum+root.val==leftSum
                || checkEquality(root.left,rightSum+root.val+sum) || checkEquality(root.right,leftSum+root.val+sum);
        
    }
    
    private int subTreeSum(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null && root.right==null) {
            return root.val;
        }
        return subTreeSum(root.left)+subTreeSum(root.right)+root.val;
    }
    
}
