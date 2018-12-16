/*

Given a binary tree, determine if it is a complete binary tree.

 */
package tree.easy;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank.b on 16/12/18.
 */
public class CheckCompleteness {

    public boolean isCompleteTree(TreeNode current) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(current);
        boolean flag = false;
        while(!queue.isEmpty()) {
            TreeNode root = queue.poll();
            if(root.left!=null) {
                if(flag) {
                    return false;
                }
                queue.add(root.left);
            } else {
                flag=true;
            }

            if(root.right!=null) {
                if(flag) {
                    return false;
                }
                queue.add(root.right);
            } else {
                flag=true;
            }

        }

        return true;

    }

}

/*

Recursive solution:

public boolean isCompleteTree(TreeNode root) {
        return isCompleteTree(root,0,depth(root));
    }
    private boolean isCompleteTree(TreeNode root, int i, int n) {
        if (root == null)  return true;
        else if (i >= n) return false;
        return isCompleteTree(root.left, 2 * i + 1, n) && isCompleteTree(root.right, 2 * i + 2, n);
    }
     private  int depth(TreeNode root) {
        if (root == null) return 0;
        return 1 + depth(root.left) + depth(root.right);
    }

 */