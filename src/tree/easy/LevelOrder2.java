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

package tree.easy;

import tree.TreeNode;

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
        Stack<List<Integer>> stack = new Stack<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0;i<size;i++) {
                TreeNode pop = queue.poll();
                list.add(pop.val);
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if(pop.right!=null) {
                    queue.add(pop.right);
                }
            }
            stack.add(list);
        }

        while (!stack.isEmpty()) {
            lists.add(stack.pop());
        }

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

/*

Using only 1 queue:

public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }
}

 */