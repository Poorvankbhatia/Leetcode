/*

Given a binary tree, return the bottom-up level order traversal of its nodes' values.
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

 */

package tree;

import java.util.*;

/**
 * Created by poorvank on 14/09/16.
 */
public class LevelOrder2 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();

        if(root==null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Stack<TreeNode> stack = new Stack<>();
        queue.add(null);

        while (!queue.isEmpty()) {

            TreeNode current = queue.remove();
            stack.push(current);
            if(current==null) {
                if(!queue.isEmpty()) {
                    queue.add(null);
                }
                continue;
            }

            if(current.right!=null) {
                queue.add(current.right);
            }

            if(current.left!=null) {
                queue.add(current.left);
            }

        }

        List<Integer> intermediateList = new ArrayList<>();

        while (!stack.empty()) {

            TreeNode current = stack.pop();
            if (current==null) {
                if(intermediateList.size()!=0) {
                    lists.add(intermediateList);
                    intermediateList = new ArrayList<>();
                }
                continue;
            }
            intermediateList.add(current.val);

        }

        lists.add(intermediateList);

        return lists;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new LevelOrder2().levelOrderBottom(root));
    }


}
