/*

Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

 */
package tree.easy;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 16/09/16.
 */
public class Preorder {
    public List<Integer> preorderTraversal(TreeNode root) {
        return p(root,new ArrayList<>());
    }

    private List<Integer> p(TreeNode root,List<Integer> list) {
        if(root==null) {
            return list;
        }
        list = p(root.left,list);
        list = p(root.right,list);
        list.add(root.val);
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new Preorder().preorderTraversal(root));
    }
}
