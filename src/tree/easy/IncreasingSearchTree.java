/*

Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
 and every node has no left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9
Note:

The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 02/09/18.
 */
public class IncreasingSearchTree {

    private TreeNode prev=null, head=null;
    public TreeNode increasingBST(TreeNode root) {
        if(root!=null) {
            increasingBST(root.left);
            if(prev!=null) {
                root.left=null;
                prev.right=root;
            }
            if(head==null) {
                head=root;
            }
            prev=root;
            increasingBST(root.right);
            return head;
        }
        return null;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(2);
        treeNode.left.left=new TreeNode(1);
        treeNode.right=new TreeNode(6);
        treeNode.right.left=new TreeNode(5);
        treeNode.right.left.left=new TreeNode(4);
        System.out.println(new IncreasingSearchTree().increasingBST(treeNode).val);
    }

}
