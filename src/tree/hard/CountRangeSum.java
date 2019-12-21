/*

Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.

 */
package tree.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountRangeSum {

    class SegmentTreeNode {
        SegmentTreeNode left;
        SegmentTreeNode right;
        int count;
        long[] range;
        SegmentTreeNode(long[] range) {
            this.range=range;
        }
    }
    private SegmentTreeNode buildSegmentTree(Long[] valArr, int low, int high) {
        if(low > high) return null;
        SegmentTreeNode node = new SegmentTreeNode(new long[]{valArr[low], valArr[high]});
        if(low == high) {
            return node;
        }
        int mid = low+(high-low)/2;
        node.left = buildSegmentTree(valArr, low, mid);
        node.right = buildSegmentTree(valArr, mid+1, high);
        return node;
    }
    private void updateSegmentTree(SegmentTreeNode node, Long val) {
        if(node == null) return;
        if(val >= node.range[0] && val <= node.range[1]) {
            node.count++;
            updateSegmentTree(node.left, val);
            updateSegmentTree(node.right, val);
        }
    }
    private int getCount(SegmentTreeNode node, long min, long max) {
        if(node == null) return 0;
        if(min > node.range[1] || max < node.range[0]) {
            return 0;
        }
        if(min <= node.range[0] && max >= node.range[1]) {
            return node.count;
        }
        return getCount(node.left, min, max) + getCount(node.right, min, max);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums == null || nums.length == 0) return 0;
        int ans = 0;
        Set<Long> set = new HashSet<>();
        long sum = 0;
        /**
         * Because in this method, what really matters is the range of sum. So duplicates has no use at all.
         */
        for (int num : nums) {
            sum += (long) num;
            set.add(sum);
        }
        Long[] valArr = set.toArray(new Long[0]);
        Arrays.sort(valArr);
        SegmentTreeNode root = buildSegmentTree(valArr, 0, valArr.length-1);
        /*
         we can see "sum" is the sum of total numbers, then we can reduce nums[i] from sum one by one,
         to get all different sum values, and update the segment tree (initially the segment tree's counter is 0).
         During the process, each time we get an updated sum, we can create the range [lower+sum, upper+sum] and lookup in the segment tree
         */
        for(int i = nums.length-1; i >=0; i--) {
            updateSegmentTree(root, sum);
            sum -= (long) nums[i];
            ans += getCount(root, (long)lower+sum, (long)upper+sum);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{0,-3,-3,1,1,2};
        System.out.println(new CountRangeSum().countRangeSum(arr,3,5));
    }

}


/*

we have the formula:
lower<= sums[i] - sums[j] <= upper (sums[k] is the sum of nums[0...k]), which is
lower+sums[j] <= sums[i] <= upper+sums[j],
so for each sum[j],
we want to find out the # of all sums[i] which satisfy the condition -----
"give a range, and find the # of values in the range, which is what segment tree does"

Since we are looking for different "sum", not the number itself, so we first need to get all sums from nums.



 */