/*

Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1


 */

package tree;

/**
 * Created by poorvank.b on 12/09/16.
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {

        if(root==null) {
            return null;
        }

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        new InvertBinaryTree().invertTree(root);
        System.out.println(root.right.left.val);


    }

}
