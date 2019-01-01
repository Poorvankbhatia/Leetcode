/*

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root
node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 02/11/16.
 */
public class DeleteNodeBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) {
            return null;
        }

        if(root.val<key) {
            root.right= deleteNode(root.right,key);
        } else if(root.val>key) {
            root.left= deleteNode(root.left,key);
        } else {
            if(root.left==null && root.right==null) {
                return null;
            }
            if(root.right==null) {
                return root.left;
            } else {
                TreeNode inorderSuccessor = getSuccessor(root.right);
                System.out.println(root.val+" "+inorderSuccessor.val);
                root.val = inorderSuccessor.val;
                root.right= deleteNode(root.right,inorderSuccessor.val);
            }

        }

        return root;
    }


    private TreeNode getSuccessor(TreeNode current) {

        while(current.left!=null) {
            current=current.left;
        }

        return current;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(new DeleteNodeBST().deleteNode(root,3).val);


    }

}