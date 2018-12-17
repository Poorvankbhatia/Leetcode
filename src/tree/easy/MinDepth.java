/*

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 */
package tree.easy;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 13/09/16.
 */
public class MinDepth {

    public int minDepth(TreeNode root) {

        if(root==null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int dis=1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                TreeNode pop = queue.poll();
                if(pop.right==null && pop.left==null) {
                    return dis;
                }
                if(pop.left!=null) {
                    queue.offer(pop.left);
                }
                if(pop.right!=null) {
                    queue.offer(pop.right);
                }
            }
            dis++;
        }

        return dis;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        System.out.println(new MinDepth().minDepth(root));
    }

}

/*

Another Sol:

public int minDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

    }

 */