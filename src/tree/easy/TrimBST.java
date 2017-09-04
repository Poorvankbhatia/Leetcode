/*

Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L).
You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank.b on 04/09/17.
 */
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int L, int R) {

        if(root==null) {
            return null;
        }

        if(root.left==null && root.right==null) {
            if(root.val<L || root.val>R) {
                return null;
            }
        }

        if(root.val<L) {
            root =  trimBST(root.right,L,R);
        } else if(root.val>R) {
            root =  trimBST(root.left,L,R);
        } else {
            root.left = trimBST(root.left,L,R);
            root.right = trimBST(root.right,L,R);
        }

        return root;

    }


}


