/*

Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

 */

package tree.medium;

import tree.TreeNode;

import java.util.HashMap;

/**
 * Created by poorvank on 26/09/16.
 */
public class PreInTree {

    private int preIndex;

    private HashMap<Integer,Integer> preMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        preIndex = 0;

        preMap = new HashMap<>();

        for (int i=0;i<inorder.length;i++) {
            preMap.put(inorder[i],i);
        }

        return buildTree(preorder,0,inorder.length-1);

    }


    private TreeNode buildTree(int[] preOrder,int inStart,int inEnd) {

        if(inStart>inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preIndex++]);
        int rootIndex = preMap.get(root.val);

        if(preIndex < preOrder.length) {

            root.left = buildTree(preOrder,inStart,rootIndex-1);
            root.right = buildTree(preOrder,rootIndex+1,inEnd);

        }


        return root;
    }

    public static void main(String[] args) {
        int[] pr = new int[]{2,4,8,6,9};
        int[] in = new int[]{8,4,6,2,9};
        System.out.println(new PreInTree().buildTree(pr,in).left.right.val);
    }

}
