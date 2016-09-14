/*

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

 */

package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank.b on 13/09/16.
 */
public class LevelOrder {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result  = new ArrayList<>();

        if(root==null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        List<Integer> intermediateList = new ArrayList<>();
        queue.add(null);

        while (!queue.isEmpty()) {


            TreeNode current = queue.remove();
            //Reached end of a level
            if(current==null) {
                result.add(intermediateList);
                intermediateList = new ArrayList<>();
                if(!queue.isEmpty()) {
                    queue.add(null);
                }
                continue;
            }
            intermediateList.add(current.val);

            if(current.left!=null) {
                queue.add(current.left);
            }
            if(current.right!=null) {
                queue.add(current.right);
            }

        }

        return result;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(20);
        root.right.right = new TreeNode(7);
        System.out.println(new LevelOrder().levelOrder(root));
    }

}


/*

Can also be done using 2 queues

 */