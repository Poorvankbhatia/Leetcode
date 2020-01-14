/*

You are given an integer array nums and you have to return a new counts array. The counts array has the property where
 counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

 */

package tree.hard;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by poorvank on 29/05/17.
 */
public class CountNoSmaller {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min,n);
            max = Math.max(max,n);
        }
        SegmentNode segmentNode = new SegmentNode(min,max);
        for (int j = nums.length-1; j >=0; j--) {
            add(segmentNode, nums[j]);
            res.add(0,count(nums[j],segmentNode));
        }
        return res;
    }


    private class SegmentNode {
        private int min, max;
        private int count;
        private SegmentNode left, right;

        public SegmentNode(int min, int max) {
            this.min = min;
            this.max = max;
            count = 0;
            left = right = null;
        }
    }

    public void add(SegmentNode node,int n) {
        node.count++;
        if (node.min==node.max)return;
        int mid = node.min+(node.max-node.min)/2;
        if (n <= mid) {
            if (node.left ==null) {
                node.left = new SegmentNode(node.min, mid);
            }
            add(node.left,n);
        } else {
            if (node.right ==null) {
                node.right = new SegmentNode(mid+1,node.max);
            }
            add(node.right,n);
        }
    }

    public int count(long n,SegmentNode node) {
        if (node.min>=n) return 0;
        if (node.max<n) return node.count;
        return
                (node.left ==null?0:count(n,node.left))+
                        (node.right ==null?0:count(n,node.right));
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3,2,2,6,1};
        System.out.println(new CountNoSmaller().countSmaller(arr));
    }

}

/*

We Use a SEGMENT TREE, to query how many elements in tree have a value smaller than a number.

Merge Sort Solution:

public List<Integer> countSmaller(int[] nums) {
        int[] res = new int[nums.length];
        int[] index = new int[res.length];
        for (int i = 0; i < res.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, index, 0, nums.length-1, res);
        List<Integer> list = new LinkedList<>();
        for (int i : res) {
            list.add(i);
        }
        return list;
    }

    private void mergeSort(int[] nums, int[] index, int l, int r, int[] res) {
        if (l >= r) {
            return;
        }
        int mid = (l+r)/2;
        mergeSort(nums, index, l, mid, res);
        mergeSort(nums, index, mid+1, r, res);
        merge(nums, index, l, mid, mid+1, r, res);
    }

    private void merge(int[] nums, int[] index, int l1, int r1, int l2, int r2, int[] res) {
        int start = l1;
        int[] tmp = new int[r2-l1+1];
        int count = 0;
        int p = 0;
        while (l1 <= r1 || l2 <= r2) {
            if (l1 > r1) {
                tmp[p++] = index[l2++];
            } else if (l2 > r2) {
                res[index[l1]] += count;
                tmp[p++] = index[l1++];
            } else if (nums[index[l1]] > nums[index[l2]]) {
                tmp[p++] = index[l2++];
                count++;
            } else {
                res[index[l1]] += count;
                tmp[p++] = index[l1++];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            index[start+i] = tmp[i];
        }
    }

 The basic idea is to do merge sort to nums[]. To record the result, we need to keep the index of each number in the original array.
 So instead of sort the number in nums, we sort the indexes of each number.
Example: nums = [5,2,6,1], indexes = [0,1,2,3]
After sort: indexes = [3,1,0,2]

While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.

We keep a rightcount to record how many numbers from right[] we have added and keep an array count[] to record the result.

When we move a number from right[] into the new sorted array, we increase rightcount by 1.

When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rightcount.

rightcount . This variable denotes the number of elements in the right sorted part that are smaller than the next element from the left sorted part.
Why are they smaller than the next element from the left sorted part? Because they come before (the next element from the left sorted part) in the merged array.
That how this sentence follows : When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rightcount.

Or another version: We keep the rightcount for the next number, because this next number could come from the left sorted array, and when this is the case,
we know previously there were rightcount number of elements that are smaller than it.

 */