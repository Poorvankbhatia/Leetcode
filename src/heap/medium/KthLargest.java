/*

Find the kth largest element in an unsorted array. Note that it is the kth largest element in
the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.



 */
package heap.medium;

import heap.MaxPriorityQueue;

import java.util.Arrays;

/**
 * Created by poorvank on 05/09/16.
 */
public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        Integer[] newArray = new Integer[nums.length];
        int i = 0;
        for (int value : nums) {
            newArray[i++] = value;
        }
        System.out.println(Arrays.toString(newArray));
        MaxPriorityQueue<Integer> pq =new MaxPriorityQueue<>(newArray);

        int kthLargestElement = -1;

        while (k!=0) {
            if(!pq.isEmpty()) {
                kthLargestElement = pq.deleteMax();
            }
            k--;
        }

        return kthLargestElement;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3,2,1,5,6,4};
        KthLargest k = new KthLargest();
        System.out.println(k.findKthLargest(a,2));
    }
}
