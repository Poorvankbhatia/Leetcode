/*

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in
the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

 */
package tree.hard;

import tree.TreeNode;

/**
 * Created by poorvank on 15/09/16.
 */
public class MaxPathSum {

    private static int finalResult  = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        if(root==null) {
            return 0;
        }

        maxPathSumUtil(root);
        return finalResult;
    }

    private int maxPathSumUtil(TreeNode root) {

        if(root==null) {
            return 0;
        }

        int leftSum = maxPathSumUtil(root.left);
        int rightSum = maxPathSumUtil(root.right);

        //Maximum path Sum to be returned to parent call
        int maxIntermediatePathSum = Math.max(Math.max(leftSum,rightSum)+root.val,root.val);

        int currentMaxValue = Math.max(maxIntermediatePathSum,root.val+leftSum+rightSum);

        finalResult = Math.max(finalResult,currentMaxValue);

        return maxIntermediatePathSum;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new MaxPathSum().maxPathSum(root));
    }

}


/*

Time out program:

 public int maxPathSum(TreeNode root) {

        if(root==null) {
            return 0;
        }

        return maxPathSumUtil(root);
    }

    private int maxPathSumUtil(TreeNode root) {
        if(root==null) {
            return Integer.MIN_VALUE;
        }

        if(root.right==null && root.left==null) {
            return root.val;
        }

        int leftSum = maxPathSumUtil(root.left);
        int rightSum = maxPathSumUtil(root.right);

        int leftPathSum = intermediateMaxPathSum(root.left,0,Integer.MIN_VALUE);
        int rightPathSum = intermediateMaxPathSum(root.right,0,Integer.MIN_VALUE);

        //In case both children are negative
        int maxIntermediatePathSum = Math.max(root.val,Math.max((root.val+leftPathSum),(root.val+rightPathSum)));

        return Math.max(Math.max(leftSum,rightSum),Math.max(maxIntermediatePathSum,(leftPathSum+rightPathSum+root.val)));
    }

    private int intermediateMaxPathSum(TreeNode root,int sum,int finalSum) {

        if(root==null) {
            return 0;
        }

        sum += root.val;
        if(finalSum<sum) {
            finalSum = sum;
        }

        if(root.right==null && root.left==null) {
            return finalSum;
        }

        return Math.max(intermediateMaxPathSum(root.right,sum,finalSum),intermediateMaxPathSum(root.left,sum,finalSum));

    }


For each node there can be four ways that the max path goes through the node:
1. Node only
2. Max path through Left Child + Node
3. Max path through Right Child + Node
4. Max path through Left Child + Node + Max path through Right Child

The idea is to keep trace of four paths and pick up the max one in the end. An important thing to note is,
root of every subtree need to return maximum path sum such that at most one child of root is involved. This is
needed for parent function call.

 */