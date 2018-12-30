/*

Given a binary tree, we install cameras on the nodes of the tree.

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.

Input: [0,0,null,0,0]
Output: 1

Input: [0,0,null,0,null,0,null,null,0]
Output: 2

 */
package tree.hard;

import tree.TreeNode;

/**
 * Created by poorvank.b on 30/12/18.
 */
public class BinaryTreeCamera {

    private int sum;

    public int minCameraCover(TreeNode root) {
        sum=0;
        if(dfs(root)==0) { // if root is not monitored, we place an additional camera here
            return sum+1;
        }

        return sum;
    }

    private int dfs(TreeNode root) {

        if(root==null) {
            return 1;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        if(left==0 || right==0) { // if at least 1 child is not monitored, we need to place a camera at current node
            sum++;
            return 2;
        } else if(left==2 || right==2) { // if at least 1 child has camera, the current node if monitored.
            // Thus, we don't need to place a camera here
            return 1;
        } else { // if both children are monitored but have no camera,
            // we don't need to place a camera here. We place the camera at its parent node at the higher level.
            return 0;
        }

    }

}

/*

We put camera at as higher level (closer to root) as possible. We use post-order DFS here.
This would be O(n) time and O(h) space, where h is height of the tree.
The return value of DFS() has following meanings. 0: there is no camera at this node, and it's not monitored by
camera at either of its children, which means neither of child nodes has camera. 1: there is no camera at this node; however,
this node is monitored by at least 1 of its children, which means at least 1 of its children has camera. 2: there is a camera at this node.

 */