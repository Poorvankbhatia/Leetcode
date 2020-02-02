/*
Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.

Since the answer may be too large, return it modulo 10^9 + 7.

Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
Example 3:

Input: root = [2,3,9,10,7,8,6,5,4,11,1]
Output: 1025
Example 4:

Input: root = [1,1]
Output: 1


Constraints:

Each tree has at most 50000 nodes and at least 2 nodes.
Each node's value is between [1, 10000].
 */
package tree.medium;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class MaximumProductSplitBT {
    private int mod = ((int)Math.pow(10,9))+7;
    private Map<TreeNode,Long> subtreeSum;
    private long max;
    public int maxProduct(TreeNode root) {
        subtreeSum = new HashMap<>();
        fillSubTreeSum(root);
        max=0;
        util(root,subtreeSum.get(root));
        return (int) (max%mod);
    }

    private long util(TreeNode root,long totalSum) {
        if(root==null) {
            return 0;
        }
        long maxLeft = util(root.left,totalSum);
        long maxRight = util(root.right,totalSum);
        long leftSubTreeSum =root.left!=null?subtreeSum.get(root.left):0;
        long rightSubTreeSum=root.right!=null?subtreeSum.get(root.right):0;
        max = Math.max(max,Math.max(maxLeft,maxRight));
        max = Math.max(max,Math.max(((totalSum-leftSubTreeSum)*leftSubTreeSum),((totalSum-rightSubTreeSum)*rightSubTreeSum)));
        return max;
    }

    private long fillSubTreeSum(TreeNode root) {
        if(root==null) {
            return 0;
        }
        long left = fillSubTreeSum(root.left);
        long right = fillSubTreeSum(root.right);
        long sum = root.val+left+right;
        subtreeSum.put(root,sum);
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        node.right.right = new TreeNode(4);
        node.right.right.left = new TreeNode(5);
        node.right.right.right = new TreeNode(6);
        System.out.println(new MaximumProductSplitBT().maxProduct(node));
    }

}

/*

Another Sol:

int MOD = 1000000007;
public int maxProduct(TreeNode root) {
    List<Long> sums = new ArrayList<>();
    long total = helper(root, sums);
    long max = 0;
    for(long s : sums){
        long p = s * (total - s);
        max = Math.max(max, p);
    }
    return (int)(max % MOD);
}

public long helper(TreeNode node, List<Long> sums){
    if(node == null){
        return 0;
    }
    long left = helper(node.left, sums);
    long right = helper(node.right, sums);
    long sum = left + right + node.val;
    sums.add(sum);
    return sum;
}

 */