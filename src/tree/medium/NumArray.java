/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.


 */

package tree.medium;

/**
 * Created by poorvank on 06/08/17.
 */
public class NumArray {

    private class Node {
        private Node left;
        private Node right;
        private int sum;
        private int[] range;

        Node(Node left, Node right, int sum, int[] range) {
            this.left = left;
            this.right = right;
            this.sum = sum;
            this.range = range;
        }
    }

    Node root;
    int n;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        n = nums.length;
        if (n!=0) {
            root = constructTree(0,n-1,nums);
        }
    }

    private Node constructTree(int start,int end,int[] nums) {
        if(start==end) {
            return new Node(null,null,nums[start],new int[]{start,end});
        }
        int mid = (start)+(end-start)/2;
        Node left = constructTree(start,mid,nums);
        Node right = constructTree(mid+1,end,nums);
        return new Node(left,right,left.sum+right.sum,new int[]{left.range[0],right.range[1]});
    }

    public void update(int i, int val) {
        if(i<n && i>=0) {
            int diff = val-nums[i];
            nums[i]=val;
            updateIndex(diff,i,root);
        }
    }

    private void updateIndex(int diff,int index,Node root) {
        if(root==null || index<root.range[0] || index>root.range[1]) {
            return;
        }
        root.sum+=diff;
        updateIndex(diff,index,root.left);
        updateIndex(diff,index,root.right);
    }

    public int sumRange(int i, int j) {
        return sumUtil(i,j,root);
    }

    private int sumUtil(int i,int j,Node root) {
        if(root==null || i>root.range[1] || j<root.range[0]) {
            return 0;
        }else if(i<=root.range[0] && root.range[1]<=j) {
            return root.sum;
        } else {
            return sumUtil(i,j,root.left)+ sumUtil(i,j,root.right);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }

}

/*

SEGMENT TREE

 */