/*

Find the kth largest element in an unsorted array. Note that it is the kth largest element in
the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.



 */
package heap.medium;

import java.util.Random;

/**
 * Created by poorvank on 05/09/16.
 */
public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        if(nums==null || nums.length==0) {
            return Integer.MAX_VALUE;
        }
        shuffle(nums);
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }

    private int quickSelect(int[] nums,int start,int end,int target) {

        int left = start;
        int pivot = nums[end];
        for (int i=start;i<end;i++) {
            if(nums[i]<=pivot) {
                swap(nums,left++,i);
            }
        }
        swap(nums,left,end);
        if(left==target) {
            return nums[left];
        } else if(left>target) {
            return quickSelect(nums,start,left-1,target);
        } else {
            return quickSelect(nums,left+1,end,target);
        }

    }

    private void swap(int[] nums,int i1,int i2) {
        int temp = nums[i1];
        nums[i1]=nums[i2];
        nums[i2]=temp;
    }

    /*
Sorting an already sorted array is worst case in quicksort, because whenever you pick a pivot,
you discover that all the elements get placed on the same side of the pivot, so you don't split into two roughly equal halves at all.
And often in practice this already sorted case will turn up more often than other cases.
Randomly shuffling the data first is a quick way of ensuring that you really do end up with all cases turning up with equal probability,
and therefore that this worst case will be as rare as any other case.
It's worth noting that there are other strategies that deal well with already sorted data, such as choosing the middle element as the pivot.
     */
    private void shuffle(int a[]) {

        Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{3,2,1,5,6,4};
        KthLargest k = new KthLargest();
        System.out.println(k.findKthLargest(a,2));
    }
}

/*

The basic idea is to use Quick Select algorithm to partition the array with pivot:

Put numbers < pivot to pivot's left
Put numbers > pivot to pivot's right
Then

if indexOfPivot == k, return A[k]
else if indexOfPivot < k, keep checking left part to pivot
else if indexOfPivot > k, keep checking right part to pivot

https://stackoverflow.com/questions/8783408/why-is-the-runtime-of-the-selection-algorithm-on

Why BS is O(logn) and quickselect is O(n):

Binary search algorithm in each step does O(1) operations. Totally there are log(N) steps and this makes it O(log(N))

Selection algorithm in each step performs O(n) operations. But this 'n' keeps on reducing by half each time. There are totally log(N) steps. This makes it N + N/2 + N/4 + ... + 1 (log(N) times) = 2N = O(N)

For binary search it is 1 + 1 + ... (log(N) times) = O(logN)

Using heap:

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

 */