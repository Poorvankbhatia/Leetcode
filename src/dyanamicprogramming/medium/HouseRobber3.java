/*

The thief has found himself a new place for his thievery again. There is only one entrance to this area,
called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief
realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked
houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

 */
package dyanamicprogramming.medium;


import tree.TreeNode;

/**
 * Created by poorvank on 17/09/16.
 */
public class HouseRobber3 {

    public int rob(TreeNode root) {

        int[] result = robUtil(root);
        return Math.max(result[0],result[1]);

    }

    private int[] robUtil(TreeNode root) {

        int[] result = new int[2];
        if(root == null){
            result = new int[]{0, 0};
            return result;
        }


        int[] left = robUtil(root.left);
        int[] right = robUtil (root.right);

        // result[0] is when root is selected, result[1] is when not.
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(new HouseRobber3().rob(root));
    }

}
