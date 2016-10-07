/*

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

 */
package tree.medium;

import tree.TreeNode;

import java.util.HashMap;

/**
 * Created by poorvank on 26/09/16.
 */
public class PoInTree {

    private int postIndex;

    private HashMap<Integer,Integer> posMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        postIndex = postorder.length-1;

        posMap= new HashMap<>();

        for (int i=0;i<inorder.length;i++) {
            posMap.put(inorder[i],i);
        }

        return buildTree(postorder,0,inorder.length-1);

    }

    private TreeNode buildTree(int[] postOrder,int inStart,int inEnd) {

        if(inStart>inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postIndex--]);
        int rootIndex = posMap.get(root.val);

        if(postIndex >= 0) {

            root.right = buildTree(postOrder,rootIndex+1,inEnd);
            root.left = buildTree(postOrder,inStart,rootIndex-1);

        }


        return root;
    }

    public static void main(String[] args) {
        int[] po = new int[]{2,1,3};
        int[] in = new int[]{2,3,1};
        System.out.println(new PoInTree().buildTree(in,po).right.val);
    }

}
