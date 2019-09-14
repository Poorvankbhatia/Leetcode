/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FindLeavesBinaryTree {

    Map<Integer,List<Integer>> map;
    public List<List<Integer>> findLeaves(TreeNode root) {
        map = new TreeMap<>(); //group leaves of same level together.
        util(root);
        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private int util(TreeNode root) {

        if(root == null) {
            return 0;
        }
        int left = util(root.left);
        int right = util(root.right);
        int sum = 1+Math.max(left,right);
        map.putIfAbsent(sum,new ArrayList<>());
        map.get(sum).add(root.val);
        return sum;
    }

}

/*

Without hashing:

public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }
    private int height(TreeNode node, List<List<Integer>> res){
        if(null==node)  return -1;
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if(res.size()<level+1)  res.add(new ArrayList<>());
        res.get(level).add(node.val);
        return level;
    }

 */