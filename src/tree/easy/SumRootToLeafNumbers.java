/*

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 16/09/16.
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {

        return sumNumbersUtil(0,root);

    }

    private int sumNumbersUtil(int val,TreeNode root) {

        if(root==null) {
            return 0;
        }

        val = (val*10) + root.val;
        if(root.right==null && root.left==null) {
            //System.out.println(val);
            return val;
        }

        return sumNumbersUtil(val,root.left) +sumNumbersUtil(val,root.right);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(9);
        System.out.println(new SumRootToLeafNumbers().sumNumbers(root));
    }

}

/*

Another sol:

class Solution {

    private int sum=0;

    public int sumNumbers(TreeNode root) {
        util(root,0);
        return sum;
    }

    private void util(TreeNode root,int val) {

        if(root!=null) {
            if(root.left==null && root.right==null) {
                sum+=(val*10+root.val);
                return;
            }

            util(root.left,val*10+root.val);
            util(root.right,val*10+root.val);
        }

    }

}

 */