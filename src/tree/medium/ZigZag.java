/*

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then
right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank on 17/09/16.
 */
public class ZigZag {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        return zigzagUtil(root,new ArrayList<>());

    }

    private List<List<Integer>> zigzagUtil(TreeNode root,List<List<Integer>> lists) {

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        if(root==null) {
            return lists;
        }

        s1.add(root);

        while (!s1.empty() || !s2.empty()) {

            List<Integer> list = new ArrayList<>();

            while (!s1.empty()) {
                TreeNode current = s1.pop();

                list.add(current.val);

                if(current.left!=null) {
                    s2.add(current.left);
                }

                if(current.right!=null) {
                    s2.add(current.right);
                }

            }

            if(list.size()!=0) {
                lists.add(list);
            }

            list = new ArrayList<>();

            while (!s2.empty()) {

                TreeNode current = s2.pop();

                list.add(current.val);

                if(current.right!=null) {
                    s1.add(current.right);
                }

                if(current.left!=null) {
                    s1.add(current.left);
                }

            }

            if(list.size()!=0) {
                lists.add(list);
            }


        }

        return lists;

    }


}
