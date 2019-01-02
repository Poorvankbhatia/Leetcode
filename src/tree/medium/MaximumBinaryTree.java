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
        LinkedList<TreeNode> list = new LinkedList<>();
        for(int i=0;i<nums.length;i++) {

            TreeNode current  = new TreeNode(nums[i]);
            while(!list.isEmpty() && nums[i]>list.peekFirst().val) {
                current.left=list.removeFirst();
            }
            if(!list.isEmpty()) {
                list.peek().right=current;
            }

            list.addFirst(current);

        }

        return list.isEmpty()?null:list.peekLast();
    }
}


If we have built the max binary tree for nums[0] ~ nums[i - 1], how can we insert nums[i] to the binary tree?
Say the max binary tree for nums[0] ~ nums[i - 1] looks like:

      A
     / \
  ...   B
       / \
    ...   C
         / \
      ...   ...
Say the node for nums[i] is D.
If D.val > A.val, then because A.val is at the left of D.val, we can just move the tree rooted at A to the left child of D.

        D
       /
      A
     / \
  ...   B
       / \
    ...   C
         / \
      ...   ...
If D.val < A.val, then because D.val is at the right of A.val, D must be put into the right subtree of A.
Similarly, if D.val < B.val, then D must be put into the right subtree of B.
Say B.val > D.val > C.val, then D should be the right child of B. (because D.val is at the right of B.val, and D.val is the biggest among the numbers at the right of B.val.)
Because C.val < D.val, and C.val is at the left of D.val, C should become left child of D.

      A
     / \
  ...   B
       / \
     ...  D
         /
        C
       / \
    ...   ...
So to update the max binary tree for nums[0] ~ nums[i - 1], we need to know the nodes on the right path of the tree. (A, B, C, ...)
How to maintain the path?
Let's look at the property of the nodes.
A is the biggest among nums[0] ~ nums[i - 1].
B is the biggest for the numbers between A and nums[i] (exclusive).
C is the biggest for the numbers between B and nums[i] (exclusive).
Let's use a stack, and assume that the content of the stack contains the "right path" of nodes before the node for the current number.
For the node of the new number, we should remove the nodes in the stack which are smaller than the current number.
So we pop the stack until the top element of the stack is greater than the current number.
Then, add the node for the current number to the stack.

 */