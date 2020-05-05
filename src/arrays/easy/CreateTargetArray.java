/*
Given two arrays of integers nums and index. Your task is to create target array under the following rules:

Initially target array is empty.
From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
Repeat the previous step until there are no elements to read in nums and index.
Return the target array.

It is guaranteed that the insertion operations will be valid.



Example 1:

Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
Output: [0,4,1,3,2]
Explanation:
nums       index     target
0            0        [0]
1            1        [0,1]
2            2        [0,1,2]
3            2        [0,1,3,2]
4            1        [0,4,1,3,2]
Example 2:

Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
Output: [0,1,2,3,4]
Explanation:
nums       index     target
1            0        [1]
2            1        [1,2]
3            2        [1,2,3]
4            3        [1,2,3,4]
0            0        [0,1,2,3,4]
Example 3:

Input: nums = [1], index = [0]
Output: [1]


Constraints:

1 <= nums.length, index.length <= 100
nums.length == index.length
0 <= nums[i] <= 100
0 <= index[i] <= i
 */
package arrays.easy;

import java.util.LinkedList;
import java.util.List;

public class CreateTargetArray {

    public int[] createTargetArray(int[] nums, int[] index) {
        int n = index.length;
        List<Integer> list = new LinkedList<>();
        for (int i=0;i<nums.length;i++) {
            list.add(index[i],nums[i]);
        }
        int[] res = new int[n];
        for (int i=0;i<n;i++) {
            res[i]=list.get(i);
        }
        return res;
    }

}

/*

BETTER MERGE SORT METHOD:
For each index i, if there are j indices on the left, which is >= i, all those j needs to bump 1.
In other words, for each index i, if there are x indices on the right which are <= i, i needs to bump x.
e.g., nums = [0,1,2,3,4], index = [0,1,2,2,1].
Number of elements on the right which is <= the current element: [0, 1, 2, 1, 0],
Then add to the original index to get [0, 2, 4, 3, 1], which is the final index to set nums.

Finding number of elements on the right <= self, can be found in O(nlogn) using merge sort.

    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        int[] a = new int[n];
        for(int i = 0; i < n; ++i) {
            a[i] = i;
        }
        helper(a, 0, n, index, new int[n]);
        int[] result = new int[n];
        for(int i = 0; i < n; ++i) {
            result[index[i]] = nums[i];
        }
        return result;
    }

    static void helper(int[] a, int i, int j, int[] index, int[] tmp) {
        if (j - i <= 1) {
            return;
        }
        int k = (i + j) >>> 1;
        helper(a, i, k, index, tmp);
        helper(a, k, j, index, tmp);
        int x = i;
        int y = k;
        int z = 0;
        int count = 0;
        while(x < k && y < j) {
            while(y < j && index[a[y]] <= index[a[x]] + count) {
                ++count;
                tmp[z++] = a[y++];
            }
            index[a[x]] += count;
            tmp[z++] = a[x++];
        }
        while(x < k) {
            index[a[x]] += count;
            tmp[z++] = a[x++];
        }
        while(y < j) {
            tmp[z++] = a[y++];
        }
        for(int p = i, q = 0; p < j; ++p, ++q) {
            a[p] = tmp[q];
        }
    }

 */