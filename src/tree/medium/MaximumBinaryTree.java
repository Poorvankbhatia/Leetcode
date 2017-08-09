/*

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1
Note:
The size of the given array will be in the range [1,1000].

 */
package tree.medium;

import tree.TreeNode;

/**
 * Created by poorvank on 07/08/17.
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if(nums==null || nums.length==0) {
            return null;
        }

        return util(nums,0,nums.length-1);

    }

    private TreeNode util(int[] nums,int start,int end) {

        if(start>end) {
            return null;
        }

        int maxIndex = 0,max=Integer.MIN_VALUE;
        for (int i=start;i<=end;i++) {
            if(nums[i]>max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxIndex]);
        root.right = util(nums,maxIndex+1,end);
        root.left = util(nums,start,maxIndex-1);
        return root;

    }

}

/*

O(n) solution:

We scan numbers from left to right, build the tree one node by one step;
We use a stack to keep some (not all) tree nodes and ensure a decreasing order;
For each number, we keep pop the stack until empty or a bigger number; The bigger number
(if exist, it will be still in stack) is current number's root, and the last popped number (if exist)
is current number's right child (temporarily, this relationship may change in the future); Then we push current number into the stack.

public TreeNode constructMaximumBinaryTree(int[] nums) {
        LinkedList<TreeNode> lklist = new LinkedList<>();
        TreeNode left = null;
        for (int num: nums){
            TreeNode cur = new TreeNode(num);
            while (!lklist.isEmpty() && lklist.peekFirst().val < cur.val){
                cur.left = lklist.pop();
            }

            if (!lklist.isEmpty()){
                lklist.peekFirst().right = cur;
            }
            lklist.push(cur);
        }

        return lklist.peekLast();
    }

 */