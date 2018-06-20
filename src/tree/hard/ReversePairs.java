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

import java.util.Arrays;

/**
 * Created by poorvank.b on 02/12/17.
 */
public class ReversePairs {

    private static class Node {
        Node left, right;
        long val;
        int cnt;

        Node(long val) {
            this.val = val;
        }

        void add(long n) {
            if(n <= val) {
                cnt++;
            }
            if(n > val) {
                right.add(n);
            }
            if(n < val) {
                left.add(n);
            }
        }

        int count(long n) {
            return n > val ? cnt + (right == null ? 0 : right.count(n))
                    : (left == null ? 0 : left.count(n));
        }
    }

    public int reversePairs(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;

        long[] doubles = new long[n];
        for(int i = 0; i < n; i++)
            doubles[i] = 2L * nums[i];
        Arrays.sort(doubles);
        int j = 0;
        for(int i = 1; i < n; i++)
            if(doubles[j] != doubles[i])
                doubles[++j] = doubles[i];

        Node root = build(doubles, 0, j);

        int res = 0;
        for(int i = n - 1; i >= 0; i--) {
            res += root.count(nums[i]);
            root.add(2L * nums[i]);
        }

        return res;
    }

    private Node build(long[] nums, int st, int en) {
        if(st > en)
            return null;

        int mi = st + (en - st) / 2;
        Node n = new Node(nums[mi]);
        n.left = build(nums, st, mi - 1);
        n.right = build(nums, mi + 1, en);
        return n;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        System.out.println(new ReversePairs().reversePairs(arr));
    }

}
