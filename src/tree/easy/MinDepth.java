/*

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 13/09/16.
 */
public class MinDepth {

    private int minLevel = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {

        if(root==null) {
            return 0;
        }

        minDepthUtil(root,1);
        return minLevel;

    }

    private void minDepthUtil(TreeNode root,int level) {

        if(root==null) {
            return;
        }

        if(root.right==null && root.left==null) {
            if(minLevel>level) {
                minLevel = level;
            }
            return;
        }

        minDepthUtil(root.right,level+1);
        minDepthUtil(root.left,level+1);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        System.out.println(new MinDepth().minDepth(root));
    }

}
