/*
Given a binary tree root, a ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right then move to the right child of the current node otherwise move to the left child.
Change the direction from right to left or right to left.
Repeat the second and third step until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
Example 3:

Input: root = [1]
Output: 0


Constraints:

Each tree has at most 50000 nodes..
Each node's value is between [1, 100].
 */
package tree.medium;

import tree.TreeNode;

public class ZigZagPathLongest {

    static int max = 0;
    public static void dfs(TreeNode root, int prev, int curr) {
        max = Math.max(max, curr);
        if(root == null) return;
        if(prev == 1) {
            dfs(root.left, -1, curr + 1);
            dfs(root.right, 1, 0);
        } else if(prev == 0) {
            dfs(root.left, -1, 0);
            dfs(root.right, 1, 0);
        } else {
            dfs(root.left, -1, 0);
            dfs(root.right, 1, curr + 1);
        }
    }
    public int longestZigZag(TreeNode root) {
        max = 0;
        dfs(root, 0, 0);
        return max;
    }

}
