/*
Given a binary search tree, return a balanced binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The tree nodes will have distinct values between 1 and 10^5.
 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalanceBST {

    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root,list);
        return balance(0,list.size()-1,list);
    }

    private TreeNode balance(int start,int end,List<TreeNode> list) {
        if(start>end) {
            return null;
        }
        int mid = start+(end-start)/2;
        TreeNode root = list.get(mid);
        root.left = balance(start,mid-1,list);
        root.right = balance(mid+1,end,list);
        return root;
    }

    private void inorder(TreeNode root, List<TreeNode> list) {
        if(root!=null) {
            inorder(root.left,list);
            list.add(root);
            inorder(root.right,list);
        }
    }

}

/*

Traverse given BST in inorder and store result in an array.
This step takes O(n) time.

Build a balanced BST from the above created sorted array .
 This step also takes O(n) time as we traverse every element exactly once and processing an element takes O(1) time.

 */