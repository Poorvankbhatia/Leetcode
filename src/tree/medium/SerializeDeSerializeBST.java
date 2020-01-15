/*

Serialization is the process of converting a data structure or object into a
sequence of bits so that it can be stored in a file or memory buffer, or transmitted across
 a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on
how your serialization/deserialization algorithm should work. You just need to ensure that a binary
search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

 */

package tree.medium;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by poorvank.b on 03/11/16.
 */
public class SerializeDeSerializeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode popVal = stack.pop();
            result.append(popVal.val).append("_");
            if(popVal.right!=null) {
                stack.push(popVal.right);
            }
            if(popVal.left!=null) {
                stack.push(popVal.left);
            }
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) {
            return null;
        }
        String[] arr = data.split("_");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        stack.push(root);
        for (int i=1;i<arr.length;i++) {
            TreeNode temp=null;
            while (!stack.isEmpty() && Integer.parseInt(arr[i])>stack.peek().val) {
                temp = stack.pop();
            }
            if(temp!=null) {
                temp.right = new TreeNode(Integer.parseInt(arr[i]));
                stack.push(temp.right);
            } else {
                temp = stack.peek();
                temp.left = new TreeNode(Integer.parseInt(arr[i]));
                stack.push(temp.left);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);
        n.left = new TreeNode(2);
        n.right = new TreeNode(4);
        String ser = new SerializeDeSerializeBST().serialize(n);
        System.out.print(ser+"\n");
        TreeNode node =new SerializeDeSerializeBST().deserialize(ser);
        System.out.println(node.val + " " + node.left.val + " " + node.right.val);

    }

}
