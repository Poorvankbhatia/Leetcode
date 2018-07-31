/*

Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree.
An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree
in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work.
You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original
N-nary tree structure.



 */
package tree.hard;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by poorvank.b on 01/03/18.
 */
public class EncodeNArrayTreeToBT {


    /*
    The strategy follows two rules:
    1. The left child of each TreeNode in the binary tree is the next sibling of the TreeNode in the N-ary tree.
    2. The right child of each TreeNode in the binary tree is the first child of the TreeNode in the N-ary tree.
     */

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode result = new TreeNode(root.val);
        if (root.children.size() > 0) {
            result.left = encode(root.children.get(0));
        }
        TreeNode cur = result.left;
        for (int i = 1; i < root.children.size(); i++) {
            cur.right = encode(root.children.get(i));
            cur = cur.right;
        }
        return result;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node result = new Node(root.val, new LinkedList<>());
        TreeNode cur = root.left;
        while (cur != null) {
            result.children.add(decode(cur));
            cur = cur.right;
        }
        return result;
    }

}

/*

https://leetcode.com/articles/introduction-to-n-ary-trees/

convert a N-ary tree to a binary tree recursively:

1. Deal with its children recursively.
2. Add its left child as the next child of its parent if it has a left child.
3. Add its right child as the first child of the TreeNode itself if it has a right child.

Next Level -> left, Same Level -> right

  1
 / | \
2  3  4

to:

 1
/
2
 \
  3
   \
    4

 */