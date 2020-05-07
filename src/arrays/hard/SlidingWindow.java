/*

Given an array nums, there is a sliding window of size k which is moving from the very left of the
array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 */
package arrays.hard;

import java.util.*;

/**
 * Created by poorvank on 02/01/17.
 */
public class SlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums==null || k>nums.length || nums.length==0) {
            return new int[]{};
        }

        int n = nums.length;

        List<Integer> list = new ArrayList<>();

        int i=0;

        Deque<Integer> integerDeque = new LinkedList<>();

        while (i<k) {

            while (!integerDeque.isEmpty() && nums[i]>nums[integerDeque.getLast()]) {
                integerDeque.removeLast();
            }

            integerDeque.add(i);

            i++;

        }

        if(i==n) {
            return new int[]{nums[integerDeque.getFirst()]};
        }

        while (i<n) {



            list.add(nums[integerDeque.getFirst()]);

            //Remove redundant elements
            while (!integerDeque.isEmpty() && integerDeque.getFirst()<=i-k) {
                integerDeque.removeFirst();
            }

            while (!integerDeque.isEmpty() && nums[i]>nums[integerDeque.getLast()]) {
                integerDeque.removeLast();
            }

            integerDeque.add(i);

            i++;

        }

        list.add(nums[integerDeque.getFirst()]);

        int[] result = new int[list.size()];
        for (i=0; i < result.length; i++)
        {
            result[i] = list.get(i);
        }
        return result;

    }


    public static void main(String[] args) {

        int[] arr = new int[]{1,3,1,2,0,5};

        System.out.print(Arrays.toString(new SlidingWindow().maxSlidingWindow(arr,3)));

    }

}
