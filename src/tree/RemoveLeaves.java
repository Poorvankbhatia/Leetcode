/*

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree

          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].



 */
package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 15/09/16.
 */
public class RemoveLeaves {

    public List<List<Integer>> removeLeaves(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();

        removeLeavesUtil(root,lists);
        return lists;

    }

    private int removeLeavesUtil(TreeNode root,List<List<Integer>> lists) {

        if(root==null) {
            return -1;
        }

        int left = removeLeavesUtil(root.left,lists);
        int right = removeLeavesUtil(root.right,lists);
        int levelRemoved = 1 + Math.max(left,right);

        if(levelRemoved>=lists.size()) {
            lists.add(new ArrayList<>());
        }


        lists.get(levelRemoved).add(root.val);

        return levelRemoved;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left = new TreeNode(5);

        System.out.println(new RemoveLeaves().removeLeaves(root));
    }

}


