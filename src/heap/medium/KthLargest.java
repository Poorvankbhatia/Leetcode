/*

Find the kth largest element in an unsorted array. Note that it is the kth largest element in
the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.



 */
package heap.medium;

/**
 * Created by poorvank on 05/09/16.
 */
public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        if(nums==null || nums.length==0) {
            return Integer.MAX_VALUE;
        }
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