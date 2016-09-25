/*

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 16/09/16.
 */
public class ArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {

        return toBSTUtil(0,nums.length-1,nums);
    }

    private TreeNode toBSTUtil(int start,int end,int[] nums) {

        if(start>end) {
            return null;
        }

        int mid = (start+end)/2;

        TreeNode root = new TreeNode(nums[mid]);

        root.left = toBSTUtil(start,mid-1,nums);
        root.right = toBSTUtil(mid+1,end,nums);

        return root;

    }

}
