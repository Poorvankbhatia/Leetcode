/*

Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank.b on 20/01/19.
 */
public class DistributeCoins {

    private int moves;
    public int distributeCoins(TreeNode root) {
        moves=0;
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int res = left+right;
        moves+=Math.abs(left)+Math.abs(right);
        res+=root.val-1;
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left=new TreeNode(0);
        root.right=new TreeNode(0);
        System.out.println(new DistributeCoins().distributeCoins(root));
    }
}
/*

a node having excess coins or deficit of coins requires that many moves of coins from the connected node
so the deficit or excess is to be added to the no of moves the excess or deficit is moved to or from the parent

No global variable:

public int distributeCoins(TreeNode root) {
        int res = 0;
        if (root.left != null) {
            res += distributeCoins(root.left);
            root.val += root.left.val - 1;
            res += Math.abs(root.left.val - 1);
        }
        if (root.right != null) {
            res += distributeCoins(root.right);
            root.val += root.right.val - 1;
            res += Math.abs(root.right.val - 1);
        }
        return res;
    }

 */