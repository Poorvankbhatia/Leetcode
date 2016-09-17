/*

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]


 */
package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 16/09/16.
 */
public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> list = new ArrayList<>();
        pathSumUtil(root,sum,new ArrayList<>(),list);
        return list;

    }


    private void pathSumUtil(TreeNode root,int sum,List<Integer> list,List<List<Integer>> lists) {

        if(root==null) {
            return;
        }

        sum -= root.val;
        list.add(root.val);
        if(root.left==null && root.right==null) {
            if(sum==0) {
                lists.add(new ArrayList<>(list));
            }
            return;
        }

        pathSumUtil(root.left,sum,new ArrayList<>(list),lists);
        pathSumUtil(root.right,sum,new ArrayList<>(list),lists);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(new PathSum2().pathSum(root,22));
    }

}
