/*
Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation:
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.


Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.
 */
package tree.medium;

import tree.TreeNode;

public class MaxDiff {

    int diff= Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        if(root==null){
            return 0;
        }

        helper(root, root.val, root.val);
        return diff;
    }

    public void helper(TreeNode root, int min, int max){
        if(root==null){
            return;
        }

        int localMin = Math.min(root.val,min);
        int localMax = Math.max(root.val, max);

        diff = Math.max(diff, localMax - localMin);
        helper(root.left, localMin, localMax);
        helper(root.right,localMin, localMax);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(3);
        root.left.left.left.left = new TreeNode(1);

        System.out.println(new MaxDiff().maxAncestorDiff(root));
    }


}

/*
Another soln :
    private int ans=0;

    public int maxAncestorDiff(TreeNode root) {
        util(root);
        return ans;
    }

    private int[] util(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val, root.val};
        }
        int[] r = util(root.right);
        int[] l = util(root.left);

        int leftDiff = (Math.max(Math.abs(root.val-l[1]),Math.abs(root.val-l[0])));
        int rightDiff = (Math.max(Math.abs(root.val-r[0]),Math.abs(root.val-r[1])));

        ans = Math.max(Math.max(leftDiff>100000?0:leftDiff,rightDiff>100000?0:rightDiff),ans);
        int min = Math.min(Math.min(root.val, r[1]), l[1]);
        int max = Math.max(Math.max(root.val, r[0]), l[0]);
        return new int[]{max, min};
    }
 */