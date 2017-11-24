/*

Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.

 */

package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 14/02/17.
 */
public class  BottomLeftTree {

    private int leftMostValue =0,max=0;

    public int findBottomLeftValue(TreeNode root) {

        findBottomLeftUtil(root,1);
        return leftMostValue;

    }

    private void findBottomLeftUtil(TreeNode root,int level) {

        if(root==null) {
            return;
        }

        /*
         Same logic as printing left view of a tree
         */
        if(level>max) {
            leftMostValue = root.val;
            max = level;
        }

        findBottomLeftUtil(root.left,level+1);
        findBottomLeftUtil(root.right,level+1);

    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        //node.left.right = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(5);
        node.right.right = new TreeNode(6);
        node.right.left.left = new TreeNode(7);

        System.out.print(new BottomLeftTree().findBottomLeftValue(node));
    }

}


/*

Queue also works:

public int findLeftMostNode(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int interval = 0; interval < size; interval++) {
                TreeNode node = queue.poll();
                if (interval == 0) result = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return result;
    }

 */