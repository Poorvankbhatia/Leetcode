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

    private class SegmentTree {

        private int inputSize;
        public int[] tree;
        public int[] inputArray;

        public SegmentTree(int[] nums) {
            inputSize = nums.length;
            inputArray = nums;
            int size = (int) Math.ceil(Math.log(inputSize)/Math.log(2));
            int maxSize = 2*((int) Math.pow(2,size))-1;
            if(maxSize<=0) {
                return;
            }
            tree = new int[maxSize];
            constructTreeUtil(nums,0,inputSize-1,0);
        }


        private int getMid(int low,int high) {
            return low+(high-low)/2;
        }

        private int constructTreeUtil(int[] arr,int start,int end,int pos) {
            if(start==end) {
                tree[pos] = arr[start];
                return tree[pos];
            }

            int mid = getMid(start,end);
            tree[pos] = constructTreeUtil(arr,start,mid,(2*pos+1)) + constructTreeUtil(arr,mid+1,end,(2*pos+2));
            return tree[pos];
        }

        public int getSum(int qStart,int qEnd) {
            if(qStart<0 && qEnd>=inputSize) {
                System.out.println("Invalid input");
                return -1;
            }
            return getSumUtil(0,inputSize-1,qStart,qEnd,0);
        }

        private int getSumUtil(int start,int end,int qStart,int qEnd,int pos) {
            if(qEnd<start || qStart>end) {
                return 0;
            }

            if(qEnd>=end && qStart<=start) {
                return tree[pos];
            }

            int mid = getMid(start,end);
            return getSumUtil(start,mid,qStart,qEnd,(2*pos)+1) + getSumUtil(mid+1,end,qStart,qEnd,(2*pos)+2);

        }

        public void updateValue(int index,int val) {

            int diff = val-inputArray[index];
            inputArray[index] = val;

            updateValueUtil(0,diff,0,inputSize-1,index);

        }

        private void updateValueUtil(int pos,int diff,int start,int end,int indexUpdated) {

            if(indexUpdated<start || indexUpdated>end) {
                return;
            }

            tree[pos] += diff;
            if(start!=end) {
                int mid = getMid(start,end);
                updateValueUtil((2*pos+1),diff,start,mid,indexUpdated);
                updateValueUtil((2*pos+2),diff,mid+1,end,indexUpdated);
            }

        }

    }

    private SegmentTree segmentTree;

    public NumArray(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }

    public void update(int i, int val) {
        segmentTree.updateValue(i,val);
    }

    public int sumRange(int i, int j) {
        return segmentTree.getSum(i,j);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }

}
