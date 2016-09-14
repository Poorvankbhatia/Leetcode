/*

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

 */

package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 12/09/16.
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> result = new ArrayList<>();
        fillList(root,new ArrayList<Integer>(),result);
        return result;

    }

    private void fillList(TreeNode root,List<Integer> paths,List<String> result) {

        if(root==null) {
            return;
        }

        paths.add(root.val);

        if(root.left==null && root.right==null) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : paths) {
                sb.append(i).append("->");
            }
            result.add(sb.substring(0,sb.length()-2).toString());
        }

        fillList(root.left,new ArrayList<>(paths),result);
        fillList(root.right,new ArrayList<>(paths),result);

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        BinaryTreePaths bp = new BinaryTreePaths();
        System.out.println(bp.binaryTreePaths(root));


    }



}
