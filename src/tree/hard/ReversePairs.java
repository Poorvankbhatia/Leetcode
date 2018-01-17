/*

Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

 */
package tree.hard;

/**
 * Created by poorvank.b on 02/12/17.
 */
public class ReversePairs {

    private class Node {

        private Node left;
        private Node right;
        private int value;
        private int greaterCount;

    }

    private Node root;

    private Node addToBST(Integer x,Node root) {
        if(root==null) {
            root = new Node();
            root.value=x;
            root.greaterCount=1;
        } else if(root.value>x) {
            root.left = addToBST(x,root.left);
        } else {
            root.greaterCount++;
            root.right = addToBST(x,root.right);
        }
        return root;
    }

    private int searchCount(long x,Node root) {
        if(root==null) {
            return 0;
        }
        if(x==root.value) {
            return root.greaterCount;
        } else if(x<root.value) {
            return root.greaterCount+searchCount(x,root.left);
        } else {
            return searchCount(x,root.right);
        }
    }

    public int reversePairs(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        int ans=0;
        root=null;

        for (int num : nums) {
            Long value = (num*2L+1);
            ans+=searchCount(value,root);
            root = addToBST(num,root);
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        System.out.println(new ReversePairs().reversePairs(arr));
    }

}
