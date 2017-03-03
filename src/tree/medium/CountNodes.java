/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.


 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 15/09/16.
 */
public class CountNodes {

    public int countNodes(TreeNode root) {

        if(root==null) {
            return 0;
        }

        if(root.left==null && root.right==null) {
            return 1;
        }

        int lHeight = getLeftHeight(root.left);
        int rHeight = getRightHeight(root.right);


        if(lHeight==rHeight) {
            int treeHeight = 1+ lHeight;
            /*
            Left bit shifting to multiply by any power of two and Right bit shifting to divide by any power of two.
            For example x = x * 2; can also be written as x<<1 or x = x*8 can be written as x<<3 (since 2 to the power of 3 is 8).
            Similarly x = x / 2; is x>>1 and so on.
             */
            return (2<<(treeHeight-1))-1;
        } else {
            return (1+countNodes(root.left) + countNodes(root.right));
        }
    }

    private int getLeftHeight(TreeNode root) {

        if(root==null){
            return 0;
        }
        int c = 0;

        while (root!=null) {
            c++;
            root = root.left;
        }

        return c;
    }

    private int getRightHeight(TreeNode root) {

        if(root==null){
            return 0;
        }
        int c = 0;

        while (root!=null) {
            c++;
            root = root.right;
        }

        return c;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        System.out.println(new CountNodes().countNodes(root));
    }

}

/*

In a complete binary tree of height 'h' there can be [2^(h-1) to  2^(h) - 1] nodes.
 A complete binary tree can have 2^h minimum number nodes or 2^(h-1)-1 maximum number of nodes. (where tree with 1 node has height 1)

If the height of the leftmost tree & right most tree is same. height is (2)^h -1;
Else
it is normal count.

 */