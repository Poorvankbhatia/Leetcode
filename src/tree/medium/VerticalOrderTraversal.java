/*

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 */
package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * Created by poorvank.b on 07/02/18.
 */
public class VerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if(root==null) {
            return result;
        }

        Map<Integer,List<Integer>> ColValMap = new HashMap<>();

        Queue<Integer> levelQueue = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();

        levelQueue.add(0);
        nodeQueue.add(root);

        int minLevel = Integer.MAX_VALUE;
        int maxLevel = Integer.MIN_VALUE;

        while (!levelQueue.isEmpty()) {
            int level = levelQueue.poll();
            TreeNode current = nodeQueue.poll();

           if(current!=null) {
               minLevel = Math.min(minLevel,level);
               maxLevel = Math.max(maxLevel,level);

               if(ColValMap.containsKey(level)) {
                   ColValMap.get(level).add(current.val);
               } else {
                   List<Integer> list = new ArrayList<>();
                   list.add(current.val);
                   ColValMap.put(level,list);
               }

               if(current.left!=null) {
                   levelQueue.add(level-1);
                   nodeQueue.add(current.left);
               }

               if(current.right!=null) {
                   levelQueue.add(level+1);
                   nodeQueue.add(current.right);
               }
           }

        }

        for(int i=minLevel; i<=maxLevel; i++){
            if(ColValMap.containsKey(i)){
                result.add(ColValMap.get(i));
            }
        }

        return result;


    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(4);
        System.out.println(new VerticalOrderTraversal().verticalOrder(root));
    }

}

/*

For each node, its left child's degree is -1 and is right child's degree is +1. We can do a level order traversal and save the degree information.



 */
