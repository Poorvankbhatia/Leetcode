/*

Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

 */
package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * Created by poorvank on 30/07/17.
 */
public class DuplicateSubtrees {

    Map<String,Integer> map = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        findDuplicates(root,result);
        return result;
    }

    private String findDuplicates(TreeNode root,List<TreeNode> list) {

        if(root==null) {
            return "";
        }

        // To remove cases like "00" string we add l for left and r for right;
        String left = findDuplicates(root.left,list)+"l";
        String right = findDuplicates(root.right,list)+"r";

        String finalStr = left+root.val+right;

        if(map.containsKey(finalStr) && map.get(finalStr)==1) {
            list.add(root);
            map.put(finalStr,2);
        } else if(!map.containsKey(finalStr)) {
            map.put(finalStr,1);
        }

        return finalStr;
    }

    public static void main(String[] args){
        /*TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.right.left = new TreeNode(2);
        node.right.right = new TreeNode(4);
        node.right.left.left = new TreeNode(4);*/


        TreeNode node = new TreeNode(0);
        node.left = new TreeNode(0);
        node.right = new TreeNode(0);

        TreeNode left = new TreeNode(0);
        left.right = new TreeNode(0);
        left.left = new TreeNode(0);

        TreeNode right = new TreeNode(0);
        right.right = new TreeNode(0);
        right.left = new TreeNode(0);

        node.left.left = left;
        node.right.right = right;

        List<TreeNode> list = new DuplicateSubtrees().findDuplicateSubtrees(node);
        for (TreeNode node1 : list) {
            System.out.print(node1.val + " ");
        }
    }

}

/*
 G I
 */